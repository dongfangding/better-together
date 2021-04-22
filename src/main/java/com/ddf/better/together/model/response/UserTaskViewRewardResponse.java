package com.ddf.better.together.model.response;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-11
 */
@Data
@NoArgsConstructor
public class UserTaskViewRewardResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 对应的任务视图id
     */
    private Long userTaskViewId;

    /**
     * 任务奖励	0 站外奖励，即用文案描述奖励内容，由任务完成者来确认最终任务奖励完成情况	1 积分奖励， 即完成任务有多少积分	2 递进型积分奖励， 递进型的任务奖励，由任务制定者指定奖励等级，每个等级不同的积分奖励，最终由任务制定者或任务监督人来确认奖励等级。	
     */
    private Integer type;

    /**
     * 任务奖励类型名称
     */
    private String typeName;

    /**
     * 奖励描述
     */
    private String description;

    /**
     * 奖励积分，仅积分类型时可用
     */
    private Long rewardScore;

    /**
     * 是否获得 0 否 1 是
     */
    private Boolean obtain;


}
