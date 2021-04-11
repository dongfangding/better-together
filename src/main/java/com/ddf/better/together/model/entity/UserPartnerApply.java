package com.ddf.better.together.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * 用户伙伴申请记录表
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-01
 */
@Data
@TableName("user_partner_apply" )
public class UserPartnerApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id" , type = IdType.AUTO)
    private Long id;

    /**
     * 0 伙伴申请 1 伙伴关系解散
     */
    private Integer type;

    /**
     * 发起人uid
     */
    private String fromUid;

    /**
     * 目标uid
     */
    private String targetUid;

    /**
     * 目标名称备注
     */
    private String targetNameRemark;

    /**
     * 发起时间
     */
    private LocalDateTime applyTime;

    /**
     * 0 已发起 1 已同意 2 已拒绝 3 已过期
     */
    private Integer status;

    /**
     * 处理时间
     */
    private LocalDateTime dealTime;

    /**
     * 申请备注
     */
    private String applyRemark;


}
