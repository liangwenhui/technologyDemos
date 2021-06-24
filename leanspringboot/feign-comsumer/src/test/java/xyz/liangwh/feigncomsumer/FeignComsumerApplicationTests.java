package xyz.liangwh.feigncomsumer;

import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class FeignComsumerApplicationTests {

    @Test
    @SneakyThrows
    void contextLoads() {
        CloseableHttpClient httpClient =HttpClientBuilder.create().build();
        //HttpPost httpPost = new HttpPost("http://www.baidu.com");
        HttpPost httpPost = new HttpPost("https://134.224.175.6:8443/cas/casrs/storage/volume");
        httpPost.setHeader("ContentType","application/xml");
        httpPost.setHeader("Authorization","basic asdadada==");
        httpPost.setEntity(new StringEntity("<domain>\n" +
                "    <id>37</id>\n" +
                "    <name>zdbsxjtest3</name>\n" +
                "    <network>\n" +
                "        <mac>18:08:20:00:00:05</mac>\n" +
                "        <ipAddr>173.12.23.34</ipAddr>\n" +
                "        <vsId>9</vsId>\n" +
                "        <vsName>yewu</vsName>\n" +
                "        <deviceModel>virtio</deviceModel>\n" +
                "        <isKernelAccelerated>1</isKernelAccelerated>        \n" +
                "        <mode>veb</mode>\n" +
                "        <profileId>1</profileId>\n" +
                "        <profileName>Default</profileName>\n" +
                "        <hotPluggable>true</hotPluggable>\n" +
                "    </network>\n" +
                "</domain>\n"));
        CloseableHttpResponse execute = httpClient.execute(httpPost);

        System.out.println(execute.getStatusLine());

        execute.close();

    }

}
