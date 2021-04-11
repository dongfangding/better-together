package com.ddf.better.together.constants.enumeration;

import com.ddf.boot.common.core.exception200.BusinessException;
import com.ddf.boot.common.core.exception200.GlobalCallbackCode;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户任务视图状态
 *
 * @author dongfang.ding
 * @date 2021/4/10 0010 14:49
 */
@AllArgsConstructor
public enum UserTaskViewStatusEnum {

    /**
     * 0 进行中 1 任务超时（即时间到了，但是任务双方没进行任务操作） 2 任务完成 3 任务失败 4 奖励确认中 5 奖励完成
     */
    UNKNOWN(-1, "未知"),
    ING(0, "进行中"),
    TIME_OUT(1, "任务超时"),
    COMPLETE(2, "任务完成"),
    FAILURE(3, "任务失败"),
    REWARD_ENSURE_ING(4, "奖励确认中"),
    REWARD_COMPLETE(5, "奖励完成")

    ;

    private static final Map<Integer, UserTaskViewStatusEnum> MAPPINGS;

    static {
        MAPPINGS = Arrays.stream(UserTaskViewStatusEnum.values()).collect(Collectors.toMap(
                UserTaskViewStatusEnum::getCode, val -> val));
    }

    /**
     * 获取枚举实例
     *
     * @param type
     * @return
     */
    public static UserTaskViewStatusEnum instanceOfCode(Integer type) {
        return instanceOfCode(type, false, false);
    }


    /**
     * 获取实例
     * @param type
     * @return
     */
    public static UserTaskViewStatusEnum instanceOfCodeDefaultUnknown(Integer type) {
        return instanceOfCode(type, false, true);
    }

    /**
     * 获取实例, 强一致性
     * @param type
     * @return
     */
    public static UserTaskViewStatusEnum instanceOfCodeConsistency(Integer type) {
        return instanceOfCode(type, true, false);
    }


    /**
     * 获取枚举实例
     * @param type
     * @param consistency 是否校验强一致性
     * @param defaultUnknown 映射错误时 是否使用默认known对象
     * @return
     */
    public static UserTaskViewStatusEnum instanceOfCode(Integer type, boolean consistency, boolean defaultUnknown) {
        final UserTaskViewStatusEnum instance = MAPPINGS.get(type);
        if (instance == null) {
            if (consistency) {
                throw new BusinessException(GlobalCallbackCode.ENUM_CODE_NOT_MAPPING);
            } else if (defaultUnknown) {
                return UNKNOWN;
            }
            return null;
        }
        return instance;
    }


    /**
     * 代码
     */
    @Getter
    private final Integer code;

    /**
     * 含义
     */
    @Getter
    private final String desc;
}
