package com.ddf.better.together.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户动态收件箱，推模式下使用,注意发布人自己的动态也会推送一条给自己的数据 前端控制器
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-06
 */
@RestController
@RequestMapping("/userDynamicReceiveBox")
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserDynamicReceiveBoxController {

}

