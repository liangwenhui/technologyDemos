package xyz.liangwh.esdemos;

import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void bulkDocs(){
       BulkRequestBuilder bulkRequestBuilder =  transportClient.prepareBulk();


    }

}
