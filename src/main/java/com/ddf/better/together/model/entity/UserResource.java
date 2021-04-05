package com.ddf.better.together.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * 用户上传的资源表
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-04
 */
@Data
@TableName("user_resource" )
public class UserResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id" , type = IdType.AUTO)
    private Long id;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 图片地址
     */
    private String picUrl;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 资源扩展信息
     */
    private String extra;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;


}
