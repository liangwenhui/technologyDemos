package xyz.liangwh.coredemo.spring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.ServletRequestHandledEvent;
import org.springframework.web.servlet.FrameworkServlet;

/**
 * 通过spring事件获取http请求调用详情
 */
@Component
public class HttpCallEventListener implements ApplicationListener<ServletRequestHandledEvent> {
    @Override
    public void onApplicationEvent(ServletRequestHandledEvent servletRequestHandledEvent) {
        String servletName = servletRequestHandledEvent.getServletName();
        FrameworkServlet servlet=(FrameworkServlet)servletRequestHandledEvent.getSource();
        String clientAddress = servletRequestHandledEvent.getClientAddress();
        String requestUrl = servletRequestHandledEvent.getRequestUrl();
        String description = servletRequestHandledEvent.getDescription();
        int statusCode = servletRequestHandledEvent.getStatusCode();

    }
}
