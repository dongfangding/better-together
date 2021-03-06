package com.ddf.better.together.model.request;

import com.ddf.boot.common.core.model.PageRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>查询用户动态</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/05 21:12
 */
@Data
public class SearchUserDynamicRequest implements PageRequest {

    /**
     * 用户uid
     */
    @NotBlank(message = "用户uid不能为空")
    private String uid;

    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 每页数据大小
     */
    private Integer pageSize;

}
