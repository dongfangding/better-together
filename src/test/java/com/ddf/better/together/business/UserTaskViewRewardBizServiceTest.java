package com.ddf.better.together.business;

import com.ddf.better.together.ApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/22 23:14
 */
public class UserTaskViewRewardBizServiceTest extends ApplicationTest {

    @Autowired
    private UserTaskViewRewardBizService userTaskViewRewardBizService;

    @Test
    public void testTaskRewards() {
        System.out.println(userTaskViewRewardBizService.taskRewards(138416915284296089L));
    }
}
