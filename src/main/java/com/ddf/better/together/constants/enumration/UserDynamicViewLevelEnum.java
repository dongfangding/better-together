package com.ddf.better.together.constants.enumration;

import com.ddf.boot.common.core.exception200.BusinessException;
import com.ddf.boot.common.core.exception200.GlobalCallbackCode;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户动态可见级别
 *
 * @author dongfang.ding
 * @date 2021/2/10 0010 13:49
 */
@AllArgsConstructor
public enum UserDynamicViewLevelEnum {

    /**
     * 状态
     */
    UNKNOWN(-1, "未知"),
    FRIEND(0, "仅朋友可见"),
    SELF(1, "仅自己可见")


    ;

    private static final Map<Integer, UserDynamicViewLevelEnum> MAPPINGS;

    static {
        MAPPINGS = Arrays.stream(UserDynamicViewLevelEnum.values()).collect(Collectors.toMap(
                UserDynamicViewLevelEnum::getCode, val -> val));
    }


    /**
     * 获取枚举实例
     *
     * @param type
     * @return
     */
    public static UserDynamicViewLevelEnum instanceOfCode(Integer type) {
        return instanceOfCode(type, false, false);
    }


    /**
     * 获取实例
     * @param type
     * @return
     */
    public static UserDynamicViewLevelEnum instanceOfCodeDefaultUnknown(Integer type) {
        return instanceOfCode(type, false, true);
    }

    /**
     * 获取实例, 强一致性
     * @param type
     * @return
     */
    public static UserDynamicViewLevelEnum instanceOfCodeConsistency(Integer type) {
        return instanceOfCode(type, true, false);
    }


    /**
     * 获取枚举实例
     * @param type
     * @param consistency 是否校验强一致性
     * @param defaultUnknown 映射错误时 是否使用默认known对象
     * @return
     */
    public static UserDynamicViewLevelEnum instanceOfCode(Integer type, boolean consistency, boolean defaultUnknown) {
        final UserDynamicViewLevelEnum instance = MAPPINGS.get(type);
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
