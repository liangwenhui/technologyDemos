package xyz.liangwh.esdemos;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/**
 *RestHighLevelClient tests
 */
@SpringBootTest
public class EsDemosAppHLRCTests {


    @Autowired
    RestHighLevelClient restHighLevelClient;




}
