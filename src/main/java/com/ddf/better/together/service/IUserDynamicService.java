package com.ddf.better.together.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ddf.better.together.model.dto.UserDynamicDTO;
import com.ddf.better.together.model.entity.UserDynamic;
import com.ddf.better.together.model.request.SearchUserDynamicRequest;

/**
 * <p>
 * 用户动态 服务类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-04
 */
public interface IUserDynamicService extends IService<UserDynamic> {

    /**
     * 分页查询用户动态信息
     *
     * @param request
     * @return
     */
    Page<UserDynamicDTO> searchUserDynamic(SearchUserDynamicRequest request);
}
