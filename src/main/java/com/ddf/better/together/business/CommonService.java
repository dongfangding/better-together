package com.ddf.better.together.business;

import com.ddf.better.together.model.request.EmailCodeRequest;

/**
 * <p>通用业务类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/01 22:34
 */
public interface CommonService {

    /**
     * 生成邮箱验证码
     *
     * @param request
     * @return
     */
    Boolean generateEmailCode(EmailCodeRequest request);
}
