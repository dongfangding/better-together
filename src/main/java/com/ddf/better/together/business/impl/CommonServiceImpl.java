package com.ddf.better.together.business.impl;

import cn.hutool.core.util.RandomUtil;
import com.ddf.better.together.business.CommonService;
import com.ddf.better.together.constants.MailConst;
import com.ddf.better.together.model.request.EmailCodeRequest;
import com.ddf.better.together.redis.CacheKeys;
import com.ddf.boot.common.core.util.MailUtil;
import java.text.MessageFormat;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>通用接口</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/01 22:34
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class CommonServiceImpl implements CommonService {

    private final StringRedisTemplate stringRedisTemplate;

    private final MailUtil mailUtil;

    /**
     * 生成邮箱验证码
     *
     * @param request
     * @return
     */
    @SneakyThrows
    @Override
    public Boolean generateEmailCode(EmailCodeRequest request) {
        final int randomCode = RandomUtil.randomInt(100000, 1000000);
        stringRedisTemplate.opsForValue()
                .set(CacheKeys.getEmailVerifyCodeKey(request.getEmail()), String.valueOf(randomCode),
                        Duration.ofMinutes(2)
                );

        mailUtil.sendMimeMail(
                new String[] {request.getEmail()}, MailConst.REGISTRY_CODE_EMAIL_SUBJECT,
                MessageFormat.format(MailConst.REGISTRY_CODE_EMAIL, randomCode)
        );

        return Boolean.TRUE;
    }
}
