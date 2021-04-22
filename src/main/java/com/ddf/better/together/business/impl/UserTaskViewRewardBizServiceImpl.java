package com.ddf.better.together.business.impl;

import com.ddf.better.together.business.UserTaskViewRewardBizService;
import com.ddf.better.together.convert.mapper.UserTaskViewRewardMapperConvert;
import com.ddf.better.together.model.response.UserTaskViewRewardResponse;
import com.ddf.better.together.service.IUserTaskViewRewardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/22 20:58
 */
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Service
public class UserTaskViewRewardBizServiceImpl implements UserTaskViewRewardBizService {

    private final IUserTaskViewRewardService userTaskViewRewardService;

    /**
     * 查看任务奖励
     *
     * @param userTaskViewId
     * @return
     */
    @Override
    public List<UserTaskViewRewardResponse> taskRewards(Long userTaskViewId) {
        return UserTaskViewRewardMapperConvert.INSTANCE.convert(userTaskViewRewardService.getByTaskViewId(userTaskViewId));
    }
}
