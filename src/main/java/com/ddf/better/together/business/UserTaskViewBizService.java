package com.ddf.better.together.business;

import com.ddf.better.together.model.request.UserTaskViewPageRequest;
import com.ddf.better.together.model.response.UserTaskViewResponse;
import com.ddf.boot.common.core.model.PageResult;

/**
 * <p>任务视图业务接口累</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/21 19:35
 */
public interface UserTaskViewBizService {

    /**
     * 用户任务视图分页查询
     *
     * @param request
     * @return
     */
    PageResult<UserTaskViewResponse> myTaskPageList(UserTaskViewPageRequest request);
}
