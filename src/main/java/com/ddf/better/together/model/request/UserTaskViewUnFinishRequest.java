package com.ddf.better.together.model.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>未完成任务请求参数类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/23 23:08
 */
@Data
public class UserTaskViewUnFinishRequest {
    /**
     * 任务视图id
     */
    @NotNull(message = "任务视图id不能为空")
    private Long userTaskViewId;
}
