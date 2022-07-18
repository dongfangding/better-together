package com.ddf.better.together.service;

import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.model.query.SearchUserDynamicQuery;
import com.ddf.boot.common.authentication.model.UserClaim;
import com.ddf.boot.common.authentication.util.UserContextUtil;
import com.ddf.boot.common.core.util.JsonUtil;
import org.junit.jupiter.api.Test;
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
        final SearchUserDynamicQuery query = new SearchUserDynamicQuery();
        query.setUid("1377909795931066461");
        query.setPageNum(2);
        query.setPageSize(10);
        System.out.println(JsonUtil.asString(userDynamicService.searchUserDynamic(query)));
    }
}
