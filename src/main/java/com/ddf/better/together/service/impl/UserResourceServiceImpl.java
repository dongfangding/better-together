package com.ddf.better.together.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddf.better.together.constants.ExceptionCode;
import com.ddf.better.together.mapper.UserResourceMapper;
import com.ddf.better.together.model.dto.ResourceDTO;
import com.ddf.better.together.model.entity.UserResource;
import com.ddf.better.together.model.request.UserResourceCreateRequest;
import com.ddf.better.together.service.IUserResourceService;
import com.ddf.boot.common.core.exception200.BusinessException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户上传的资源表 服务实现类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-04
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserResourceServiceImpl extends ServiceImpl<UserResourceMapper, UserResource> implements IUserResourceService {

    /**
     * 创建资源
     *
     * @param request
     * @return
     */
    @Override
    public List<UserResource> create(UserResourceCreateRequest request) {
        if (CollectionUtil.isEmpty(request.getPicResources()) && Objects.isNull(request.getVideoResource())) {
            throw new BusinessException(ExceptionCode.RESOURCE_PROP_ERROR);
        }
        final LocalDateTime now = LocalDateTime.now();
        if (Objects.nonNull(request.getVideoResource())) {
            final UserResource resource = new UserResource();
            resource.setUid(request.getUid());
            resource.setVideoUrl(request.getVideoResource().getUrl());
            resource.setExtra(request.getVideoResource().getExtra());
            resource.setCreateDate(now);
            save(resource);
            return Collections.singletonList(resource);
        } else if (CollectionUtil.isNotEmpty(request.getPicResources())) {
            List<UserResource> saveList = new ArrayList<>(request.getPicResources().size());
            for (ResourceDTO resource : request.getPicResources()) {
                final UserResource temp = new UserResource();
                temp.setUid(request.getUid());
                temp.setPicUrl(resource.getUrl());
                temp.setExtra(resource.getExtra());
                temp.setCreateDate(now);
                saveList.add(temp);
            }
            saveBatch(saveList);
            return saveList;
        }
        throw new BusinessException(ExceptionCode.RESOURCE_PROP_ERROR);
    }

    /**
     * 获取用户资源map
     * key 资源id
     * value 资源对象
     *
     * @param resourceIds
     * @return
     */
    @Override
    public Map<Long, UserResource> getUserResourceMap(List<Long> resourceIds) {
        if (CollectionUtil.isEmpty(resourceIds)) {
            return Collections.emptyMap();
        }
        final List<UserResource> resources = listByIds(resourceIds);
        return resources.stream().collect(Collectors.toMap(UserResource::getId, val -> val));
    }
}
