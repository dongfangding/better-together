package com.ddf.better.together.redis;

import com.ddf.boot.common.redis.constant.ApplicationNamedKeyGenerator;

/**
 * 缓存key生成
 *
 * @author dongfang.ding
 * @date 2021/2/10 0010 12:25
 */
public interface CacheKeys {


    /**
     * 获取邮箱验证码缓存key
     *
     * @param email 邮箱
     * @return
     */
    static String getEmailVerifyCodeKey(String email) {
        return ApplicationNamedKeyGenerator.genKey("verify_code", "email", email);
    }

    /**
     * 任务开始时间缓存key
     * ZSet key 任务id 处罚时间戳
     *
     * @return
     */
    static String getTaskTriggerTimeKey() {
        return ApplicationNamedKeyGenerator.genKey("task_trigger_time");
    }
}
