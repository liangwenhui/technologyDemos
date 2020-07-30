package xyz.liangwh.esdemos.beans;

import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetAddress;

@Component
public class EsClientsConfig {


    @Autowired
    TransportClient transportClient;

    @Autowired
    RestHighLevelClient restHighLevelClient;
    /**
     * 创建 TransportClient ,ES 7.0 弃用该客户端，建议使用 Hight level REST client
     * @return
     */
    @SneakyThrows
    @Bean(name = "transportClient")
    public TransportClient getTransportClient(){
        Settings settings =
                    Settings.builder().put("cluster.name", "elasticsearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                                //使用ES 通信端口
                                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));
        System.out.println("BEAN TransportClient [transportClient] create success");
        return client;
    }

    @SneakyThrows
    @Bean(name = "restHighLevelClient")
    public RestHighLevelClient getRestHighLevelClient(){
        HttpHost[] hosts;
        //RestHighLevelClient 使用 ES server 端口
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost",9200,"http"));
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);

        System.out.println("BEAN RestHighLevelClient [restHighLevelClient] create success");


        return client;
    }

    @PreDestroy
    @SneakyThrows
    public void destroy(){
        System.out.println("######################## DESTROY #############################");
        if (transportClient!=null){
            transportClient.close();
        }
        if (restHighLevelClient!=null){
            restHighLevelClient.close();
        }
    }


}
