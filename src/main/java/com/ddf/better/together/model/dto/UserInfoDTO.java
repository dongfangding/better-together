package com.ddf.better.together.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;

/**
 * <p>用户对象信息</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/02 11:54
 */
public class UserInfoDTO {

    private Long id;

    /**
     * 用户uid， 内部全部使用这个来关联
     */
    private String uid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱，当前系统的登陆方式
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTime;
}
