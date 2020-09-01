package xyz.liangwh.adminservice;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xyz.liangwh.adminservice.notifier.DingDingNotifier;

@SpringBootApplication
@EnableAdminServer
public class AdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }
    @Bean
    public DingDingNotifier dingDingNotifier(InstanceRepository repository) {
        return new DingDingNotifier(repository);
    }
}
