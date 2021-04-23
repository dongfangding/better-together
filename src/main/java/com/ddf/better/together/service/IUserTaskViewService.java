package com.ddf.better.together.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ddf.better.together.model.entity.UserTaskView;
import com.ddf.better.together.model.request.UserTaskViewPageRequest;

/**
 * <p>
 * 用户任务视图表 服务类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-09
 */
public interface IUserTaskViewService extends IService<UserTaskView> {

    /**
     * 根据userTaskViewId查找记录
     *
     * @param userTaskViewId
     * @return
     */
    UserTaskView getByUserTaskViewId(Long userTaskViewId);


    /**
     * 用户任务视图分页查询
     *
     * @param request
     * @return
     */
    Page<UserTaskView> pageList(UserTaskViewPageRequest request);

    /**
     * 完成任务
     *
     * @param id
     * @return
     */
    void finishedTask(Long id);

}
