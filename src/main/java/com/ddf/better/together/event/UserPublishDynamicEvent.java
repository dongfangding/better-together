package com.ddf.better.together.event;

import com.ddf.better.together.model.dto.UserPublishDynamicEventDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * <p>用户发布动态事件</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/06 22:48
 */
public class UserPublishDynamicEvent extends ApplicationEvent {

    @Getter
    private final UserPublishDynamicEventDTO dto;


    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UserPublishDynamicEvent(Object source, UserPublishDynamicEventDTO dto) {
        super(source);
        this.dto = dto;
    }
}
