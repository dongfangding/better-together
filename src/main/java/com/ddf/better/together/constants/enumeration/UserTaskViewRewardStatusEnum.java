package com.ddf.better.together.constants.enumeration;

import com.ddf.boot.common.core.exception200.BusinessException;
import com.ddf.boot.common.core.exception200.GlobalCallbackCode;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户任务奖励状态
 *
 * @author dongfang.ding
 * @date 2021/2/tin10 0010 13:49
 */
@AllArgsConstructor
public enum UserTaskViewRewardStatusEnum {

    /**
     * 状态
     * 未获得，说明这个奖励就没拿到
     * 已获得，指的是这个奖励是要给用户的，但是需要用户确认自己有没有领取到
     * 已领取，用户获取到奖励，自己确认领取了，说明真的奖励到手了
     */
    UNKNOWN(-1, "未知"),
    NOT_OBTAIN(0, "未获得"),
    OBTAINED(1, "已获得"),
    RECEIVED(1, "已领取"),


    ;

    private static final Map<Integer, UserTaskViewRewardStatusEnum> MAPPINGS;

    static {
        MAPPINGS = Arrays.stream(UserTaskViewRewardStatusEnum.values()).collect(Collectors.toMap(
                UserTaskViewRewardStatusEnum::getCode, val -> val));
    }

    /**
     * 获取枚举实例
     *
     * @param type
     * @return
     */
    public static UserTaskViewRewardStatusEnum instanceOfCode(Integer type) {
        return instanceOfCode(type, false, false);
    }


    /**
     * 获取实例
     * @param type
     * @return
     */
    public static UserTaskViewRewardStatusEnum instanceOfCodeDefaultUnknown(Integer type) {
        return instanceOfCode(type, false, true);
    }

    /**
     * 获取实例, 强一致性
     * @param type
     * @return
     */
    public static UserTaskViewRewardStatusEnum instanceOfCodeConsistency(Integer type) {
        return instanceOfCode(type, true, false);
    }


    /**
     * 获取枚举实例
     * @param type
     * @param consistency 是否校验强一致性
     * @param defaultUnknown 映射错误时 是否使用默认known对象
     * @return
     */
    public static UserTaskViewRewardStatusEnum instanceOfCode(Integer type, boolean consistency, boolean defaultUnknown) {
        final UserTaskViewRewardStatusEnum instance = MAPPINGS.get(type);
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
