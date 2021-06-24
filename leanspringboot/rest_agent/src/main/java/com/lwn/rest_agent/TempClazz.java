package com.lwn.rest_agent;

import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class TempClazz {


    public RestTemplateCustomizer restTemplateCustomizer(
            final LoadBalancerInterceptor loadBalancerInterceptor) {
        System.out.println("OSS重写 LoadBalancerInterceptorConfig restTemplateCustomizer !!!");
        RestTemplateCustomizer customizer = new LwhRestTemplateCustomizer(loadBalancerInterceptor) ;
        System.out.println("OSS重写 LoadBalancerInterceptorConfig restTemplateCustomizer end!!!");
        return customizer;
    }


    static class LwhClientHttpRequestInterceptor implements  ClientHttpRequestInterceptor{

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            URI originalUri = request.getURI();
            String authority = originalUri.getAuthority();
            ClientHttpResponse execute = null;
            try {
                execute = execution.execute(request, body);
            }catch (Exception e){
                System.err.println("RestTemplate 远程调用失败 具体服务实例地址为"+authority);
                RuntimeException exception = new RuntimeException("RestTemplate 远程调用失败 具体服务实例地址为"+authority, e);
                throw exception;
            }
            return execute;
        }
    }


    static class LwhRestTemplateCustomizer implements RestTemplateCustomizer{


        private LoadBalancerInterceptor loadBalancerInterceptor;

        public LwhRestTemplateCustomizer(LoadBalancerInterceptor loadBalancerInterceptor) {
            this.loadBalancerInterceptor = loadBalancerInterceptor;
        }

        @Override
        public void customize(RestTemplate restTemplate) {
            List<ClientHttpRequestInterceptor> list = new ArrayList<>(
                    restTemplate.getInterceptors());
            System.out.println("OSS重写 RestTemplateCustomizer  !!!");
            list.add(loadBalancerInterceptor);
            list.add(new LwhClientHttpRequestInterceptor());
            restTemplate.setInterceptors(list);
        }
    }





}
