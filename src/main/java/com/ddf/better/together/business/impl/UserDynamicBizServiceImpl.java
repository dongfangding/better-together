package com.ddf.better.together.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddf.better.together.business.UserDynamicBizService;
import com.ddf.better.together.constants.TagConst;
import com.ddf.better.together.model.dto.ResourceDTO;
import com.ddf.better.together.model.dto.UserDynamicDTO;
import com.ddf.better.together.model.entity.UserDynamic;
import com.ddf.better.together.model.entity.UserResource;
import com.ddf.better.together.model.request.SearchUserDynamicRequest;
import com.ddf.better.together.model.request.UserDynamicPublishRequest;
import com.ddf.better.together.model.request.UserResourceCreateRequest;
import com.ddf.better.together.model.response.UserDynamicResponse;
import com.ddf.better.together.service.IUserDynamicService;
import com.ddf.better.together.service.IUserResourceService;
import com.ddf.boot.common.core.model.PageResult;
import com.ddf.boot.common.core.util.PageUtil;
import com.ddf.boot.common.core.util.UserContextUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
            dynamic.setVideoResourceId(resources.get(0)
                    .getId());
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

    /**
     * 查询用户动态，如果是自己，则可以查看全部，如果非自己，则只能查看公开的动态
     *
     * @param request
     * @return
     */
    @Override
    public PageResult<UserDynamicResponse> searchUserDynamic(SearchUserDynamicRequest request) {
        final Page<UserDynamicDTO> page = userDynamicService.searchUserDynamic(request);
        if (Objects.isNull(page) || CollectionUtil.isEmpty(page.getRecords())) {
            return PageUtil.empty();
        }
        final List<UserDynamicDTO> records = page.getRecords();
        List<Long> resourceIds = new ArrayList<>();
        // 收集资源id集合
        for (UserDynamicDTO record : records) {
            if (StrUtil.isNotBlank(record.getPicResourceIds())) {
                resourceIds.addAll(Arrays.stream(record.getPicResourceIds()
                        .split(TagConst.RESOURCE_SPLIT_CHAR))
                        .map(Long::parseLong)
                        .collect(Collectors.toList()));
            } else if (Objects.nonNull(record.getVideoResourceId())) {
                resourceIds.add(record.getVideoResourceId());
            }
        }
        final Map<Long, UserResource> userResourceMap = userResourceService.getUserResourceMap(resourceIds);

        return PageUtil.convertMybatis(page, (list) -> {
            List<UserDynamicResponse> responseList = new ArrayList<>(list.size());
            for (UserDynamicDTO dto : list) {
                final UserDynamicResponse response = new UserDynamicResponse();
                response.setUid(dto.getUid());
                response.setNickname(dto.getNickname());
                response.setAvatarUrl(dto.getAvatarUrl());
                response.setPartnerNameRemark("");
                response.setContent(dto.getContent());
                response.setLocation(dto.getLocation());
                response.setPublishTime(dto.getPublishTime());
                if (CollectionUtil.isNotEmpty(userResourceMap)) {
                    if (StrUtil.isNotBlank(dto.getPicResourceIds())) {
                        final String[] split = dto.getPicResourceIds()
                                .split(TagConst.RESOURCE_SPLIT_CHAR);
                        for (String s : split) {
                            response.getPicResources()
                                    .add(new ResourceDTO().setUrl(userResourceMap.get(Long.parseLong(s))
                                            .getPicUrl())
                                            .setExtra(userResourceMap.get(Long.parseLong(s))
                                                    .getExtra()));
                        }
                    } else if (Objects.nonNull(dto.getVideoResourceId())) {
                        response.setVideoResource(new ResourceDTO().setUrl(userResourceMap.get(dto.getVideoResourceId())
                                .getVideoUrl())
                                .setExtra(userResourceMap.get(dto.getVideoResourceId())
                                        .getExtra()));
                    }
                }
                responseList.add(response);
            }
            return responseList;
        });
    }
}
