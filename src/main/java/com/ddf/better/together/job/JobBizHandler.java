package com.ddf.better.together.job;

import cn.hutool.core.collection.CollectionUtil;
import com.ddf.better.together.constants.enumeration.UserTaskViewStatusEnum;
import com.ddf.better.together.convert.mapper.UserTaskViewMapperConvert;
import com.ddf.better.together.model.entity.UserTaskDefinition;
import com.ddf.better.together.model.entity.UserTaskDefinitionReward;
import com.ddf.better.together.model.entity.UserTaskView;
import com.ddf.better.together.redis.CacheKeys;
import com.ddf.better.together.service.IUserTaskDefinitionRewardService;
import com.ddf.better.together.service.IUserTaskDefinitionService;
import com.ddf.better.together.service.IUserTaskViewRewardService;
import com.ddf.better.together.service.IUserTaskViewService;
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
                    .rangeByScore(CacheKeys.getTaskTriggerTimeKey(), currentTimeMillis, currentTimeMillis);
            if (CollectionUtil.isEmpty(userTaskDefinitionIdSet)) {
                XxlJobLogger.log("未扫描到[{}-{}]需要开始的任务..........", currentTimeMillis, currentTimeMillis);
                return ReturnT.SUCCESS;
            }
            // todo 每周任务的话，这其实会把系统所有的周任务都查询出来，考虑错开时间，或者避免查询报错，因为可能会sql超长
            final List<UserTaskDefinition> definitions = userTaskDefinitionService.listByIds(userTaskDefinitionIdSet);

            List<UserTaskView> taskViewList = new ArrayList<>();
            int maxBatchSize = 1000;
            List<Long> userTaskDefinitionList = new ArrayList<>();
            for (UserTaskDefinition definition : definitions) {
                final UserTaskView userTaskView = UserTaskViewMapperConvert.INSTANCE.convert(definition);
                userTaskView.setStatus(UserTaskViewStatusEnum.ING.getCode());
                taskViewList.add(userTaskView);
                userTaskDefinitionList.add(definition.getId());
                if (taskViewList.size() == 1000) {
                    userTaskViewService.saveBatch(taskViewList);

                    final List<UserTaskDefinitionReward> rewards =
                            userTaskDefinitionRewardService.listByTaskDefinitionIds(userTaskDefinitionList);
                    for (UserTaskDefinitionReward reward : rewards) {

                    }
                    taskViewList.clear();
                }
            }
            if (CollectionUtil.isNotEmpty(taskViewList)) {
                userTaskViewService.saveBatch(taskViewList);
            }
            // 没有提前生成id，批量提前绑定不太好搞
            if (CollectionUtil.isNotEmpty(userTaskDefinitionList)) {

            }
        } catch (Exception e) {
            XxlJobLogger.log("开始任务定时执行失败", e);
        }
        return ReturnT.SUCCESS;
    }
}
