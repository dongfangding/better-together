package com.ddf.better.together.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ddf.better.together.business.UserInfoBizService;
import com.ddf.better.together.constants.ExceptionCode;
import com.ddf.better.together.convert.mapper.UserInfoMapperConvert;
import com.ddf.better.together.model.dto.UserInfoDTO;
import com.ddf.better.together.model.entity.UserInfo;
import com.ddf.better.together.model.request.EmailRegistryRequest;
import com.ddf.better.together.model.request.EmailRegistryValidateRequest;
import com.ddf.better.together.model.request.SearchUserRequest;
import com.ddf.better.together.model.response.EmailRegistryValidateResponse;
import com.ddf.better.together.redis.CacheKeys;
import com.ddf.better.together.service.IUserInfoService;
import com.ddf.boot.common.authentication.config.AuthenticationProperties;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.boot.common.core.util.SecureUtil;
import com.ddf.common.ids.service.api.IdsApi;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
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
@Service
public class UserInfoBizServiceImpl implements UserInfoBizService {

    private final IUserInfoService userInfoService;

    private final IdsApi idsApi;

    private final StringRedisTemplate stringRedisTemplate;

    private final AuthenticationProperties authenticationProperties;

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
            response.setNicknameResult(userInfoService.countByNickname(request.getNickname()) == 0);
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

        // 验证码白名单忽略处理
        if (Objects.isNull(authenticationProperties) || CollectionUtil.isEmpty(authenticationProperties.getBiz().getWhiteLoginNameList())
                || !authenticationProperties.getBiz().getWhiteLoginNameList().contains(request.getEmail())) {
            // 校验验证码
            final String verifyCode = stringRedisTemplate.opsForValue()
                    .get(CacheKeys.getEmailVerifyCodeKey(request.getEmail()));
            PreconditionUtil.checkArgument(StrUtil.isNotBlank(verifyCode), ExceptionCode.VERIFY_CODE_EXPIRED);
            PreconditionUtil.checkArgument(StrUtil.equals(verifyCode, request.getVerifyCode()), ExceptionCode.VERIFY_CODE_NOT_MATCH);
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(idsApi.getSnowflakeId());
        userInfo.setEmail(request.getEmail());
        userInfo.setNickname(request.getNickname());
        userInfo.setRegisterTime(LocalDateTime.now());
        userInfo.setPassword(SecureUtil.signWithHMac(request.getPassword(), userInfo.getUid()));
        userInfoService.save(userInfo);

        return Boolean.TRUE;
    }

    /**
     * 查询用户
     *
     * @param request
     * @return
     */
    @Override
    public List<UserInfoDTO> searchUser(SearchUserRequest request) {
        return UserInfoMapperConvert.INSTANCE.convert(userInfoService.searchUser(request));
    }
}
