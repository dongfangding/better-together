package com.ddf.better.together.service;

import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.model.request.SearchUserDynamicRequest;
import com.ddf.boot.common.core.model.UserClaim;
import com.ddf.boot.common.core.util.JsonUtil;
import com.ddf.boot.common.core.util.UserContextUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/05 21:54
 */
public class IUserDynamicServiceTest extends ApplicationTest {

    @Autowired
    private IUserDynamicService userDynamicService;

    @Test
    public void testSearchUserDynamic() {
        UserContextUtil.setUserClaim(new UserClaim().setUserId("2222"));
        final SearchUserDynamicRequest request = new SearchUserDynamicRequest();
        request.setUid("1377909795931066461");
        request.setPageNum(2);
        request.setPageSize(10);
        System.out.println(JsonUtil.asString(userDynamicService.searchUserDynamic(request)));
    }
}
