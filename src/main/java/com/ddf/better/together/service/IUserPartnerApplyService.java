package com.ddf.better.together.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddf.better.together.model.entity.UserPartnerApply;

/**
 * <p>
 * 用户伙伴申请记录表 服务类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-01
 */
public interface IUserPartnerApplyService extends IService<UserPartnerApply> {

    /**
     * 根据主键获取记录
     *
     * @param id
     * @return
     */
    default UserPartnerApply getByPK(Long id) {
        return getById(id);
    }

    /**
     * 查询是否存在未处理的伙伴申请记录
     *
     * @param fromUid
     * @param targetUid
     * @return
     */
    UserPartnerApply checkNotDealApplyIsExist(String fromUid, String targetUid);

    /**
     * 同意
     *
     * @param id
     * @return
     */
    boolean agree(Long id);

    /**
     * 拒绝
     *
     * @param id
     * @return
     */
    boolean refuse(Long id);
}
