package com.ddf.better.together.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * 用户伙伴申请记录表
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-01
 */
@Data
public class UserPartnerApplyResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 0 伙伴申请 1 伙伴关系解散
     */
    private Integer type;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 发起人uid
     */
    private String fromUid;

    /**
     * 目标uid
     */
    private String targetUid;

    /**
     * 发起时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime applyTime;

    /**
     * 0 已发起 1 已同意 2 已拒绝 3 已过期
     */
    private Integer status;

    /**
     * 状态值
     */
    private String statusName;

    /**
     * 处理时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dealTime;


}
