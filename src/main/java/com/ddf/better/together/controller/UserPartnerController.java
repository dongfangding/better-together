package com.ddf.better.together.controller;

import com.ddf.better.together.business.UserPartnerApplyBizService;
import com.ddf.better.together.model.request.AgreePartnerApplyRequest;
import com.ddf.better.together.model.request.RefusePartnerApplyRequest;
import com.ddf.better.together.model.request.UserPartnerApplyRequest;
import com.ddf.better.together.model.response.UserPartnerApplyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户伙伴关系表 前端控制器
 * </p>
 * @menu 用户伙伴关系表 前端控制器
 * @author mybatis-plus-generator
 * @since 2021-04-01
 */
@RestController
@RequestMapping("/userPartner")
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserPartnerController {

    private final UserPartnerApplyBizService userPartnerApplyBizService;

    /**
     * 伙伴关系申请
     *
     * @param request
     * @return
     */
    @PostMapping("apply")
    public UserPartnerApplyResponse apply(@RequestBody @Validated UserPartnerApplyRequest request) {
        return userPartnerApplyBizService.apply(request);
    }

    /**
     * 同意成为伙伴
     *
     * @param request
     * @return
     */
    @PostMapping("agree")
    public Boolean agree(@RequestBody @Validated AgreePartnerApplyRequest request) {
        return userPartnerApplyBizService.agree(request);
    }

    /**
     * 拒绝成为伙伴
     *
     * @param request
     * @return
     */
    @PostMapping("refuse")
    public Boolean refuse(@RequestBody @Validated RefusePartnerApplyRequest request) {
        return userPartnerApplyBizService.refuse(request);
    }

}

