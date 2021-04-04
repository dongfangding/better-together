package com.ddf.better.together.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>资源核心属性类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/04 22:13
 */
@Data
@Accessors(chain = true)
public class ResourceDTO {

    /**
     * 地址
     */
    private String url;

    /**
     * 扩展属性
     */
    private String extra;
}
