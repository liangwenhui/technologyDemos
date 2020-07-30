package xyz.liangwh.esdemos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class EsDemosApplicationTests {

    @Autowired
    TransportClient transportClient;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    //#############################  transportClient  #############################################
    /**
     * 创建索引，或者插入更新数据
     */
    @Test
    @SneakyThrows
    void createIndex(){
        IndexResponse response = transportClient
                .prepareIndex("product2","_doc","1")
                .setSource(XContentFactory.jsonBuilder().startObject()
                        .field("name","Honor MagicBook Pro")
                        .field("desc","一款轻便的笔记本")
                        .field("price","5399")
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
                                .field("price","3399")
                                .field("pulldate","2019-11-11")
                                .field("tags",new String[]{"AMD","AMD-R5","Honor","qingbian","性价比","办公","笔记本"})
                                .endObject()))
                .add(transportClient.prepareIndex("product2","_doc")
                        .setSource(XContentFactory.jsonBuilder().startObject()
                                .field("name","Huawei MateBook Pro")
                                .field("desc","一款轻便的笔记本")
                                .field("price","6999")
                                .field("pulldate","2019-05-11")
                                .field("tags",new String[]{"Inter","10900","HUAWEI","qingbian","高档","办公","笔记本"})
                                .endObject()))
                .add(transportClient.prepareIndex("product2","_doc")
                        .setSource(XContentFactory.jsonBuilder().startObject()
                                .field("name","LIANGXIANG X9700")
                                .field("desc","一款高性能笔记本")
                                .field("price","8888")
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


}
