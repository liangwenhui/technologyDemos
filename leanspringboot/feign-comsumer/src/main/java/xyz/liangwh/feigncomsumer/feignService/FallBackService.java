package xyz.liangwh.feigncomsumer.feignService;

import org.springframework.stereotype.Component;
import xyz.liangwh.feignapi.service.UserService;

import java.util.Collections;
import java.util.Map;

@Component
public class FallBackService implements UserComsumerService {

    @Override
    public String getUsername() {
        return "not name";
    }

    @Override
    public Map getMap1() {
        return Collections.singletonMap("fk","1");
    }

    @Override
    public Map getMap2(String name) {
        return Collections.singletonMap("fk","2");
    }

    @Override
    public Map postMap1(String name) {
        return Collections.singletonMap("fk","2");
    }
}
