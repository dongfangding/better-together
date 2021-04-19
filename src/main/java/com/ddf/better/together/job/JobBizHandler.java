package com.ddf.better.together.job;

import cn.hutool.core.collection.CollectionUtil;
import com.ddf.better.together.constants.enumeration.UserTaskViewStatusEnum;
import com.ddf.better.together.convert.mapper.UserTaskViewMapperConvert;
import com.ddf.better.together.convert.mapper.UserTaskViewRewardMapperConvert;
import com.ddf.better.together.model.entity.UserTaskDefinition;
import com.ddf.better.together.model.entity.UserTaskDefinitionReward;
import com.ddf.better.together.model.entity.UserTaskView;
import com.ddf.better.together.model.entity.UserTaskViewReward;
import com.ddf.better.together.redis.CacheKeys;
import com.ddf.better.together.service.IUserTaskDefinitionRewardService;
import com.ddf.better.together.service.IUserTaskDefinitionService;
import com.ddf.better.together.service.IUserTaskViewRewardService;
import com.ddf.better.together.service.IUserTaskViewService;
import com.ddf.boot.common.core.util.IdsUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>任务业务逻辑执行器</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/11 22:51
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class JobBizHandler {

    private final StringRedisTemplate stringRedisTemplate;

    private final IUserTaskDefinitionService userTaskDefinitionService;

    private final IUserTaskViewService userTaskViewService;

    private final IUserTaskDefinitionRewardService  userTaskDefinitionRewardService;

    private final IUserTaskViewRewardService userTaskViewRewardService;


    /**
     * 扫描需要开始的任务， 创建任务记录
     *
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnT<String> startTask(String param) {
        try {
            XxlJobLogger.log("开始任务扫描..........");
            final long currentTimeMillis = System.currentTimeMillis();
            final Set<String> userTaskDefinitionIdSet = stringRedisTemplate.opsForZSet()
                    .rangeByScore(CacheKeys.getTaskTriggerTimeKey(), 0, currentTimeMillis);
            if (CollectionUtil.isEmpty(userTaskDefinitionIdSet)) {
                XxlJobLogger.log("未扫描到[{}-{}]需要开始的任务..........", currentTimeMillis, currentTimeMillis);
                return ReturnT.SUCCESS;
            }
            // todo 每周每月任务的话，这种情况的任务会在同一时间满足条件，能否错开？
            final List<UserTaskDefinition> definitions = userTaskDefinitionService.listByIds(userTaskDefinitionIdSet);

            List<UserTaskView> taskViewList = new ArrayList<>();
            int maxBatchSize = 1000;
            List<Long> userTaskDefinitionList = new ArrayList<>();
            UserTaskView userTaskView;
            UserTaskViewReward userTaskViewReward;
            List<UserTaskViewReward> taskViewRewardList = new ArrayList<>();
            for (int i = 0, size = definitions.size(); i < size; i++) {
                UserTaskDefinition definition = definitions.get(i);
                userTaskView = UserTaskViewMapperConvert.INSTANCE.convert(definition);
                userTaskView.setStatus(UserTaskViewStatusEnum.ING.getCode());
                userTaskView.setUserTaskViewId(IdsUtil.getNextLongId());
                taskViewList.add(userTaskView);
                userTaskDefinitionList.add(definition.getId());
                if (taskViewList.size() == maxBatchSize || i == definitions.size() - 1) {
                    userTaskViewService.saveBatch(taskViewList);
                    final List<UserTaskDefinitionReward> rewards =
                            userTaskDefinitionRewardService.listByTaskDefinitionIds(userTaskDefinitionList);
                    for (UserTaskDefinitionReward reward : rewards) {
                        userTaskViewReward = UserTaskViewRewardMapperConvert.INSTANCE.convert(reward);
                        userTaskViewReward.setUserTaskViewId(userTaskView.getUserTaskViewId());
                        taskViewRewardList.add(userTaskViewReward);
                    }
                    userTaskViewRewardService.saveBatch(taskViewRewardList);
                    taskViewList.clear();
                    taskViewRewardList.clear();
                    userTaskDefinitionList.clear();
                }
            }
        } catch (Exception e) {
            XxlJobLogger.log("开始任务定时执行失败", e);
        }
        return ReturnT.SUCCESS;
    }
}
