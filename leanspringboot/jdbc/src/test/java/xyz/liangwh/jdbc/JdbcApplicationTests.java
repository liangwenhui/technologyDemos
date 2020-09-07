package xyz.liangwh.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class JdbcApplicationTests {
  @Autowired
 private JdbcTemplate jdbcTemplate;

  @Test
  public void test1(){
      List<Map<String, Object>> maps = jdbcTemplate.queryForList("select T_BFM_STAFF_ID_SEQ.nextval as nextval");
      Map<String, Object> map = maps.get(0);

      String value = String.valueOf(map.get("nextval"));
      long i = Long.valueOf(Long.parseLong(value));


      System.out.println(i);
  }

}
