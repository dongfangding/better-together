package com.ddf.better.together.business.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddf.better.together.business.UserTaskViewBizService;
import com.ddf.better.together.convert.mapper.UserTaskViewMapperConvert;
import com.ddf.better.together.model.entity.UserTaskView;
import com.ddf.better.together.model.request.UserTaskViewPageRequest;
import com.ddf.better.together.model.response.UserTaskViewResponse;
import com.ddf.better.together.service.IUserTaskViewRewardService;
import com.ddf.better.together.service.IUserTaskViewService;
import com.ddf.boot.common.core.model.PageResult;
import com.ddf.boot.common.core.util.PageUtil;
import com.ddf.boot.common.core.util.UserContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>任务视图业务实现类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/21 19:36
 */
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Service
public class UserTaskViewBizServiceImpl implements UserTaskViewBizService {

    private final IUserTaskViewService userTaskViewService;

    private final IUserTaskViewRewardService userTaskViewRewardService;


    /**
     * 当前用户任务视图分页查询
     *
     * @param request
     * @return
     */
    @Override
    public PageResult<UserTaskViewResponse> myTaskPageList(UserTaskViewPageRequest request) {
        request.setUid(UserContextUtil.getUserId());
        final Page<UserTaskView> page = userTaskViewService.pageList(request);
        return PageUtil.convertMybatis(page, UserTaskViewMapperConvert.INSTANCE::convert);
    }
}
