package com.ddf.better.together.model.response;

import com.ddf.better.together.model.dto.ResourceDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * <p>用户动态</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/05 21:15
 */
@Data
public class UserDynamicResponse {

    /**
     * 动态发布人uid
     */
    private String uid;

    /**
     * 动态发布人昵称
     */
    private String nickname;

    /**
     * 动态发布人头像url
     */
    private String avatarUrl;

    /**
     * 好友备注，当不是查看自己的动态时显示
     */
    private String partnerNameRemark;

    /**
     * 动态内容
     */
    private String content;

    /**
     * 动态发布地址
     */
    private String location;

    /**
     * 动态发布时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishTime;

    /**
     * 图片列表
     */
    private List<ResourceDTO> picResources = new ArrayList<>();

    /**
     * 视频
     */
    private ResourceDTO videoResource;
}
