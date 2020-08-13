package xyz.liangwh.headwaters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.liangwh.headwaters.core.dao.HwMarkDao;
import xyz.liangwh.headwaters.core.model.HeadwatersPo;
import xyz.liangwh.headwaters.core.model.HwMarkSamplePo;

import java.util.List;

@SpringBootTest
public class HwMarkDapApplicationTest {

    @Autowired
    HwMarkDao hwMarkDao;

    @Test
    void testGetAllMark(){
        List<HeadwatersPo> allHeadwaters = hwMarkDao.getAllHeadwaters();
        System.out.println(allHeadwaters);
    }
    @Test
    void testUpdateAndGet(){
        String key = "one";
        HeadwatersPo po = hwMarkDao.updateAndGetHeadwaters(key);
        System.out.println(po);
    }

    @Test
    void testUpdateAndGet2(){
        String key = "one";
        int step = 2000;
        HeadwatersPo po = hwMarkDao.updateAutoAndGetHeadwaters(key,step);
        System.out.println(po);
    }

    @Test
    void testGetSamples(){
        List<HwMarkSamplePo> allKeyMap = hwMarkDao.getAllKeyMap();
        System.out.println(allKeyMap);
    }




}
