package xyz.liangwh.headwaters.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.cglib.core.HashCodeCustomizer;

import java.util.Date;
@Data
@TableName("hw_mark")
public class HeadwatersPo extends Model<HeadwatersPo> {

    @TableField
    private Integer id;
    @TableField(value = "busi_key")
    private String key;
    @TableField(value = "inside_id")
    private Integer insideId;
    @TableField(value = "max_id")
    private Long maxId;
    @TableField
    private Integer step;
    @TableField
    private String desc;
    @TableField(value = "update_time")
    private Date updateTime;


}
