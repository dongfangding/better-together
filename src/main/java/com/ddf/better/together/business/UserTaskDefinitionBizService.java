package com.ddf.better.together.business;

import com.ddf.better.together.model.request.DefinitionTaskRequest;
import com.ddf.better.together.model.request.UserTaskDefinitionRequest;
import com.ddf.better.together.model.response.UserTaskDefinitionResponse;
import com.ddf.boot.common.core.model.PageResult;

/**
 * <p>用户任务定义资源库业务累</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/10 15:05
 */
public interface UserTaskDefinitionBizService {

    /**
     * 定义任务
     *
     * @param request
     * @return
     */
    Boolean definitionTask(DefinitionTaskRequest request);

    /**
     * 查询登陆用户定义的任务
     *
     * @param request
     * @return
     */
    PageResult<UserTaskDefinitionResponse> myTaskDefinition(UserTaskDefinitionRequest request);
}
