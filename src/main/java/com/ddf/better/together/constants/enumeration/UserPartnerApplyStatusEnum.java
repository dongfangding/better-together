package com.ddf.better.together.constants.enumeration;

import com.ddf.boot.common.core.exception200.BusinessException;
import com.ddf.boot.common.core.exception200.GlobalCallbackCode;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户伙伴关系申请状态
 *
 * @author dongfang.ding
 * @date 2021/2/10 0010 13:49
 */
@AllArgsConstructor
public enum UserPartnerApplyStatusEnum {

    /**
     * 状态
     */
    UNKNOWN(-1, "未知"),
    NOT_DEAL(0, "待处理"),
    AGREE(1, "已同意"),
    REFUSE(2, "已拒绝"),
    EXPIRED(3, "已过期"),


    ;

    private static final Map<Integer, UserPartnerApplyStatusEnum> MAPPINGS;

    static {
        MAPPINGS = Arrays.stream(UserPartnerApplyStatusEnum.values()).collect(Collectors.toMap(
                UserPartnerApplyStatusEnum::getCode, val -> val));
    }


    /**
     * 获取枚举实例
     *
     * @param type
     * @return
     */
    public static UserPartnerApplyStatusEnum instanceOfCode(Integer type) {
        return instanceOfCode(type, false, false);
    }


    /**
     * 获取实例
     * @param type
     * @return
     */
    public static UserPartnerApplyStatusEnum instanceOfCodeDefaultUnknown(Integer type) {
        return instanceOfCode(type, false, true);
    }

    /**
     * 获取实例, 强一致性
     * @param type
     * @return
     */
    public static UserPartnerApplyStatusEnum instanceOfCodeConsistency(Integer type) {
        return instanceOfCode(type, true, false);
    }


    /**
     * 获取枚举实例
     * @param type
     * @param consistency 是否校验强一致性
     * @param defaultUnknown 映射错误时 是否使用默认known对象
     * @return
     */
    public static UserPartnerApplyStatusEnum instanceOfCode(Integer type, boolean consistency, boolean defaultUnknown) {
        final UserPartnerApplyStatusEnum instance = MAPPINGS.get(type);
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
