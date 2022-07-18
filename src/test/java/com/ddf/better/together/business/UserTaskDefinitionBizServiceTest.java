package com.ddf.better.together.business;

import cn.hutool.core.util.RandomUtil;
import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.constants.enumeration.UserTaskCycleEnum;
import com.ddf.better.together.constants.enumeration.UserTaskRewardTypeEnum;
import com.ddf.better.together.model.request.AddTaskDefinitionRewardRequest;
import com.ddf.better.together.model.request.DefinitionTaskRequest;
import com.ddf.better.together.model.request.UserTaskDefinitionRequest;
import com.ddf.boot.common.authentication.model.UserClaim;
import com.ddf.boot.common.authentication.util.UserContextUtil;
import com.ddf.boot.common.core.util.JsonUtil;
import java.time.LocalDateTime;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/10 23:23
 */
public class UserTaskDefinitionBizServiceTest extends ApplicationTest {

    @Autowired
    private UserTaskDefinitionBizService userTaskDefinitionBizService;

    @Test
    public void testDefinitionTask() {
        UserContextUtil.setUserClaim(new UserClaim("1377909795931066461", "chen"));
        final DefinitionTaskRequest request = new DefinitionTaskRequest();
        String s = RandomUtil.randomString(10);
        request.setName("测试任务" + s);
        request.setDescription("测试任务描述" + s);
        request.setCycle(UserTaskCycleEnum.ONE.getCode());
        request.setStartTime(LocalDateTime.now());
        request.setEndTime(request.getStartTime().plusDays(4));
        request.setActive(false);
        request.setSupervised(true);
        request.setSupervisedUid("111222");
        request.setRewardType(0);
        final AddTaskDefinitionRewardRequest rewardRequest = new AddTaskDefinitionRewardRequest();
        rewardRequest.setDescription("奖励人民币1000元");
        request.setRewardList(Lists.newArrayList(rewardRequest));

        userTaskDefinitionBizService.defineTask(request);

        s = RandomUtil.randomString(10);
        request.setName("测试任务" + s);
        request.setDescription("测试任务描述" + s);
        request.setCycle(UserTaskCycleEnum.EVERY_DAY.getCode());
        rewardRequest.setDescription("奖励人民币2000元");
        request.setRewardList(Lists.newArrayList(rewardRequest));
        userTaskDefinitionBizService.defineTask(request);

        s = RandomUtil.randomString(10);;
        request.setName("测试任务" + s);
        request.setDescription("测试任务描述" + s);
        request.setCycle(UserTaskCycleEnum.EVERY_WEEK.getCode());
        rewardRequest.setDescription("奖励人民币3000元");
        request.setRewardList(Lists.newArrayList(rewardRequest));
        userTaskDefinitionBizService.defineTask(request);

        s = RandomUtil.randomString(10);;
        request.setName("测试任务" + s);
        request.setDescription("测试任务描述" + s);
        request.setRewardType(UserTaskRewardTypeEnum.SCORE.getCode());
        final AddTaskDefinitionRewardRequest rewardRequest2 = new AddTaskDefinitionRewardRequest();
        rewardRequest2.setDescription("这是一个积分奖励");
        rewardRequest2.setRewardScore(200L);
        request.setRewardList(Lists.newArrayList(rewardRequest2));
        userTaskDefinitionBizService.defineTask(request);

        s = RandomUtil.randomString(10);;
        request.setName("测试任务" + s);
        request.setDescription("测试任务描述" + s);
        request.setCycle(UserTaskCycleEnum.EVERY_YEAR.getCode());
        request.setRewardType(UserTaskRewardTypeEnum.LEVEL_SCORE.getCode());
        final AddTaskDefinitionRewardRequest rewardRequest3 = new AddTaskDefinitionRewardRequest();
        rewardRequest3.setDescription("这是一个递进积分奖励");
        rewardRequest3.setRewardScore(500L);

        final AddTaskDefinitionRewardRequest rewardRequest4 = new AddTaskDefinitionRewardRequest();
        rewardRequest4.setDescription("这是一个递进积分奖励");
        rewardRequest4.setRewardScore(1000L);
        request.setRewardList(Lists.newArrayList(rewardRequest3, rewardRequest4));
        userTaskDefinitionBizService.defineTask(request);

    }

    @Test
    public void testMyTaskDefinition() {
        UserContextUtil.setUserClaim(new UserClaim("1377909795931066461", "chen"));

        final UserTaskDefinitionRequest request = new UserTaskDefinitionRequest();
        request.setPageNum(1);
        request.setPageSiz(2);
        System.out.println(JsonUtil.asString(userTaskDefinitionBizService.myTaskDefinition(request)));

    }
}
