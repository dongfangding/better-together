package com.ddf.better.together.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>获取邮箱验证码</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/01 22:29
 */
@Data
public class EmailCodeRequest {

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email
    private String email;
}
