package com.ddf.better.together.controller;

import com.ddf.better.together.business.CommonService;
import com.ddf.better.together.business.UserInfoBizService;
import com.ddf.better.together.model.dto.UserInfoDTO;
import com.ddf.better.together.model.request.EmailCodeRequest;
import com.ddf.better.together.model.request.EmailRegistryRequest;
import com.ddf.better.together.model.request.EmailRegistryValidateRequest;
import com.ddf.better.together.model.request.SearchUserRequest;
import com.ddf.better.together.model.response.EmailRegistryValidateResponse;
import com.ddf.common.captcha.helper.CaptchaHelper;
import com.ddf.common.captcha.model.CaptchaRequest;
import com.ddf.common.captcha.model.CaptchaResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户基本信息表 前端控制器
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-01
 */
@RestController
@RequestMapping("/userInfo")
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserInfoController {

    private final CommonService commonService;

    private final UserInfoBizService userInfoBizService;

    private final CaptchaHelper captchaHelper;

    /**
     * 生成验证码
     *
     * @param request
     * @return
     */
    @PostMapping("/generateCaptcha")
    public CaptchaResult generateCaptcha(@RequestBody @Validated CaptchaRequest request) {
        return captchaHelper.generate(request);
    }

    /**
     * 生成邮箱验证码
     *
     * @param request
     * @return
     */
    @PostMapping("generateEmailCode")
    public Boolean generateEmailCode(@RequestBody @Validated EmailCodeRequest request) {
        return commonService.generateEmailCode(request);
    }

    /**
     * 校验注册信息是否可用
     *
     * @param request
     * @return
     */
    @PostMapping("checkRegistryInfo")
    public EmailRegistryValidateResponse checkRegistryInfo(@RequestBody @Validated EmailRegistryValidateRequest request) {
        return userInfoBizService.checkRegistryInfo(request);
    }

    /**
     * 邮箱注册
     *
     * @param request
     * @return
     */
    @PostMapping("registry")
    public Boolean registry(@RequestBody @Validated EmailRegistryRequest request) {
        return userInfoBizService.registry(request);
    }

    /**
     * 查询用户
     *
     * @param request
     * @return
     */
    @GetMapping("searchUser")
    public List<UserInfoDTO> searchUser(@Validated SearchUserRequest request) {
        return userInfoBizService.searchUser(request);
    }

}

