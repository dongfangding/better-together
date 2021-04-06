package com.ddf.better.together.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 用户动态收件箱，推模式下使用,注意发布人自己的动态也会推送一条给自己的数据
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-06
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@TableName("user_dynamic_receive_box" )
public class UserDynamicReceiveBox implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id" , type = IdType.AUTO)
    private Long id;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 动态id
     */
    private Long dynamicId;

    /**
     * 动态发布时间，冗余字段，方便排序
     */
    private LocalDateTime publishTime;


}
