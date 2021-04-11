package com.ddf.better.together.model.request;

import com.ddf.boot.common.core.model.PageRequest;
import lombok.Data;

/**
 * <p>用户任务定义查询</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/11 21:21
 */
@Data
public class UserTaskDefinitionRequest implements PageRequest {

    private Integer pageNum;
    private Integer pageSiz;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务周期
     */
    private Integer cycle;

    /**
     * 任务是否激活
     */
    private Boolean active;

    /**
     * 任务是否需要监督
     */
    private Boolean supervised;

    /**
     * 任务监督人
     */
    private String supervisedUid;

    /**
     * 任务奖励类型
     */
    private Integer rewardType;

    /**
     * 用户uid
     */
    private String uid;

}
