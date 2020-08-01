package xyz.liangwh.esdemos;

import com.google.gson.Gson;
import lombok.Data;
import lombok.SneakyThrows;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *RestHighLevelClient tests
 */
@SpringBootTest
public class EsDemosAppHLRCTests {


    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     */
    @Test
    @SneakyThrows
    void creatIndex(){
        CreateIndexRequest request = new CreateIndexRequest("product3");
        request.settings(Settings.builder()
                .put("index.number_of_shards",1)
                .put("index.number_of_replicas",0));

                CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
                if (response.isAcknowledged()) {
                    System.out.println("创建成功");
                }else {
                    System.out.println("创建失败");
                }
    }

    /**
     * _cat/indices
     */
    @Test
    @SneakyThrows
    void getIndex(){
        org.elasticsearch.client.indices.GetIndexRequest request
                = new org.elasticsearch.client.indices.GetIndexRequest("product*");
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(request, RequestOptions.DEFAULT);
        String[] indices = getIndexResponse.getIndices();
        for(String index: indices){
            System.out.println(index);
        }
    }

    /**
     * DELETE index
     */
    @Test
    @SneakyThrows
    void deleteIndex(){
        AcknowledgedResponse product3 = restHighLevelClient.indices().delete(new DeleteIndexRequest("product3"), RequestOptions.DEFAULT);
        if(product3.isAcknowledged()){
            System.out.println("删除index成功");
        }else{
            System.out.println("删除index失败");
        }
    }

    @Data
    class Product3{
        //private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer id;
        String name;
        Integer price;
        String makeArea;
        Date makeDate;
        @SneakyThrows
        public Product3(Integer id,String name, Integer price, String makeArea, Date makeDate) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.makeArea = makeArea;
            this.makeDate = makeDate;
        }
    }
    /**
     * 插入数据
     */
    @Test
    @SneakyThrows
    void insertDatas(){
        //List<Product3> plist = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Product3 product3 = new Product3(1, "xiaomi shouji", 5000, "China", sdf.parse("2020-01-23"));
        //plist.add(new Product3(2,"huawei shouji", 8000, "China", "2020-02-22"));
        //plist.add(new Product3(3,"apple shouji",3000,"Usa","2020-02-23"));
        //plist.add(new Product3(4,"snmsung shouji",1000,"Korea","2020-03-23"));
        IndexRequest request = new IndexRequest("product3");
        request.id(product3.getId().toString());
        Gson gson = new Gson();
        request.source(XContentFactory.jsonBuilder()
                .startObject()
                .field("name",product3.getName())
                .field("price",product3.getPrice())
                .field("make_area",product3.getMakeArea())
                .field("make_date",sdf.format(product3.getMakeDate()))
                .endObject());
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response);

    }

    /**
     * 批量插入
     */
    @Test
    @SneakyThrows
    void batchInsertDatas(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        BulkRequest request = new BulkRequest("product3");
        List<Product3> plist = new ArrayList<>();
        plist.add(new Product3(2,"huawei shouji", 8000, "China", sdf.parse("2020-02-22")));
        plist.add(new Product3(3,"apple shouji",3000,"Usa",sdf.parse("2020-02-23")));
        plist.add(new Product3(4,"snmsung shouji",1000,"Korea",sdf.parse("2020-03-23")));

        for(Product3 p: plist){
            request.add(new IndexRequest()
                    .id(p.getId().toString())
                    .source(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("name",p.getName())
                            .field("price",p.getPrice())
                            .field("make_area",p.getMakeArea())
                            .field("make_date",sdf.format(p.getMakeDate()))
                            .endObject()));
        }
        BulkResponse bulk = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulk.getItems());
    }

    /**
     * get by id
     */
    @Test
    @SneakyThrows
    void getById(){
        GetRequest getRequest = new GetRequest("product3", "1");
        String[] includes = {"name","price"};
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true,includes,null);
        getRequest.fetchSourceContext(fetchSourceContext);
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse);
    }
    @Test
    @SneakyThrows
    void multiGetById(){
        MultiGetRequest request = new MultiGetRequest();
        request.add("product3","1");
        request.add("product","1");
        MultiGetResponse mget = restHighLevelClient.mget(request, RequestOptions.DEFAULT);
        for(MultiGetItemResponse response: mget){
            System.out.println(response.getResponse().getSourceAsString());
        }
    }

    @Test
    @SneakyThrows
    void uodateByQurey(){
        UpdateByQueryRequest request = new UpdateByQueryRequest("product3");
        request.setQuery(QueryBuilders.matchQuery("name","apple"));
        request.setScript(
                new Script(ScriptType.INLINE,"painless","ctx._source.price+=1", Collections.emptyMap())
        );
        BulkByScrollResponse bulkByScrollResponse = restHighLevelClient.updateByQuery(request, RequestOptions.DEFAULT);
        System.out.println(bulkByScrollResponse);
    }

}
