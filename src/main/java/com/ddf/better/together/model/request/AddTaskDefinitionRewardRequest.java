package com.ddf.better.together.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>添加任务定义奖励</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/11 22:25
 */
@Data
public class AddTaskDefinitionRewardRequest {

    /**
     * 奖励描述
     */
    @NotBlank(message = "任务奖励描述不能为空")
    private String description;

    /**
     * 奖励积分，仅积分类型时可用
     */
    @Min(value = 1, message = "奖励积分最小为1")
    private Long rewardScore;
}
