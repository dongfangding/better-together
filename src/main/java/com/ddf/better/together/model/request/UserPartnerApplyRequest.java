package com.ddf.better.together.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>创建伙伴申请记录</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/02 14:00
 */
@Data
public class UserPartnerApplyRequest {

    /**
     * 目标uid
     */
    @NotBlank(message = "目标用户uid不能为空")
    private String targetUid;
}
