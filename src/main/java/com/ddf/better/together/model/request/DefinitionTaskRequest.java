package com.ddf.better.together.model.request;

import cn.hutool.core.util.StrUtil;
import com.ddf.better.together.constants.enumeration.UserTaskCycleEnum;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.boot.common.core.validator.constraint.LogicValueValidator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>定义任务请求类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/10 14:25
 */
@Data
public class DefinitionTaskRequest {

    /**
     * 任务名称
     */
    @NotBlank(message = "任务名称不能为空")
    private String name;

    /**
     * 任务描述
     */
    @NotBlank(message = "任务描述不能为空")
    private String description;

    /**
     * 0 未知  1 一次性任务 2 每日任务 3 每周任务 4 每月任务  5 每年任务
     */
    @LogicValueValidator(values = {1, 2, 3, 4, 5}, message = "任务周期类型参数值有误")
    private Integer cycle;

    /**
     * 一次性任务时需要用户自己指定开始时间， 但其他循环任务则系统自动计算
     */
    private LocalDateTime startTime;

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
    @LogicValueValidator(values = {0, 1, 2}, message = "任务奖励类型参数值有误")
    private Integer rewardType;

    @Valid
    @NotEmpty(message = "任务奖励不能为空")
    private List<AddTaskDefinitionRewardRequest> rewardList;

    /**
     * 条惨参数校验
     */
    public void checkRequirements() {
        if (Objects.equals(UserTaskCycleEnum.ONE.getCode(), cycle)) {
            PreconditionUtil.checkBadRequest(Objects.nonNull(startTime), "一次性任务模式下任务开始时间不能为空！");
            PreconditionUtil.checkBadRequest(Objects.nonNull(startTime), "一次性任务模式下任务结束时间不能为空！");
        }
        if (supervised) {
            PreconditionUtil.checkBadRequest(StrUtil.isNotBlank(supervisedUid), "任务需要监督的话，任务监督人不能为空！");
        }
    }
}
