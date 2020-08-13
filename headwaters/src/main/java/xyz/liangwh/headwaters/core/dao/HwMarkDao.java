package xyz.liangwh.headwaters.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.liangwh.headwaters.core.model.HeadwatersPo;
import xyz.liangwh.headwaters.core.model.HwMarkSamplePo;

import java.util.List;
@Component
public class HwMarkDao {

    @Autowired
    private HwMarkMapper hwMarkMapper;

    public List<HeadwatersPo> getAllHeadwaters() {
        return hwMarkMapper.getAll();
    }

    @Transactional
    public HeadwatersPo updateAndGetHeadwaters(String key) {
        hwMarkMapper.updateByKey(key);
        HeadwatersPo po = hwMarkMapper.getOneByKey(key);
        return po;
    }

    @Transactional
    public HeadwatersPo updateAutoAndGetHeadwaters(String key, int step) {
        hwMarkMapper.updateByKeyAndAutoStep(key,step);
        HeadwatersPo po = hwMarkMapper.getOneByKey(key);
        return po;
    }

    public List<HwMarkSamplePo> getAllKeyMap() {

        return hwMarkMapper.getAllSample();
    }


}
