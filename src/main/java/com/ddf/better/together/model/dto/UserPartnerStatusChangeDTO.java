package com.ddf.better.together.model.dto;

import com.ddf.better.together.constants.enumeration.UserPartnerStatusEnum;
import lombok.Data;

/**
 * <p>用户伙伴关系变更</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/07 22:58
 */
@Data
public class UserPartnerStatusChangeDTO {

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 伙伴uid
     */
    private String partnerUid;

    /**
     * 变更后状态
     */
    private UserPartnerStatusEnum targetStatus;
}
