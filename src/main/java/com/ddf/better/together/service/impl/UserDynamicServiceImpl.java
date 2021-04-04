package com.ddf.better.together.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddf.better.together.mapper.UserDynamicMapper;
import com.ddf.better.together.model.entity.UserDynamic;
import com.ddf.better.together.service.IUserDynamicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户动态 服务实现类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-04
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserDynamicServiceImpl extends ServiceImpl<UserDynamicMapper, UserDynamic> implements IUserDynamicService {

}
