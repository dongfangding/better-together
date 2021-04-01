package com.ddf.better.together.model.response;

import lombok.Data;

/**
 * <p>校验注册参数信息是否可用的返回结果</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/01 22:58
 */
@Data
public class EmailRegistryValidateResponse {

    /**
     * 邮箱是否可用
     */
    private Boolean emailResult = Boolean.TRUE;

    /**
     * 昵称是否可用
     */
    private Boolean nicknameResult = Boolean.TRUE;
}
