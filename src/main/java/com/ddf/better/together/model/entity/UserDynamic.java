package com.ddf.better.together.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * 用户动态
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-04
 */
@Data
@TableName("user_dynamic" )
public class UserDynamic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id" , type = IdType.AUTO)
    private Long id;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 动态内容
     */
    private String content;

    /**
     * 图片资源id集合，多个用逗号分隔
     */
    private String picResourceIds;

    /**
     * 视频资源id
     */
    private Long videoResourceId;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 0 公开 1 仅自己可见
     */
    private Integer viewLevel;

    /**
     * 定位地址
     */
    private String location;


}
