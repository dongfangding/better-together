package com.ddf.better.together.controller;

import com.ddf.better.together.business.UserTaskDefinitionBizService;
import com.ddf.better.together.business.UserTaskViewBizService;
import com.ddf.better.together.business.UserTaskViewRewardBizService;
import com.ddf.better.together.model.request.DefinitionTaskRequest;
import com.ddf.better.together.model.request.UserTaskDefinitionRequest;
import com.ddf.better.together.model.request.UserTaskViewFinishRequest;
import com.ddf.better.together.model.request.UserTaskViewPageRequest;
import com.ddf.better.together.model.request.UserTaskViewUnFinishRequest;
import com.ddf.better.together.model.response.UserTaskDefinitionResponse;
import com.ddf.better.together.model.response.UserTaskViewResponse;
import com.ddf.better.together.model.response.UserTaskViewRewardResponse;
import com.ddf.boot.common.core.model.PageResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户任务定义表，这里作为用户自己定义的任务资源库，一旦任务开始，则会复制数据到user_task_view中，任务的结算等与这个数据挂钩 用户任务定义表 前端控制器
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/userTaskDefinition")
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserTaskController {

    private final UserTaskDefinitionBizService userTaskDefinitionBizService;

    private final UserTaskViewBizService userTaskViewBizService;

    private final UserTaskViewRewardBizService userTaskViewRewardBizService;

    /**
     * 定义任务
     *
     * @param request
     * @return
     */
    @PostMapping("defineTask")
    public Boolean defineTask(@RequestBody @Validated DefinitionTaskRequest request) {
        return userTaskDefinitionBizService.defineTask(request);
    }

    /**
     * 查询登陆用户定义的任务
     *
     * @param request
     * @return
     */
    @PostMapping("myTaskDefinition")
    public PageResult<UserTaskDefinitionResponse> myTaskDefinition(@RequestBody @Validated UserTaskDefinitionRequest request) {
        return userTaskDefinitionBizService.myTaskDefinition(request);
    }


    /**
     * 用户待完成任务视图分页查询
     *
     * @param request
     * @return
     */
    @PostMapping("myTaskPageList")
    public PageResult<UserTaskViewResponse> myTaskPageList(@RequestBody @Validated UserTaskViewPageRequest request) {
        return userTaskViewBizService.myTaskPageList(request);
    }


    /**
     * 用户监督的任务分页查询
     *
     * @param request
     * @return
     */
    @PostMapping("mySupervisedTaskPageList")
    public PageResult<UserTaskViewResponse> mySupervisedTaskPageList(@RequestBody @Validated UserTaskViewPageRequest request) {
        return userTaskViewBizService.mySupervisedTaskPageList(request);
    }

    /**
     * 完成任务并指定奖励
     *
     * @param request
     * @return
     */
    @PostMapping("finishedTask")
    public Boolean finishedTask(@RequestBody @Validated UserTaskViewFinishRequest request) {
        return userTaskViewBizService.finishedTask(request);
    }

    /**
     * 标识任务未完成
     *
     * @param request
     * @return
     */
    @PostMapping("unfinishedTask")
    public Boolean unfinishedTask(@RequestBody @Validated UserTaskViewUnFinishRequest request) {
        return userTaskViewBizService.unfinishedTask(request);
    }

    /**
     * 查看任务奖励
     *
     * @param userTaskViewId
     * @return
     */
    @GetMapping("taskRewards")
    public List<UserTaskViewRewardResponse> taskRewards(Long userTaskViewId) {
        return userTaskViewRewardBizService.taskRewards(userTaskViewId);
    }

}

