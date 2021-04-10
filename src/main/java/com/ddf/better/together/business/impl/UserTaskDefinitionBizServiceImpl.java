package com.ddf.better.together.business.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.ddf.better.together.business.UserTaskDefinitionBizService;
import com.ddf.better.together.constants.ExceptionCode;
import com.ddf.better.together.constants.enumration.UserTaskCycleEnum;
import com.ddf.better.together.convert.mapper.UserTaskDefinitionMapperConvert;
import com.ddf.better.together.model.entity.UserTaskDefinition;
import com.ddf.better.together.model.request.DefinitionTaskRequest;
import com.ddf.better.together.redis.CacheKeys;
import com.ddf.better.together.service.IUserTaskDefinitionService;
import com.ddf.boot.common.core.util.DateUtils;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.boot.common.core.util.UserContextUtil;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/10 15:06
 */
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Service
public class UserTaskDefinitionBizServiceImpl implements UserTaskDefinitionBizService {

    private final IUserTaskDefinitionService userTaskDefinitionService;

    private final StringRedisTemplate stringRedisTemplate;


    /**
     * 定义任务
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean definitionTask(DefinitionTaskRequest request) {
        request.checkRequirements();
        String currentUid = UserContextUtil.getUserId();
        final UserTaskDefinition name = userTaskDefinitionService.getUserTaskByName(currentUid, request.getName());
        PreconditionUtil.checkArgument(Objects.isNull(name), ExceptionCode.TASK_NAME_ALREADY_EXIST);

        final UserTaskDefinition userTaskDefinition = UserTaskDefinitionMapperConvert.INSTANCE.convert(request);

        // 计算任务开始时间并设置任务触发时间
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (Objects.equals(UserTaskCycleEnum.ONE.getCode(), request.getCycle())) {
            startTime = request.getStartTime();
            endTime = request.getEndTime();
        } else if (Objects.equals(UserTaskCycleEnum.EVERY_DAY.getCode(), request.getCycle())) {
            startTime = DateUtil.beginOfDay(DateUtil.tomorrow()).toTimestamp().toLocalDateTime();
            endTime = startTime.plusDays(1L).plusSeconds(-1);
        } else if (Objects.equals(UserTaskCycleEnum.EVERY_WEEK.getCode(), request.getCycle())) {
            // 下周一
            startTime = DateTime.of(DateUtils.nextWeekFirstDay()).toTimestamp().toLocalDateTime();;
            endTime = startTime.plusWeeks(1L).plusSeconds(-1);
        } else if (Objects.equals(UserTaskCycleEnum.EVERY_MONTH.getCode(), request.getCycle())) {
            // 下个月1号
            startTime = DateTime.of(DateUtils.nextMonthFirstDay()).toTimestamp().toLocalDateTime();
            endTime = startTime.plusMonths(1).plusSeconds(-1);
        } else if (Objects.equals(UserTaskCycleEnum.EVERY_YEAR.getCode(), request.getCycle())) {
            // 下年的第一天
            startTime = DateTime.of(DateUtils.nextYearFirstDay()).toTimestamp().toLocalDateTime();
            endTime = startTime.plusYears(1).plusSeconds(-1);
        }

        userTaskDefinition.setCreateTime(LocalDateTime.now());
        userTaskDefinition.setUid(currentUid);
        userTaskDefinition.setStartTime(startTime);
        userTaskDefinition.setEndTime(endTime);
        userTaskDefinitionService.save(userTaskDefinition);

        if (Objects.nonNull(startTime)) {
            stringRedisTemplate.opsForZSet().add(CacheKeys.getTaskTriggerTimeKey(), String.valueOf(userTaskDefinition.getId()),
                    DateUtils.toDefaultMills(startTime));
        }

        return Boolean.TRUE;
    }
}
