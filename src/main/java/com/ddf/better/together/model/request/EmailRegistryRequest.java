package com.ddf.better.together.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * <p>邮件注册</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/01 22:58
 */
@Data
public class EmailRegistryRequest {

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 18, message = "密码长度在6到18个字符之间")
    private String password;

    /**
     * 邮箱验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;
}
