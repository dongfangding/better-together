package com.ddf.better.together.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddf.better.together.mapper.UserTaskDefinitionRewardMapper;
import com.ddf.better.together.model.entity.UserTaskDefinitionReward;
import com.ddf.better.together.service.IUserTaskDefinitionRewardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-11
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserTaskDefinitionRewardServiceImpl extends ServiceImpl<UserTaskDefinitionRewardMapper, UserTaskDefinitionReward> implements IUserTaskDefinitionRewardService {

    /**
     * 根据任务定义id集合获取他们的奖励
     *
     * @param taskDefinitionIds
     * @return
     */
    @Override
    public List<UserTaskDefinitionReward> listByTaskDefinitionIds(List<Long> taskDefinitionIds) {
        final LambdaQueryWrapper<UserTaskDefinitionReward> wrapper = Wrappers.lambdaQuery();
        wrapper.in(UserTaskDefinitionReward::getUserTaskDefinitionId, taskDefinitionIds);
        return list(wrapper);
    }
}
