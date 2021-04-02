package com.ddf.better.together.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddf.better.together.constants.enumration.UserPartnerStatusEnum;
import com.ddf.better.together.mapper.UserPartnerMapper;
import com.ddf.better.together.model.entity.UserPartner;
import com.ddf.better.together.service.IUserPartnerService;
import com.ddf.boot.common.core.util.UserContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户伙伴关系表 服务实现类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-01
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserPartnerServiceImpl extends ServiceImpl<UserPartnerMapper, UserPartner> implements IUserPartnerService {

    /**
     * 根据伙伴uid查询对应的我的有效伙伴记录
     *
     * @param partnerUid
     * @return
     */
    @Override
    public UserPartner getMyActivePartner(String partnerUid) {
        final LambdaQueryWrapper<UserPartner> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserPartner::getUid, UserContextUtil.getUserId())
                .eq(UserPartner::getPartnerUid, partnerUid)
                .eq(UserPartner::getStatus, UserPartnerStatusEnum.ACTIVE.getCode());
        return getOne(wrapper);
    }
}
