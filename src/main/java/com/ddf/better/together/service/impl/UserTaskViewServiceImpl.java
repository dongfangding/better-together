package com.ddf.better.together.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddf.better.together.mapper.UserTaskViewMapper;
import com.ddf.better.together.model.entity.UserTaskView;
import com.ddf.better.together.model.request.UserTaskViewPageRequest;
import com.ddf.better.together.service.IUserTaskViewService;
import com.ddf.boot.common.core.util.PageUtil;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户任务定义表 服务实现类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-09
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserTaskViewServiceImpl extends ServiceImpl<UserTaskViewMapper, UserTaskView> implements IUserTaskViewService {

    /**
     * 用户任务视图分页查询
     *
     * @param request
     * @return
     */
    @Override
    public Page<UserTaskView> pageList(UserTaskViewPageRequest request) {
        final LambdaQueryWrapper<UserTaskView> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(request.getUid())) {
            wrapper.eq(UserTaskView::getUid, request.getUid());
        }

        if (StrUtil.isNotBlank(request.getSupervisedUid())) {
            wrapper.eq(UserTaskView::getSupervisedUid, request.getSupervisedUid());
        }

        if (StrUtil.isNotBlank(request.getName())) {
            wrapper.likeLeft(UserTaskView::getName, request.getName());
        }

        if (Objects.nonNull(request.getCycle())) {
            wrapper.eq(UserTaskView::getCycle, request.getCycle().getCode());
        }

        if (Objects.nonNull(request.getStartTime())) {
            wrapper.eq(UserTaskView::getStartTime, request.getStartTime());
        }

        if (Objects.nonNull(request.getEndTime())) {
            wrapper.eq(UserTaskView::getEndTime, request.getEndTime());
        }

        if (Objects.nonNull(request.getRewardType())) {
            wrapper.eq(UserTaskView::getRewardType, request.getRewardType().getCode());
        }

        if (Objects.nonNull(request.getStatus())) {
            wrapper.eq(UserTaskView::getStatus, request.getStatus().getCode());
        }

        wrapper.orderByDesc(UserTaskView::getId);

        return page(PageUtil.toMybatis(request), wrapper);
    }
}
