package com.ddf.better.together.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddf.better.together.model.entity.UserTaskViewReward;
import java.util.List;

/**
 * <p>
 *  服务类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-11
 */
public interface IUserTaskViewRewardService extends IService<UserTaskViewReward> {

    /**
     * 查询指定任务的奖励
     *
     * @param userTaskViewId
     * @return
     */
    List<UserTaskViewReward> getByTaskViewId(Long userTaskViewId);

    /**
     * 获取奖励
     *
     * @param userTaskViewId
     * @param idList
     */
    void obtainReward(Long userTaskViewId, List<Long> idList);

    /**
     * 领取奖励
     *
     * @param userTaskViewId
     * @param idList
     */
    void receiveReward(Long userTaskViewId, List<Long> idList);

}
