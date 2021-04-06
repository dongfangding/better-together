package com.ddf.better.together.model.dto;

import com.ddf.better.together.constants.enumration.UserDynamicViewLevelEnum;
import com.ddf.boot.common.core.validator.constraint.LogicValueValidator;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>用户发布动态事件类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/06 22:49
 */
@Data
@Accessors(chain = true)
public class UserPublishDynamicEventDTO {

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 动态id
     */
    private Long userDynamicId;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 动态显示级别 0 好友可见 1 仅自己可见
     * @see UserDynamicViewLevelEnum
     */
    @LogicValueValidator(values = {0, 1})
    private Integer viewLevel;
}
