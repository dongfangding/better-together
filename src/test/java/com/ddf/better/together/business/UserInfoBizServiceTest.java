package com.ddf.better.together.business;

import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.model.request.EmailRegistryRequest;
import com.ddf.better.together.model.request.EmailRegistryValidateRequest;
import com.ddf.better.together.model.request.SearchUserRequest;
import com.ddf.boot.common.core.util.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/02 16:50
 */
public class UserInfoBizServiceTest extends ApplicationTest {

    @Autowired
    private UserInfoBizService userInfoBizService;


    /**
     * 测试注册信息是否可用
     */
    @Test
    public void testCheckRegistryInfo() {
        final EmailRegistryValidateRequest request = new EmailRegistryValidateRequest();
        request.setEmail("test@qq.com");
        request.setNickname("haha");
        System.out.println(JsonUtil.asString(userInfoBizService.checkRegistryInfo(request)));
    }

    /**
     * 测试注册
     */
    @Test
    public void testRegistry() {
        final EmailRegistryRequest request = new EmailRegistryRequest();
        request.setEmail("123@test.com");
        request.setNickname("add");
        request.setPassword("123456");
        request.setVerifyCode("123456");
        userInfoBizService.registry(request);
    }

    /**
     * 测试查询用户信息
     */
    @Test
    public void testSearchUser() {
        final SearchUserRequest request = new SearchUserRequest();
        request.setKeyword("chen");
        System.out.println(JsonUtil.asString(userInfoBizService.searchUser(request)));
    }

}
