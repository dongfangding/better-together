package com.ddf.better.together.model.request;

import com.ddf.better.together.model.dto.ResourceDTO;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>用户资源上传请求</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/04 22:09
 */
@Data
public class UserResourceCreateRequest {

    /**
     * 用户uid
     */
    @NotBlank(message = "uid不能为空")
    private String uid;

    /**
     * 图片url
     */
    private List<ResourceDTO> picResources;

    /**
     * 视频url
     */
    private ResourceDTO videoResource;
}
