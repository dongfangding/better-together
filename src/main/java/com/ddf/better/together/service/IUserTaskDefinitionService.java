package com.ddf.better.together.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddf.better.together.model.entity.UserTaskDefinition;

/**
 * <p>
 * 用户任务定义表，这里作为用户自己定义的任务资源库，一旦任务开始，则会复制数据到user_task_view中，任务的结算等与这个数据挂钩 用户任务定义表 服务类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-09
 */
public interface IUserTaskDefinitionService extends IService<UserTaskDefinition> {

    /**
     * 根据用户uid和任务名称查询任务记录
     *
     * @param uid
     * @param name
     * @return
     */
    UserTaskDefinition getUserTaskByName(String uid, String name);

}
