package xyz.liangwh.eurekaservice.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import xyz.liangwh.eurekaservice.Persion;

import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("rest")
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("getMap")
    public Map map(){
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://EUREKA-CLIENT-1/provide/getMap", Map.class);
        Map body = forEntity.getBody();
        return body;
    }

    @RequestMapping("getObj")
    public Persion obj(){
        ResponseEntity<Persion> forEntity = restTemplate.getForEntity("http://EUREKA-CLIENT-1/provide/getObj", Persion.class);
        Persion body = forEntity.getBody();
        return body;
    }

    /**
     * 使用占位符传参
     * @param name
     * @param age
     * @return
     */
    @GetMapping("persion/v1/{name}/{age}")
    public Persion persion(@PathVariable("name") String name, @PathVariable("age")int age){
        ResponseEntity<Persion> forEntity = restTemplate.getForEntity("http://EUREKA-CLIENT-1/provide/getPersion?name={1}&age={2}", Persion.class,name,age);
        Persion body = forEntity.getBody();
        return body;
    }

    /**
     * 使用map传参
     * @param name
     * @param age
     * @return
     */
    @GetMapping("persion/v2/{name}/{age}")
    public Persion persion2(@PathVariable("name") String name, @PathVariable("age")int age){
        Map params = new HashMap();
        params.put("nnnn",name);
        params.put("aaaa",age);
        ResponseEntity<Persion> forEntity = restTemplate.getForEntity("http://EUREKA-CLIENT-1/provide/getPersion?name={nnnn}&age={aaaa}", Persion.class,params);
        Persion body = forEntity.getBody();
        return body;
    }


    /**
     * post 请求
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("persion/post/{name}/{age}")
    public Persion persion3(@PathVariable("name") String name, @PathVariable("age")int age){
        Map params = new HashMap();
        params.put("name",name);
        params.put("age",age);
        ResponseEntity<Persion> forEntity =
                restTemplate.postForEntity("http://EUREKA-CLIENT-1/provide/getPersion/p", params,Persion.class);
        Persion body = forEntity.getBody();
        return body;
    }
    @RequestMapping("locat")
    public String postForLocation(){
        Map params = new HashMap();
        params.put("name","springboot");
        ResponseEntity<Persion> persionResponseEntity = restTemplate.postForEntity("http://EUREKA-CLIENT-1/provide/location", params, Persion.class);
        return null;

    }

}
