package xyz.liangwh.esdemos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchNoneQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class EsDemosApplicationTests {

    @Autowired
    TransportClient transportClient;



    //#############################  transportClient  #############################################
    /**
     * 创建索引，或者插入更新数据
     */
    @Test
    @SneakyThrows
    void createIndex(){
        IndexResponse response = transportClient
                .prepareIndex("product2","_doc","10")
                .setSource(XContentFactory.jsonBuilder().startObject()
                        .field("name","Honor MagicBook Pro")
                        .field("desc","一款轻便的笔记本")
                        .field("price",5399)
                        .field("pulldate","2019-11-11")
                        .field("tags",new String[]{"AMD","AMD-R7","Honor","qingbian","性价比","办公","笔记本"})
                        .endObject()
                ).get();
        System.out.println(response.getResult());
    }
    /**
     * 批量插入更新数据
     */
    @Test
    @SneakyThrows
    void _bulkDocs(){
       BulkRequestBuilder bulkRequestBuilder =  transportClient.prepareBulk();
        bulkRequestBuilder
                .add(transportClient.prepareIndex("product2","_doc")
                        .setSource(XContentFactory.jsonBuilder().startObject()
                                .field("name","Honor MagicBook")
                                .field("desc","一款轻便的笔记本")
                                .field("price",3399)
                                .field("pulldate","2019-11-11")
                                .field("tags",new String[]{"AMD","AMD-R5","Honor","qingbian","性价比","办公","笔记本"})
                                .endObject()))
                .add(transportClient.prepareIndex("product2","_doc")
                        .setSource(XContentFactory.jsonBuilder().startObject()
                                .field("name","Huawei MateBook Pro")
                                .field("desc","一款轻便的笔记本")
                                .field("price",6999)
                                .field("pulldate","2019-05-11")
                                .field("tags",new String[]{"Inter","10900","HUAWEI","qingbian","高档","办公","笔记本"})
                                .endObject()))
                .add(transportClient.prepareIndex("product2","_doc")
                        .setSource(XContentFactory.jsonBuilder().startObject()
                                .field("name","LIANGXIANG X9700")
                                .field("desc","一款高性能笔记本")
                                .field("price",8888)
                                .field("pulldate","2019-11-11")
                                .field("tags",new String[]{"Inter","9700","LIANGXIANG","游戏","笔记本"})
                                .endObject()))
        ;
        BulkResponse bulkItemResponses = bulkRequestBuilder.execute().actionGet();
        //bulkItemResponses.getItems()[0].getResponse()S
        //System.out.println(new Gson().toJson(bulkItemResponses).toString());

    }

    /**
     * 普通查询数据
     */
    @Test
    @SneakyThrows
    void _searchAll(){
        SearchResponse product2 = transportClient.prepareSearch("product2").get();
        SearchHits hits = product2.getHits();
        for (SearchHit next : hits){
            Map<String, Object> sourceAsMap = next.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }
    @Test
    @SneakyThrows
    void _searchById(){
        GetResponse getResponse = transportClient.prepareGet("product2","_doc","1").get();
        System.out.println(getResponse);
    }

    @Test
    @SneakyThrows
    void updateById(){
        UpdateResponse updateResponse = transportClient.prepareUpdate("product2", "_doc", "1").setDoc(XContentFactory.jsonBuilder().startObject().field("price", 6899).endObject()).get();
        System.out.println(updateResponse);

    }

    @Test
    @SneakyThrows
    void deleteById(){
        DeleteResponse deleteResponse = transportClient.prepareDelete("product2", "_doc", "10").get();
        System.out.println(deleteResponse);
    }

    @Test
    @SneakyThrows
    void query(){
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        SearchResponse product2 = transportClient.prepareSearch("product2")
                .setQuery(matchAllQueryBuilder).setSize(2).get();
        SearchHits hits = product2.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * query 查询 match匹配
     */
    @Test
    @SneakyThrows
    void queryMatch(){
        SearchResponse searchResponse = transportClient.prepareSearch("product2")
                .setQuery(QueryBuilders.matchQuery("tags", "R7")).get();
        SearchHits hits = searchResponse.getHits();
        for(SearchHit hit : hits){
            System.out.println(hit.getSourceAsString());
        }
    }

    @Test
    @SneakyThrows
    void queryTrem(){
        SearchResponse searchResponse = transportClient.prepareSearch("product2")
                .setQuery(QueryBuilders.termQuery("tags", "AMD-R7")).get();
        SearchHits hits = searchResponse.getHits();
        for(SearchHit hit : hits){
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
    聚合查询 aggs
     {"size":0,
     "aggs": {"group_by_price": {"range": {"field": "price",
     "ranges": [{"from": 0,"to": 5000},{"from": 5001,"to": 8000},{"from": 8001,"to": 12000}]},
     "aggs": {"product": {"terms": {"field": "name.keyword"}}}}} }
     */
    @Test
    @SneakyThrows
    void queryAggs(){
        SearchResponse searchResponse = transportClient.prepareSearch("product2")
                .addAggregation(
                        AggregationBuilders.range("group_by_price")
                                .field("price")
                                .addRange(0, 5000)
                                .addRange(5001, 8000)
                                .addRange(8001, 12000)
                                .subAggregation(AggregationBuilders.terms("product").field("name.keyword"))
                ).setSize(0).execute().actionGet();

        Aggregations aggregations = searchResponse.getAggregations();
        Map<String, Aggregation> asMap = aggregations.getAsMap();
        System.out.println(asMap);

    }


}
