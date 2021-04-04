package com.ddf.better.together.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddf.better.together.model.entity.UserResource;
import com.ddf.better.together.model.request.UserResourceCreateRequest;
import java.util.List;

/**
 * <p>
 * 用户上传的资源表 服务类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-04
 */
public interface IUserResourceService extends IService<UserResource> {

    /**
     * 创建资源
     *
     * @param request
     * @return
     */
    List<UserResource> create(UserResourceCreateRequest request);

}
