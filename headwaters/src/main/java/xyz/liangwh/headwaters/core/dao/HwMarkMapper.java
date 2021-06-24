package xyz.liangwh.headwaters.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import xyz.liangwh.headwaters.core.model.HeadwatersPo;
import xyz.liangwh.headwaters.core.model.HwMarkSamplePo;

import java.util.List;
//@Repository
@Mapper
public interface HwMarkMapper  {

    @Select("SELECT id, busi_key, inside_id, max_id, step, `desc`, update_time FROM hw_mark")
    @Results(value = {
        @Result(column = "id",property = "id"),
        @Result(column = "busi_key",property = "key"),
        @Result(column = "inside_id",property = "insideId"),
        @Result(column = "max_id",property = "maxId"),
        @Result(column = "step",property = "step")
    }
    )
    List<HeadwatersPo> getAll();

    @Select("SELECT id, busi_key FROM hw_mark")
    @Results(value = {
            @Result(column = "id",property = "id"),
            @Result(column = "busi_key",property = "key")
    }
    )
    List<HwMarkSamplePo> getAllSample();

    @Select("SELECT id, busi_key, inside_id, max_id, step, `desc`, update_time FROM hw_mark " +
            "WHERE busi_key=#{key} limit 1")
    @Results(value = {
            @Result(column = "busi_key",property = "key")
    }
    )
    HeadwatersPo getOneByKey(String key);

    /**
     * update hw_mark set
     *     inside_id = inside_id+step,
     *     max_id = (id<<32) | (inside_id)
     * where busi_key = 'one'
     */
    @Update("UPDATE hw_mark SET inside_id = inside_id+step,max_id=(id<<32)|inside_id WHERE busi_key = #{key}")
    void updateByKey(String key);

    /**
     * update hw_mark set
     *     inside_id = inside_id+step,
     *     max_id = (id<<32) | (inside_id)
     * where busi_key = 'one'
     */
    @Update("UPDATE hw_mark SET step = #{step},inside_id = inside_id+#{step},max_id=(id<<32)|inside_id WHERE busi_key = #{key}")
    void updateByKeyAndAutoStep(String key,int step);


}
