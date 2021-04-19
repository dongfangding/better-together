package com.ddf.better.together.job;

import com.xxl.job.core.biz.model.ReturnT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>定时任务触发器定义</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/11 22:51
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class JobTrigger {

    private final JobBizHandler jobBizHandler;


    public ReturnT<String> startTask(String param) {
        return jobBizHandler.startTask(param);
    }
}
