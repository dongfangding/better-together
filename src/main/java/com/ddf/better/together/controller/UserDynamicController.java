package com.ddf.better.together.controller;

import com.ddf.better.together.business.UserDynamicBizService;
import com.ddf.better.together.model.request.SearchUserDynamicRequest;
import com.ddf.better.together.model.request.UserDynamicPublishRequest;
import com.ddf.better.together.model.response.UserDynamicResponse;
import com.ddf.boot.common.core.model.PageResult;
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
 * 用户动态 前端控制器
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/userDynamic")
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserDynamicController {

    private final UserDynamicBizService userDynamicBizService;


    /**
     * 用户发布动态
     * @param request
     * @return
     */
    @PostMapping("publishDynamic")
    public Boolean publishDynamic(@RequestBody @Validated UserDynamicPublishRequest request) {
        return userDynamicBizService.publishDynamic(request);
    }

    /**
     * 查询用户动态，如果是自己，则可以查看全部，如果非自己，则只能查看公开的动态
     *
     * @param request
     * @return
     */
    @GetMapping("searchUserDynamic")
    public PageResult<UserDynamicResponse> searchUserDynamic(@Validated SearchUserDynamicRequest request) {
        return userDynamicBizService.searchUserDynamic(request);
    }
}

