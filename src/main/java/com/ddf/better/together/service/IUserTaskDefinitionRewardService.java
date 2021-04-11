package com.ddf.better.together.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddf.better.together.model.entity.UserTaskDefinitionReward;
import java.util.List;

/**
 * <p>
 *  服务类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-11
 */
public interface IUserTaskDefinitionRewardService extends IService<UserTaskDefinitionReward> {

    /**
     * 根据任务定义id集合获取他们的奖励
     *
     * @param taskDefinitionIds
     * @return
     */
    List<UserTaskDefinitionReward> listByTaskDefinitionIds(List<Long> taskDefinitionIds);
}
