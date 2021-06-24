package xyz.liangwh.headwaters.core.dao;

//import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.liangwh.headwaters.core.model.HeadwatersPo;

import javax.annotation.Resource;
import java.util.List;
@Mapper
public interface TestXmlDao {

    List<HeadwatersPo> getAAAss();
}
