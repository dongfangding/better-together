package com.ddf.better.together.constants.enumration;

import com.ddf.boot.common.core.exception200.BusinessException;
import com.ddf.boot.common.core.exception200.GlobalCallbackCode;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户伙伴关系申请类型
 *
 * @author dongfang.ding
 * @date 2021/2/10 0010 13:49
 */
@AllArgsConstructor
public enum UserPartnerApplyTypeEnum {

    /**
     * 状态
     */
    UNKNOWN(-1, "未知"),
    BE_PARTNER(0, "伙伴申请"),
    CANCEL_PARTNER(1, "伙伴关系解散"),


    ;

    private static final Map<Integer, UserPartnerApplyTypeEnum> MAPPINGS;

    static {
        MAPPINGS = Arrays.stream(UserPartnerApplyTypeEnum.values()).collect(Collectors.toMap(UserPartnerApplyTypeEnum::getCode, val -> val));
    }


    /**
     * 获取枚举实例
     *
     * @param type
     * @return
     */
    public static UserPartnerApplyTypeEnum instanceOfCode(Integer type) {
        return instanceOfCode(type, false, false);
    }


    /**
     * 获取实例
     * @param type
     * @return
     */
    public static UserPartnerApplyTypeEnum instanceOfCodeDefaultUnknown(Integer type) {
        return instanceOfCode(type, false, true);
    }

    /**
     * 获取实例, 强一致性
     * @param type
     * @return
     */
    public static UserPartnerApplyTypeEnum instanceOfCodeConsistency(Integer type) {
        return instanceOfCode(type, true, false);
    }


    /**
     * 获取枚举实例
     * @param type
     * @param consistency 是否校验强一致性
     * @param defaultUnknown 映射错误时 是否使用默认known对象
     * @return
     */
    public static UserPartnerApplyTypeEnum instanceOfCode(Integer type, boolean consistency, boolean defaultUnknown) {
        final UserPartnerApplyTypeEnum instance = MAPPINGS.get(type);
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
