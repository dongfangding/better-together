package com.ddf.better.together.business.impl;

import cn.hutool.core.util.StrUtil;
import com.ddf.better.together.business.UserInfoBizService;
import com.ddf.better.together.constants.ExceptionCode;
import com.ddf.better.together.model.entity.UserInfo;
import com.ddf.better.together.model.request.EmailRegistryRequest;
import com.ddf.better.together.model.request.EmailRegistryValidateRequest;
import com.ddf.better.together.model.response.EmailRegistryValidateResponse;
import com.ddf.better.together.redis.CacheKeys;
import com.ddf.better.together.service.IUserInfoService;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.boot.common.core.util.SecureUtil;
import com.ddf.boot.common.ids.helper.SnowflakeServiceHelper;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>用户业务处理类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/01 23:08
 */
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserInfoBizServiceImpl implements UserInfoBizService {

    private final IUserInfoService userInfoService;

    private final SnowflakeServiceHelper snowflakeServiceHelper;

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 校验注册信息是否可用
     *
     * @param request
     * @return
     */
    @Override
    public EmailRegistryValidateResponse checkRegistryInfo(EmailRegistryValidateRequest request) {
        final EmailRegistryValidateResponse response = new EmailRegistryValidateResponse();
        if (StrUtil.isNotBlank(request.getEmail())) {
            response.setEmailResult(userInfoService.countByEmail(request.getEmail()) == 0);
        }

        if (StrUtil.isNotBlank(request.getNickname())) {
            response.setEmailResult(userInfoService.countByEmail(request.getEmail()) == 0);
        }
        return response;
    }

    /**
     * 邮箱注册
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean registry(EmailRegistryRequest request) {
        // 唯一性校验
        PreconditionUtil.checkArgument(userInfoService.countByEmail(request.getEmail()) == 0, ExceptionCode.EMAIL_USED);
        PreconditionUtil.checkArgument(
                userInfoService.countByNickname(request.getNickname()) == 0, ExceptionCode.NICKNAME_USED);

        // 校验验证码
        final String verifyCode = stringRedisTemplate.opsForValue()
                .get(CacheKeys.getEmailVerifyCodeKey(request.getEmail()));
        PreconditionUtil.checkArgument(StrUtil.isNotBlank(verifyCode), ExceptionCode.VERIFY_CODE_EXPIRED);
        PreconditionUtil.checkArgument(StrUtil.equals(verifyCode, request.getVerifyCode()), ExceptionCode.VERIFY_CODE_NOT_MATCH);


        UserInfo userInfo = new UserInfo();
        userInfo.setUid(snowflakeServiceHelper.getStringId());
        userInfo.setEmail(request.getEmail());
        userInfo.setNickname(request.getNickname());
        userInfo.setRegisteTime(LocalDateTime.now());
        userInfo.setPassword(SecureUtil.signWithHMac(request.getPassword(), userInfo.getUid()));
        userInfoService.save(userInfo);

        return Boolean.TRUE;
    }
}
