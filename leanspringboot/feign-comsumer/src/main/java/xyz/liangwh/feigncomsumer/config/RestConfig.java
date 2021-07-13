package xyz.liangwh.feigncomsumer.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration


public class RestConfig {


    @Bean
    public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory httpsFactory){
        return new RestTemplate(httpsFactory);
    }

    @Bean(name = "httpsFactory")
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory()
            throws Exception {
        CloseableHttpClient httpClient = HttpClientUtils.acceptsUntrustedCertsHttpClient();
        HttpComponentsClientHttpRequestFactory httpsFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);
        httpsFactory.setReadTimeout(40000);
        httpsFactory.setConnectTimeout(40000);
        return httpsFactory;
    }
}
