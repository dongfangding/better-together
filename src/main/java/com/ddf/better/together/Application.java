package com.ddf.better.together;

import com.ddf.boot.common.core.logaccess.EnableLogAspect;
import com.ddf.boot.common.jwt.config.EnableJwt;
import com.ddf.boot.common.limit.repeatable.annotation.EnableRepeatable;
import com.ddf.boot.common.limit.repeatable.validator.RedisRepeatableValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/01 22:00
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.ddf.better.together.mapper"})
@EnableJwt
@EnableLogAspect(slowTime = 3000)
@EnableRepeatable(globalValidator = RedisRepeatableValidator.BEAN_NAME)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
