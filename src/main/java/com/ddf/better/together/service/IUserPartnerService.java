package com.ddf.better.together.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddf.better.together.model.entity.UserPartner;
import java.util.List;

/**
 * <p>
 * 用户伙伴关系表 服务类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-01
 */
public interface IUserPartnerService extends IService<UserPartner> {

    /**
     * 根据伙伴uid查询对应的我的有效伙伴记录
     *
     * @param partnerUid
     * @return
     */
    UserPartner getMyActivePartner(String partnerUid);

    /**
     * 获取用户的好友列表
     *
     * @param uid
     * @return
     */
    List<UserPartner> getUserPartners(String uid);


}
