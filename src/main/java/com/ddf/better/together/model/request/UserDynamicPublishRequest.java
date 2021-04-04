package com.ddf.better.together.model.request;

import com.ddf.better.together.constants.enumration.UserDynamicViewLevelEnum;
import com.ddf.better.together.model.dto.ResourceDTO;
import com.ddf.boot.common.core.validator.constraint.LogicValueValidator;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>用户动态发布请求参数类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/04 22:34
 */
@Data
public class UserDynamicPublishRequest {

    /**
     * 动态内容
     */
    @NotBlank(message = "动态内容不能为空")
    private String content;

    /**
     * 动态显示级别 0 公开 1 仅自己可见
     * @see UserDynamicViewLevelEnum
     */
    @LogicValueValidator(values = {0, 1})
    private Integer viewLevel = 0;

    /**
     * 动态发布地址
     */
    private String location;

    /**
     * 视频地址信息
     */
    private ResourceDTO videoResource;

    /**
     * 图片地址信息
     */
    private List<ResourceDTO> picResources;
}
