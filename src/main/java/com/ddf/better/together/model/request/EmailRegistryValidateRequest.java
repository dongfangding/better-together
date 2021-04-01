package com.ddf.better.together.model.request;

import lombok.Data;

/**
 * <p>校验注册参数信息是否可用， 当字段不为空时会进行对应字段是否已被使用的判断</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/01 22:58
 */
@Data
public class EmailRegistryValidateRequest {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickname;
}
