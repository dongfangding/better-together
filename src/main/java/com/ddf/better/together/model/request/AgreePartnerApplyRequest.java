package com.ddf.better.together.model.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>同意成为伙伴请求参数</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/02 15:53
 */
@Data
public class AgreePartnerApplyRequest {

    /**
     * 申请记录id
     */
    @NotNull(message = "申请记录id不能为空")
    private Long applyId;

    /**
     * 好友备注名称
     */
    private String partnerNameRemark;
}
