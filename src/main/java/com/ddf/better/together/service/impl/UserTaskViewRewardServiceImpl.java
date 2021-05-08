package com.ddf.better.together.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddf.better.together.constants.ExceptionCode;
import com.ddf.better.together.constants.enumeration.UserTaskViewRewardStatusEnum;
import com.ddf.better.together.mapper.UserTaskViewRewardMapper;
import com.ddf.better.together.model.entity.UserTaskViewReward;
import com.ddf.better.together.service.IUserTaskViewRewardService;
import com.ddf.boot.common.core.util.PreconditionUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
public class UserTaskViewRewardServiceImpl extends ServiceImpl<UserTaskViewRewardMapper, UserTaskViewReward> implements IUserTaskViewRewardService {

    /**
     * 查询指定任务的奖励
     *
     * @param userTaskViewId
     * @return
     */
    @Override
    public List<UserTaskViewReward> getByTaskViewId(Long userTaskViewId) {
        final LambdaQueryWrapper<UserTaskViewReward> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserTaskViewReward::getUserTaskViewId, userTaskViewId);
        return list(wrapper);
    }

    /**
     * 获取奖励
     *
     * @param userTaskViewId
     * @param idList
     */
    @Override
    public void obtainReward(Long userTaskViewId, List<Long> idList) {
        final LambdaUpdateWrapper<UserTaskViewReward> wrapper = Wrappers.lambdaUpdate();
        wrapper.in(UserTaskViewReward::getId, idList)
                .eq(UserTaskViewReward::getUserTaskViewId, userTaskViewId)
                .eq(UserTaskViewReward::getStatus, UserTaskViewRewardStatusEnum.NOT_OBTAIN.getCode())
                .set(UserTaskViewReward::getStatus, UserTaskViewRewardStatusEnum.OBTAINED.getCode());
        PreconditionUtil.checkArgument(
                Objects.equals(Boolean.TRUE, update(wrapper)), ExceptionCode.UPDATE_OBTAIN_REWARD_FAILURE);
    }

    /**
     * 领取奖励
     *
     * @param userTaskViewId
     * @param idList
     */
    @Override
    public void receiveReward(Long userTaskViewId, List<Long> idList) {
        final LambdaUpdateWrapper<UserTaskViewReward> wrapper = Wrappers.lambdaUpdate();
        wrapper.in(UserTaskViewReward::getId, idList)
                .eq(UserTaskViewReward::getUserTaskViewId, userTaskViewId)
                // 这里没有使用乐观锁条件状态必须是已获取奖励的原因是有些奖励可以直接从未获取更新到已领取
                .set(UserTaskViewReward::getStatus, UserTaskViewRewardStatusEnum.RECEIVED.getCode())
                .set(UserTaskViewReward::getReceiveTime, LocalDateTime.now());
        PreconditionUtil.checkArgument(
                Objects.equals(Boolean.TRUE, update(wrapper)), ExceptionCode.RECEIVE_REWARD_FAILURE);
    }
}
