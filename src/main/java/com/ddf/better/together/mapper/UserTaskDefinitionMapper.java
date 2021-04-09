package com.ddf.better.together.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddf.better.together.model.entity.UserTaskDefinition;

/**
 * <p>
 * 用户任务定义表，这里作为用户自己定义的任务资源库，一旦任务开始，则会复制数据到user_task_view中，任务的结算等与这个数据挂钩 用户任务定义表 Mapper 接口
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-09
 */
public interface UserTaskDefinitionMapper extends BaseMapper<UserTaskDefinition> {

}
