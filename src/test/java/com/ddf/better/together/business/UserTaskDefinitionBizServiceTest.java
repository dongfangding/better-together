package com.ddf.better.together.business;

import cn.hutool.core.util.RandomUtil;
import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.constants.enumration.UserTaskCycleEnum;
import com.ddf.better.together.model.request.DefinitionTaskRequest;
import com.ddf.boot.common.core.model.UserClaim;
import com.ddf.boot.common.core.util.UserContextUtil;
import java.time.LocalDateTime;
import org.junit.Test;
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

        userTaskDefinitionBizService.definitionTask(request);

        s = RandomUtil.randomString(10);
        request.setName("测试任务" + s);
        request.setDescription("测试任务描述" + s);
        request.setCycle(UserTaskCycleEnum.EVERY_DAY.getCode());
        userTaskDefinitionBizService.definitionTask(request);

        s = RandomUtil.randomString(10);;
        request.setName("测试任务" + s);
        request.setDescription("测试任务描述" + s);
        request.setCycle(UserTaskCycleEnum.EVERY_WEEK.getCode());
        userTaskDefinitionBizService.definitionTask(request);

        s = RandomUtil.randomString(10);;
        request.setName("测试任务" + s);
        request.setDescription("测试任务描述" + s);
        request.setCycle(UserTaskCycleEnum.EVERY_MONTH.getCode());
        userTaskDefinitionBizService.definitionTask(request);

        s = RandomUtil.randomString(10);;
        request.setName("测试任务" + s);
        request.setDescription("测试任务描述" + s);
        request.setCycle(UserTaskCycleEnum.EVERY_YEAR.getCode());
        userTaskDefinitionBizService.definitionTask(request);


    }
}
