package com.ddf.better.together.service;

import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.model.dto.ResourceDTO;
import com.ddf.better.together.model.request.UserResourceCreateRequest;
import com.ddf.boot.common.core.util.JsonUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/04 22:21
 */
public class UserResourceServiceTest extends ApplicationTest {

    @Autowired
    private IUserResourceService userResourceService;


    @Test
    public void testCreate() {
        final UserResourceCreateRequest request = new UserResourceCreateRequest();
        request.setUid("1");
        request.setVideoResource(new ResourceDTO().setUrl("www.baidu.com"));
        System.out.println(JsonUtil.asString(userResourceService.create(request)));


        request.setVideoResource(null);
        request.setPicResources(Lists.newArrayList(new ResourceDTO().setUrl("www.google.com").setExtra("{\"haha\": 1}"),
                new ResourceDTO().setUrl("static/test/1.jpg")));
        System.out.println(JsonUtil.asString(userResourceService.create(request)));

    }
}
