package com.ddf.better.together.constants;

import com.ddf.boot.common.core.exception200.BaseCallbackCode;
import lombok.AllArgsConstructor;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/01 23:24
 */
@AllArgsConstructor
public enum ExceptionCode implements BaseCallbackCode {

    /**
     * 错误码
     */
    EMAIL_USED("100001", "邮箱已使用"),

    NICKNAME_USED("100002", "昵称已使用"),

    VERIFY_CODE_EXPIRED("10003", "验证码已过期"),

    VERIFY_CODE_NOT_MATCH("10004", "验证码不匹配")

    ;

    private final String code;

    private final String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return message;
    }
}
