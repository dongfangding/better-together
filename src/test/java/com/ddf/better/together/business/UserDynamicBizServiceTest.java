package com.ddf.better.together.business;

import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.model.dto.ResourceDTO;
import com.ddf.better.together.model.request.SearchUserDynamicRequest;
import com.ddf.better.together.model.request.UserDynamicPublishRequest;
import com.ddf.boot.common.core.model.UserClaim;
import com.ddf.boot.common.core.util.JsonUtil;
import com.ddf.boot.common.core.util.UserContextUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/04 23:08
 */
public class UserDynamicBizServiceTest extends ApplicationTest {

    @Autowired
    private UserDynamicBizService userDynamicBizService;

    /**
     * 测试发布动态
     */
    @Test
    public void testPublishDynamic() {
        UserContextUtil.setUserClaim(new UserClaim().setUserId("1377910295816605698"));
        final UserDynamicPublishRequest request = new UserDynamicPublishRequest();
        request.setContent("啊，好累啊");
        request.setLocation("上海市松江区");
        request.setViewLevel(0);
        request.setVideoResource(new ResourceDTO().setUrl("static/2.jpg"));
        userDynamicBizService.publishDynamic(request);

        request.setVideoResource(null);
        request.setContent("加班什么的最烦了");
        request.setPicResources(Lists.newArrayList(new ResourceDTO().setUrl("static/工作.jpg"),
                new ResourceDTO().setUrl("static/吃饭.jpg")));
        userDynamicBizService.publishDynamic(request);
    }

    /**
     * 测试查询某个用户的动态
     */
    @Test
    public void testSearchUserDynamic() {
        UserContextUtil.setUserClaim(new UserClaim().setUserId("2222"));
        final SearchUserDynamicRequest request = new SearchUserDynamicRequest();
        request.setUid("1377909795931066461");
        request.setPageNum(1);
        request.setPageSize(10);
        System.out.println(JsonUtil.asString(userDynamicBizService.searchUserDynamic(request)));
    }
}
