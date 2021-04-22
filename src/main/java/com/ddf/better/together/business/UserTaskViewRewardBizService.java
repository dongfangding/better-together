package com.ddf.better.together.business;

import com.ddf.better.together.model.response.UserTaskViewRewardResponse;
import java.util.List;

/**
 * <p>用户任务奖励业务类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/22 20:58
 */
public interface UserTaskViewRewardBizService {

    /**
     * 查看任务奖励
     *
     * @param userTaskViewId
     * @return
     */
    List<UserTaskViewRewardResponse> taskRewards(Long userTaskViewId);
}
