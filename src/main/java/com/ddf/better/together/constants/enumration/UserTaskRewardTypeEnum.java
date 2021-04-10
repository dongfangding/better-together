package com.ddf.better.together.constants.enumration;

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
 * @date 2021/4/10 0010 14:49
 */
@AllArgsConstructor
public enum UserTaskRewardTypeEnum {

    /**
     * 状态
     */
    UNKNOWN(-1, "未知"),
    OUT_SITE(0, "站外奖励"),
    SCORE(1, "积分奖励"),
    LEVEL_SCORE(2, "递进型积分奖励")

    ;

    private static final Map<Integer, UserTaskRewardTypeEnum> MAPPINGS;

    static {
        MAPPINGS = Arrays.stream(UserTaskRewardTypeEnum.values()).collect(Collectors.toMap(
                UserTaskRewardTypeEnum::getCode, val -> val));
    }

    /**
     * 获取枚举实例
     *
     * @param type
     * @return
     */
    public static UserTaskRewardTypeEnum instanceOfCode(Integer type) {
        return instanceOfCode(type, false, false);
    }


    /**
     * 获取实例
     * @param type
     * @return
     */
    public static UserTaskRewardTypeEnum instanceOfCodeDefaultUnknown(Integer type) {
        return instanceOfCode(type, false, true);
    }

    /**
     * 获取实例, 强一致性
     * @param type
     * @return
     */
    public static UserTaskRewardTypeEnum instanceOfCodeConsistency(Integer type) {
        return instanceOfCode(type, true, false);
    }


    /**
     * 获取枚举实例
     * @param type
     * @param consistency 是否校验强一致性
     * @param defaultUnknown 映射错误时 是否使用默认known对象
     * @return
     */
    public static UserTaskRewardTypeEnum instanceOfCode(Integer type, boolean consistency, boolean defaultUnknown) {
        final UserTaskRewardTypeEnum instance = MAPPINGS.get(type);
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
