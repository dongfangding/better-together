package com.ddf.better.together.business;

import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.model.request.UserTaskViewPageRequest;
import com.ddf.boot.common.core.model.UserClaim;
import com.ddf.boot.common.core.util.JsonUtil;
import com.ddf.boot.common.core.util.UserContextUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/21 20:52
 */
public class UserTaskViewBizServiceTest extends ApplicationTest {

    @Autowired
    private UserTaskViewBizService userTaskViewBizService;

    @Test
    public void testTestMyTaskPageList() {
        UserContextUtil.setUserClaim(new UserClaim().setUserId("1377909795931066461"));
        final UserTaskViewPageRequest request = new UserTaskViewPageRequest();
        request.setPageNum(1);
        request.setPageSize(3);
        System.out.println(JsonUtil.asString(userTaskViewBizService.myTaskPageList(request)));
    }
}
