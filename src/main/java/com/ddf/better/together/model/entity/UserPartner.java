package com.ddf.better.together.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * 用户伙伴关系表
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-01
 */
@Data
@TableName("user_partner" )
public class UserPartner implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id" , type = IdType.AUTO)
    private Long id;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 伙伴uid
     */
    private String partnerUid;

    /**
     * 好友备注
     */
    private String partnerNameRemark;

    /**
     * 成为伙伴时间
     */
    private LocalDateTime createTime;

    /**
     * 0 有效伙伴 1 解散申请中 2 已解散
     */
    private Integer status;

    /**
     * 伙伴关系变更时间
     */
    private LocalDateTime statusUpdateTime;


}
