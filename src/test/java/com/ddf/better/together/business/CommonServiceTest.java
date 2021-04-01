package com.ddf.better.together.business;

import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.model.request.EmailCodeRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/01 22:49
 */
public class CommonServiceTest extends ApplicationTest {

    @Autowired
    private CommonService commonService;

    @Test
    public void testRegistryEmailCode() {
        String email = "dongfang.ding1@gmail.com";
        EmailCodeRequest request = new EmailCodeRequest();
        request.setEmail(email);
        commonService.generateEmailCode(request);
    }
}
