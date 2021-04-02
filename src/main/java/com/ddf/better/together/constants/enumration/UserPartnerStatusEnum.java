package com.ddf.better.together.constants.enumration;

import com.ddf.boot.common.core.exception200.BusinessException;
import com.ddf.boot.common.core.exception200.GlobalCallbackCode;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户伙伴关系状态
 *
 * @author dongfang.ding
 * @date 2021/2/10 0010 13:49
 */
@AllArgsConstructor
public enum UserPartnerStatusEnum {

    /**
     * 状态
     */
    UNKNOWN(-1, "未知"),
    ACTIVE(0, "有效伙伴"),
    CANCEL_APPLYING(1, "解散申请中"),
    CANCELED(2, "已解散")


    ;

    private static final Map<Integer, UserPartnerStatusEnum> MAPPINGS;

    static {
        MAPPINGS = Arrays.stream(UserPartnerStatusEnum.values()).collect(Collectors.toMap(
                UserPartnerStatusEnum::getCode, val -> val));
    }


    /**
     * 获取枚举实例
     *
     * @param type
     * @return
     */
    public static UserPartnerStatusEnum instanceOfCode(Integer type) {
        return instanceOfCode(type, false, false);
    }


    /**
     * 获取实例
     * @param type
     * @return
     */
    public static UserPartnerStatusEnum instanceOfCodeDefaultUnknown(Integer type) {
        return instanceOfCode(type, false, true);
    }

    /**
     * 获取实例, 强一致性
     * @param type
     * @return
     */
    public static UserPartnerStatusEnum instanceOfCodeConsistency(Integer type) {
        return instanceOfCode(type, true, false);
    }


    /**
     * 获取枚举实例
     * @param type
     * @param consistency 是否校验强一致性
     * @param defaultUnknown 映射错误时 是否使用默认known对象
     * @return
     */
    public static UserPartnerStatusEnum instanceOfCode(Integer type, boolean consistency, boolean defaultUnknown) {
        final UserPartnerStatusEnum instance = MAPPINGS.get(type);
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
