package com.ddf.better.together.config;

import com.ddf.boot.common.core.helper.ThreadBuilderHelper;
import com.ddf.boot.common.core.shutdown.ThreadPoolExecutorShutdownDefinition;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/06 23:32
 */
@Configuration
public class ThreadConfig {

    /**
     * 推模式填充动态收件箱线程池
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor fillDynamicReceiveBoxPool() {
        return ThreadBuilderHelper.buildThreadExecutor("fillDynamicReceiveBoxPool", 300, 1000,
                Runtime.getRuntime().availableProcessors() + 1, Runtime.getRuntime().availableProcessors() * 2);
    }

    @PostConstruct
    public void init() {
        // 注册线程池优雅关闭
        ThreadPoolExecutorShutdownDefinition.registryExecutor(fillDynamicReceiveBoxPool());
    }

}
