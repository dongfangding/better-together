package com.ddf.better.together.business;

import com.ddf.better.together.model.request.AgreePartnerApplyRequest;
import com.ddf.better.together.model.request.RefusePartnerApplyRequest;
import com.ddf.better.together.model.request.UserPartnerApplyRequest;
import com.ddf.better.together.model.response.UserPartnerApplyResponse;

/**
 * <p>用户伙伴申请</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/02 13:59
 */
public interface UserPartnerApplyBizService {

    /**
     * 伙伴关系申请
     *
     * @param request
     * @return
     */
    UserPartnerApplyResponse apply(UserPartnerApplyRequest request);

    /**
     * 同意成为伙伴
     *
     * @param request
     * @return
     */
    Boolean agree(AgreePartnerApplyRequest request);

    /**
     * 拒绝成为伙伴
     *
     * @param request
     * @return
     */
    Boolean refuse(RefusePartnerApplyRequest request);
}
