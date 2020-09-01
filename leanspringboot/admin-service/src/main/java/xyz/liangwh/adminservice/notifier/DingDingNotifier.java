package xyz.liangwh.adminservice.notifier;

import com.alibaba.fastjson.JSONObject;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import reactor.core.publisher.Mono;

import java.util.Map;

public class DingDingNotifier extends AbstractStatusChangeNotifier {
    public DingDingNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        String serviceName = instance.getRegistration().getName();
        String serviceUrl = instance.getRegistration().getServiceUrl();
        String status = instance.getStatusInfo().getStatus();
        Map<String, Object> details = instance.getStatusInfo().getDetails();
        StringBuilder str = new StringBuilder();
        str.append("服务监控 : 【" + serviceName + "】").append("\r\n");
        str.append("【服务地址】" + serviceUrl).append("\r\n");
        str.append("【状态】" + status).append("\r\n");
        str.append("【详情】" + JSONObject.toJSONString(details)).append("\r\n");
        return Mono.fromRunnable(() -> {
            DingDingMessageUtil.sendTextMessage(str.toString());
        });
    }
}
