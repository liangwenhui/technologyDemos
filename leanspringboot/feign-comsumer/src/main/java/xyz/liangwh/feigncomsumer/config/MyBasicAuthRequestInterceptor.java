package xyz.liangwh.feigncomsumer.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;

public class MyBasicAuthRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization","Basic bHdoOmx3aDE2MTg=");//bHdoOmx3aDE2MTg=
    }
}
