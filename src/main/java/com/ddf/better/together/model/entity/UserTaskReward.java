package com.ddf.better.together.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-09
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@TableName("user_task_reward" )
public class UserTaskReward implements Serializable {

    private static final long serialVersionUID = 1L;

        @TableId(value = "id" , type = IdType.AUTO)
        private Long id;

    /**
     * 对应的任务视图id
     */
    private Long userTaskViewId;

    /**
     * 0 站外奖励，即用文案描述奖励内容，由任务完成者来确认最终任务奖励完成情况	1 积分奖励， 即完成任务有多少积分	2 递进型积分奖励， 递进型的任务奖励，由任务制定者指定奖励等级，每个等级不同的积分奖励，最终由任务制定者或任务监督人来确认奖励等级。	
     */
    private Integer type;

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
