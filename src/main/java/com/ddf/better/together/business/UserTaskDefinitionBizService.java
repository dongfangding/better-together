package com.ddf.better.together.business;

import com.ddf.better.together.model.request.DefinitionTaskRequest;

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
}
