package com.ddf.better.together.model.request;

import com.ddf.better.together.constants.enumeration.UserTaskCycleEnum;
import com.ddf.better.together.constants.enumeration.UserTaskRewardTypeEnum;
import com.ddf.better.together.constants.enumeration.UserTaskViewStatusEnum;
import com.ddf.boot.common.core.model.PageRequest;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>任务视图分页查询</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/21 19:48
 */
@Data
public class UserTaskViewPageRequest implements PageRequest {

    private Integer pageNum;
    private Integer pageSize;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 0 未知  1 一次性任务 2 每日任务 3 每周任务 4 每月任务  5 每年任务
     */
    private UserTaskCycleEnum cycle;

    /**
     * 一次性任务时需要用户自己指定开始时间， 但其他循环任务则系统自动计算
     */
    private LocalDateTime startTime;

    /**
     * 一次性任务时需要用户自己指定任务截止时间， 但其他循环任务则系统自动计算
     */
    private LocalDateTime endTime;

    /**
     * 0 站外奖励，即用文案描述奖励内容，由任务完成者来确认最终任务奖励完成情况	1 积分奖励， 即完成任务有多少积分	2 递进型积分奖励， 递进型的任务奖励，由任务制定者指定奖励等级，每个等级不同的积分奖励，最终由任务制定者或任务监督人来确认奖励等级。
     */
    private UserTaskRewardTypeEnum rewardType;

    /**
     * 0 进行中 1 任务超时（即时间到了，但是任务双方没进行任务操作） 2 任务完成 3 任务失败 4 奖励确认中 5 奖励完成
     */
    private UserTaskViewStatusEnum status;

}
