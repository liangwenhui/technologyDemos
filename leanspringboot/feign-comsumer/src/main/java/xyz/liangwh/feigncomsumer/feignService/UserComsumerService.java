package xyz.liangwh.feigncomsumer.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import xyz.liangwh.feignapi.service.UserService;
@FeignClient(name="provider")
public interface UserComsumerService extends UserService {
}
