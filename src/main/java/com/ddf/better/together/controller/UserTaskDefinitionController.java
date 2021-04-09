package com.ddf.better.together.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户任务定义表，这里作为用户自己定义的任务资源库，一旦任务开始，则会复制数据到user_task_view中，任务的结算等与这个数据挂钩 用户任务定义表 前端控制器
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/userTaskDefinition")
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserTaskDefinitionController {

}

