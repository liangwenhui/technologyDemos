package xyz.liangwh.eurekaservice.test;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 拦截器
 */
public class MyClientHttpRequestInterceptor
implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        System.out.println("拦截了！！！");
        System.out.println(httpRequest.getURI());
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);
        System.out.println(response.getHeaders());

        return response;
    }
}
