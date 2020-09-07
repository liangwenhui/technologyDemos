package xyz.liangwh.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class JdbcApplication {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {

        SpringApplication.run(JdbcApplication.class, args);


    }


    @Bean
    public Object getId(){
        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("select T_BFM_STAFF_ID_SEQ.nextval as nextval",new Object[0]);
//                jdbcTemplate.queryForList("select null as nextval from bfm_serv_log limit 1",new Object[0]);
        Map<String, Object> map = maps.get(0);
        System.out.println("look here！！！！："+maps);
        String value = String.valueOf(map.get("nextval"));
        Long i = Long.valueOf(Long.parseLong(value));
        System.out.println(i);
        return i;
    }
}
