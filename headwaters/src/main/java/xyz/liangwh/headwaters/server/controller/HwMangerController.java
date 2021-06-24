package xyz.liangwh.headwaters.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.liangwh.headwaters.core.dao.HwMarkDao;



@RestController
public class HwMangerController {

    @Autowired
    HwMarkDao hwMarkDao;
    @RequestMapping("test")
    @Transactional()
    public Object test(){
        return hwMarkDao.getAllHeadwatersxml();
    }

}
