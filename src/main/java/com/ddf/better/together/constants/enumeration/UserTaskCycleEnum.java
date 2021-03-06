package com.ddf.better.together.constants.enumeration;

import com.ddf.boot.common.core.exception200.BusinessException;
import com.ddf.boot.common.core.exception200.GlobalCallbackCode;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户任务周期类型枚举
 *
 * @author dongfang.ding
 * @date 2021/2/10 0010 13:49
 */
@AllArgsConstructor
public enum UserTaskCycleEnum {

    /**
     * 状态
     */
    UNKNOWN(-1, "未知"),
    UNKNOWN1(0, "未知"),
    ONE(1, "一次性任务"),
    EVERY_DAY(2, "每日任务"),
    EVERY_WEEK(3, "每周任务"),
    EVERY_MONTH(4, "每月任务"),
    EVERY_YEAR(5, "每年任务"),


    ;

    private static final Map<Integer, UserTaskCycleEnum> MAPPINGS;

    static {
        MAPPINGS = Arrays.stream(UserTaskCycleEnum.values()).collect(Collectors.toMap(
                UserTaskCycleEnum::getCode, val -> val));
    }

    /**
     * 获取枚举实例
     *
     * @param type
     * @return
     */
    public static UserTaskCycleEnum instanceOfCode(Integer type) {
        return instanceOfCode(type, false, false);
    }


    /**
     * 获取实例
     * @param type
     * @return
     */
    public static UserTaskCycleEnum instanceOfCodeDefaultUnknown(Integer type) {
        return instanceOfCode(type, false, true);
    }

    /**
     * 获取实例, 强一致性
     * @param type
     * @return
     */
    public static UserTaskCycleEnum instanceOfCodeConsistency(Integer type) {
        return instanceOfCode(type, true, false);
    }


    /**
     * 获取枚举实例
     * @param type
     * @param consistency 是否校验强一致性
     * @param defaultUnknown 映射错误时 是否使用默认known对象
     * @return
     */
    public static UserTaskCycleEnum instanceOfCode(Integer type, boolean consistency, boolean defaultUnknown) {
        final UserTaskCycleEnum instance = MAPPINGS.get(type);
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
