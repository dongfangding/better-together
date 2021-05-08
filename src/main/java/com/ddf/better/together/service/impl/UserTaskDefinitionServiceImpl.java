package com.ddf.better.together.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddf.better.together.mapper.UserTaskDefinitionMapper;
import com.ddf.better.together.model.entity.UserTaskDefinition;
import com.ddf.better.together.model.request.UserTaskDefinitionRequest;
import com.ddf.better.together.service.IUserTaskDefinitionService;
import com.ddf.boot.common.core.util.PageUtil;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户任务定义表，这里作为用户自己定义的任务资源库，一旦任务开始，则会复制数据到user_task_view中，任务的结算等与这个数据挂钩
 * 用户任务定义表 服务实现类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-09
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class UserTaskDefinitionServiceImpl extends ServiceImpl<UserTaskDefinitionMapper, UserTaskDefinition>
        implements IUserTaskDefinitionService {

    /**
     * 根据用户uid和任务名称查询任务记录
     *
     * @param name
     * @return
     */
    @Override
    public UserTaskDefinition getUserTaskByName(String uid, String name) {
        final LambdaQueryWrapper<UserTaskDefinition> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserTaskDefinition::getUid, uid);
        wrapper.eq(UserTaskDefinition::getName, name);
        return getOne(wrapper);
    }

    /**
     * 查询用户定义的任务
     *
     * @param request
     * @return
     */
    @Override
    public Page<UserTaskDefinition> getUserTaskDefinition(UserTaskDefinitionRequest request) {
        final Page<UserTaskDefinition> page = PageUtil.toMybatis(request);
        final LambdaQueryWrapper<UserTaskDefinition> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserTaskDefinition::getUid, request.getUid());
        wrapper.orderByDesc(UserTaskDefinition::getCreateTime);
        wrapper.likeLeft(StrUtil.isNotBlank(request.getName()), UserTaskDefinition::getName, request.getName());
        wrapper.eq(Objects.nonNull(request.getCycle()), UserTaskDefinition::getCycle, request.getCycle());
        wrapper.eq(Objects.nonNull(request.getActive()), UserTaskDefinition::getActive, request.getActive());
        wrapper.eq(Objects.nonNull(request.getSupervised()), UserTaskDefinition::getSupervised, request.getSupervised());
        wrapper.eq(Objects.nonNull(request.getSupervisedUid()), UserTaskDefinition::getSupervisedUid, request.getSupervisedUid());
        wrapper.eq(Objects.nonNull(request.getRewardType()), UserTaskDefinition::getRewardType, request.getRewardType());
        return page(page, wrapper);
    }
}
