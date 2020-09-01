package xyz.liangwh.eurekaservice.provide;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.liangwh.eurekaservice.Persion;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("provide")
public class ProvideController {

    @RequestMapping("getMap")
    public Map map(){
        Map map = new HashMap();
        map.put("username","liangwh");
        return map;
    }

    @RequestMapping("getObj")
    public Persion obj(){
        Persion persion =new Persion();
        persion.setAge(11);
        persion.setName("liangwh");
        return persion;
    }
    @RequestMapping("getPersion")
    public Persion persion(String name,int age){
        Persion persion =new Persion();
        persion.setAge(age);
        persion.setName(name);
        return persion;
    }
    @PostMapping("getPersion/p")
    public Persion persion2(@RequestBody Persion p){
        Persion persion =new Persion();
        persion.setAge(p.getAge());
        persion.setName(p.getName());
        return persion;
    }

    @PostMapping("location")
    public Persion location(@RequestBody Persion persion, HttpServletResponse response) throws URISyntaxException {
        String kw = persion.getName();

        URI uri = new URI("https://www.baidu.com?wd="+kw);
        response.setHeader("Location",uri.toString());
        return persion;
    }


}
