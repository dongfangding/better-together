package com.ddf.better.together.business;

import com.ddf.better.together.model.request.SearchUserDynamicRequest;
import com.ddf.better.together.model.request.UserDynamicPublishRequest;
import com.ddf.better.together.model.response.UserDynamicResponse;
import com.ddf.boot.common.core.model.PageResult;

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

    /**
     * 查询用户动态，如果是自己，则可以查看全部，如果非自己，则只能查看公开的动态
     *
     * @param request
     * @return
     */
    PageResult<UserDynamicResponse> searchUserDynamic(SearchUserDynamicRequest request);
}
