package com.ddf.better.together.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ddf.better.together.business.UserDynamicBizService;
import com.ddf.better.together.constants.TagConst;
import com.ddf.better.together.model.entity.UserDynamic;
import com.ddf.better.together.model.entity.UserResource;
import com.ddf.better.together.model.request.UserDynamicPublishRequest;
import com.ddf.better.together.model.request.UserResourceCreateRequest;
import com.ddf.better.together.service.IUserDynamicService;
import com.ddf.better.together.service.IUserResourceService;
import com.ddf.boot.common.core.util.UserContextUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>用户动态业务实现类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/04 22:54
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class UserDynamicBizServiceImpl implements UserDynamicBizService {

    private final IUserResourceService userResourceService;

    private final IUserDynamicService userDynamicService;

    /**
     * 用户发布动态
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean publishDynamic(UserDynamicPublishRequest request) {
        String currentUid = UserContextUtil.getUserId();

        final UserDynamic dynamic = new UserDynamic();
        dynamic.setContent(request.getContent());
        dynamic.setUid(currentUid);
        dynamic.setViewLevel(request.getViewLevel());
        dynamic.setPublishTime(LocalDateTime.now());
        dynamic.setLocation(request.getLocation());
        if (Objects.nonNull(request.getVideoResource())) {
            final UserResourceCreateRequest resourceCreateRequest = new UserResourceCreateRequest();
            resourceCreateRequest.setUid(currentUid);
            resourceCreateRequest.setVideoResource(request.getVideoResource());
            final List<UserResource> resources = userResourceService.create(resourceCreateRequest);
            dynamic.setVideoResourceId(resources.get(0).getId());
        } else if (CollectionUtil.isNotEmpty(request.getPicResources())) {
            final UserResourceCreateRequest resourceCreateRequest = new UserResourceCreateRequest();
            resourceCreateRequest.setUid(currentUid);
            resourceCreateRequest.setPicResources(request.getPicResources());
            final List<UserResource> resources = userResourceService.create(resourceCreateRequest);
            dynamic.setPicResourceIds(StrUtil.join(
                    TagConst.RESOURCE_SPLIT_CHAR, resources.stream()
                            .map(UserResource::getId)
                            .collect(Collectors.toList())));

        }
        userDynamicService.save(dynamic);

        return Boolean.TRUE;
    }
}
