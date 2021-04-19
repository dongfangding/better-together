package com.ddf.better.together.business;

import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.job.JobBizHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/19 23:31
 */
public class JobHandlerTest extends ApplicationTest {

    @Autowired
    private JobBizHandler jobBizHandler;

    @Test
    public void testStartTask() {
        jobBizHandler.startTask("");
    }
}
