package com.ddf.better.together.controller;

import comm.ddf.common.vps.helper.VpsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户上传的资源表 前端控制器
 * </p>
 * @menu 用户上传的资源表 前端控制器
 * @author mybatis-plus-generator
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/userResource")
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserResourceController {

    private final VpsClient vpsClient;
}

