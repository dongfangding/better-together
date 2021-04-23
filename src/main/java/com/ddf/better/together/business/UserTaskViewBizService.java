package com.ddf.better.together.business;

import com.ddf.better.together.model.request.UserTaskViewFinishRequest;
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
     * 用户待完成任务视图分页查询
     *
     * @param request
     * @return
     */
    PageResult<UserTaskViewResponse> myTaskPageList(UserTaskViewPageRequest request);


    /**
     * 用户监督的任务分页查询
     *
     * @param request
     * @return
     */
    PageResult<UserTaskViewResponse> mySupervisedTaskPageList(UserTaskViewPageRequest request);

    /**
     * 完成任务并指定奖励
     *
     * @param request
     * @return
     */
    Boolean finishedTask(UserTaskViewFinishRequest request);

}
