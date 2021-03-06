package com.ddf.better.together.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddf.better.together.model.entity.UserDynamicReceiveBox;

/**
 * <p>
 * 用户动态收件箱，推模式下使用,注意发布人自己的动态也会推送一条给自己的数据 服务类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-06
 */
public interface IUserDynamicReceiveBoxService extends IService<UserDynamicReceiveBox> {

    /**
     * 删除自己好友动态中的伙伴的动态
     *
     * @param partnerUid
     * @return
     */
    boolean deletePartnerDynamic(String partnerUid);
}
