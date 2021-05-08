package com.ddf.better.together.business.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddf.better.together.business.UserTaskViewBizService;
import com.ddf.better.together.constants.ExceptionCode;
import com.ddf.better.together.convert.mapper.UserTaskViewMapperConvert;
import com.ddf.better.together.model.entity.UserTaskView;
import com.ddf.better.together.model.request.UserTaskViewFinishRequest;
import com.ddf.better.together.model.request.UserTaskViewPageRequest;
import com.ddf.better.together.model.request.UserTaskViewUnFinishRequest;
import com.ddf.better.together.model.response.UserTaskViewResponse;
import com.ddf.better.together.service.IUserTaskViewRewardService;
import com.ddf.better.together.service.IUserTaskViewService;
import com.ddf.boot.common.core.model.PageResult;
import com.ddf.boot.common.core.util.PageUtil;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.boot.common.core.util.UserContextUtil;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>任务视图业务实现类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/21 19:36
 */
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Service
public class UserTaskViewBizServiceImpl implements UserTaskViewBizService {

    private final IUserTaskViewService userTaskViewService;

    private final IUserTaskViewRewardService userTaskViewRewardService;


    /**
     * 当前用户任务视图分页查询
     *
     * @param request
     * @return
     */
    @Override
    public PageResult<UserTaskViewResponse> myTaskPageList(UserTaskViewPageRequest request) {
        request.setUid(UserContextUtil.getUserId());
        final Page<UserTaskView> page = userTaskViewService.pageList(request);
        return PageUtil.convertMybatis(page, UserTaskViewMapperConvert.INSTANCE::convert);
    }

    /**
     * 用户监督的任务分页查询
     *
     * @param request
     * @return
     */
    @Override
    public PageResult<UserTaskViewResponse> mySupervisedTaskPageList(UserTaskViewPageRequest request) {
        request.setSupervisedUid(UserContextUtil.getUserId());
        final Page<UserTaskView> page = userTaskViewService.pageList(request);
        return PageUtil.convertMybatis(page, UserTaskViewMapperConvert.INSTANCE::convert);
    }

    /**
     * 完成任务并指定奖励
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean finishedTask(UserTaskViewFinishRequest request) {
        final UserTaskView taskView = checkCanUpdateTaskStatus(request.getUserTaskViewId());
        userTaskViewService.finishedTask(taskView.getId());
        if (taskView.canReceiveReward()) {
            userTaskViewRewardService.receiveReward(request.getUserTaskViewId(), request.getRewardList());
            // todo 积分处理
        } else {
            userTaskViewRewardService.obtainReward(request.getUserTaskViewId(), request.getRewardList());
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean unfinishedTask(UserTaskViewUnFinishRequest request) {
        final UserTaskView taskView = checkCanUpdateTaskStatus(request.getUserTaskViewId());
        userTaskViewService.finishedTask(taskView.getId());
        return Boolean.TRUE;
    }


    /**
     * 获取并校验是否可修改任务状态
     *
     * @param userTaskViewId
     * @return
     */
    private UserTaskView checkCanUpdateTaskStatus(Long userTaskViewId) {
        final UserTaskView taskView = userTaskViewService.getByUserTaskViewId(userTaskViewId);
        PreconditionUtil.checkArgument(Objects.nonNull(taskView), ExceptionCode.TASK_VIEW_RECORD_NOT_EXIST);

        String currentUid = UserContextUtil.getUserId();
        if (taskView.getSupervised()) {
            PreconditionUtil.checkArgument(
                    Objects.equals(currentUid, taskView.getSupervisedUid()),
                    ExceptionCode.SUPERVISED_TASK_WAIT_SUPERVISED_USER
            );
        } else {
            PreconditionUtil.checkArgument(
                    Objects.equals(currentUid, taskView.getUid()), ExceptionCode.NOT_ALLOW_FINISHED_OTHERS_TASK_VIEW);
        }
        return taskView;
    }
}
