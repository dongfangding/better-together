package com.ddf.better.together.business;

import com.ddf.better.together.model.request.UserDynamicPublishRequest;

/**
 * <p>用户动态业务实现类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/04 22:39
 */
public interface UserDynamicBizService {

    /**
     * 用户发布动态
     * @param request
     * @return
     */
    Boolean publishDynamic(UserDynamicPublishRequest request);
}
