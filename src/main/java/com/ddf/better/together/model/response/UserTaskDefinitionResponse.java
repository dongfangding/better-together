package com.ddf.better.together.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>用户任务定义响应类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/11 21:18
 */
@Data
public class UserTaskDefinitionResponse implements Serializable {

    private static final long serialVersionUID = 1516322558409231083L;

    /**
     * id
     */
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
     * 任务周期类型
     * 0 未知  1 一次性任务 2 每日任务 3 每周任务 4 每月任务  5 每年任务
     */
    private Integer cycle;

    /**
     * 任务周期类型
     */
    private String cycleName;

    /**
     * 一次性任务时需要用户自己指定开始时间， 但其他循环任务则系统自动计算
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 任务最初创建时间，对于周期循环任务，这个值是保持最初的时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 一次性任务时需要用户自己指定任务截止时间， 但其他循环任务则系统自动计算
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    /**
     * 任务奖励类型
     */
    private String rewardTypeName;
}
