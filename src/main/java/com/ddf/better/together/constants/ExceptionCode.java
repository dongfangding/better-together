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

    VERIFY_CODE_NOT_MATCH("10004", "验证码不匹配"),

    USER_INFO_NOT_EXIST("10005", "用户不存在"),

    APPLY_RECORD_EXIST("10006", "申请记录已存在"),

    APPLY_RECORD_NOT_EXIST("10007", "申请记录不存在"),

    NOT_ALLOW_OPERATE_OTHER_DATA("10008", "不可操作他人数据"),

    APPLY_RECORD_HAD_DEAL("10009", "申请记录已处理，不可继续操作"),

    ALREADY_YOURS_PARTNER("10010", "对方已是你的伙伴哦"),

    RESOURCE_PROP_ERROR("10011", "资源属性有误，无法上传"),

    TASK_NAME_ALREADY_EXIST("10012", "任务名称已存在"),

    TASK_VIEW_RECORD_NOT_EXIST("10013", "任务视图不存在"),

    SUPERVISED_TASK_WAIT_SUPERVISED_USER("10014", "被监督的任务只能监督人完成哦"),

    NOT_ALLOW_FINISHED_OTHERS_TASK_VIEW("10015", "不可以完成他人的任务"),

    UPDATE_FINISHED_TASK_FAILURE("10016", "更新完成任务失败"),

    UPDATE_OBTAIN_REWARD_FAILURE("10017", "更新获取奖励失败"),

    RECEIVE_REWARD_FAILURE("10018", "更新领取奖励失败"),

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
