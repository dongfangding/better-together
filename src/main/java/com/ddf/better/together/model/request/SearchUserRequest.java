package com.ddf.better.together.model.request;

import lombok.Data;

/**
 * <p>查询用户请求类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/02 12:53
 */
@Data
public class SearchUserRequest {

    /**
     * 精确匹配邮箱或昵称
     */
    private String keyword;
}
