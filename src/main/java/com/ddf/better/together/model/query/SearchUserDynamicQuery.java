package com.ddf.better.together.model.query;

import com.ddf.boot.common.core.model.PageRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/11/10 15:54
 */
@Data
public class SearchUserDynamicQuery implements PageRequest {

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


    // ----------------------不需要客户端传的参数----------------
    /**
     * 当前用户uid，客户端不需要传
     */
    private String currentUid;
}
