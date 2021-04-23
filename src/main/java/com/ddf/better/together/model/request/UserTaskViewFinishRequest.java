package com.ddf.better.together.model.request;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>完成任务请求参数类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/23 23:08
 */
@Data
public class UserTaskViewFinishRequest {

    /**
     * 任务视图id
     */
    @NotNull(message = "任务视图id不能为空")
    private Long userTaskViewId;

    /**
     * 获取的奖励列表，目前一般应该为一个，为后续支持，参数支持多个，业务上使用需注意，这里不会去校验应不应该获取多份奖励
     */
    @NotEmpty(message = "完成任务时奖励不能为空")
    private List<Long> rewardList;
}
