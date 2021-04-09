package com.ddf.better.together.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * 用户任务定义表，这里作为用户自己定义的任务资源库，一旦任务开始，则会复制数据到user_task_view中，任务的结算等与这个数据挂钩 用户任务定义表
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-09
 */
@Data
@TableName("user_task_definition" )
public class UserTaskDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

        @TableId(value = "id" , type = IdType.AUTO)
        private Long id;

    /**
     * 任务创建人
     */
    private String uid;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 0 未知  1 一次性任务 2 每日任务 3 每周任务 4 每月任务  5 每年任务
     */
    private Integer cycle;

    /**
     * 一次性任务时需要用户自己指定开始时间， 但其他循环任务则系统自动计算
     */
    private LocalDateTime startTime;

    /**
     * 任务最初创建时间，对于周期循环任务，这个值是保持最初的时间
     */
    private LocalDateTime createTime;

    /**
     * 一次性任务时需要用户自己指定任务截止时间， 但其他循环任务则系统自动计算
     */
    private LocalDateTime endTime;

    /**
     * 任务是否激活 0 否 1 是 对于进行中的任务，不可停止，如果是循环任务，则下个周期任务才会停止
     */
    private Boolean active;

    /**
     * 任务是否需要监督，如果需要监督，则指定好友中的一个人作为监督人。这样任务的完成情况，自己只能申请任务完成，只有监督人确认后任务才算完成。当然监督人也可以单方面直接确认任务完成，不需要申请。
     */
    private Boolean supervised;

    /**
     * 任务监督人
     */
    private String supervisedUid;

    /**
     * 0 站外奖励，即用文案描述奖励内容，由任务完成者来确认最终任务奖励完成情况	1 积分奖励， 即完成任务有多少积分	2 递进型积分奖励， 递进型的任务奖励，由任务制定者指定奖励等级，每个等级不同的积分奖励，最终由任务制定者或任务监督人来确认奖励等级。	
     */
    private Integer rewardType;


}
