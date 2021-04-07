package com.ddf.better.together.event;

import com.ddf.better.together.model.dto.UserPartnerStatusChangeDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * <p>用户伙伴关系状态变更事件</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/07 23:00
 */
public class UserPartnerStatusChangeEvent extends ApplicationEvent {

    @Getter
    public final UserPartnerStatusChangeDTO dto;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UserPartnerStatusChangeEvent(Object source, UserPartnerStatusChangeDTO dto) {
        super(source);
        this.dto = dto;
    }
}
