package com.ddf.better.together.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddf.better.together.constants.enumration.UserPartnerApplyStatusEnum;
import com.ddf.better.together.constants.enumration.UserPartnerApplyTypeEnum;
import com.ddf.better.together.mapper.UserPartnerApplyMapper;
import com.ddf.better.together.model.entity.UserPartnerApply;
import com.ddf.better.together.service.IUserPartnerApplyService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户伙伴申请记录表 服务实现类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-01
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserPartnerApplyServiceImpl extends ServiceImpl<UserPartnerApplyMapper, UserPartnerApply> implements IUserPartnerApplyService {

    /**
     * 查询是否存在未处理的伙伴申请记录
     *
     * @param fromUid
     * @param targetUid
     * @return
     */
    @Override
    public UserPartnerApply checkNotDealApplyIsExist(String fromUid, String targetUid) {
        final LambdaQueryWrapper<UserPartnerApply> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserPartnerApply::getType, UserPartnerApplyTypeEnum.BE_PARTNER.getCode())
                .eq(UserPartnerApply::getFromUid, fromUid)
                .eq(UserPartnerApply::getTargetUid, targetUid)
                .eq(UserPartnerApply::getStatus, UserPartnerApplyStatusEnum.NOT_DEAL.getCode());
        return getOne(wrapper);
    }

    /**
     * 同意
     *
     * @param id
     * @return
     */
    @Override
    public boolean agree(Long id) {
        final LambdaUpdateWrapper<UserPartnerApply> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(UserPartnerApply::getId, id);
        wrapper.set(UserPartnerApply::getDealTime, LocalDateTime.now())
                .set(UserPartnerApply::getStatus, UserPartnerApplyStatusEnum.AGREE.getCode());
        return update(wrapper);
    }

    /**
     * 拒绝
     *
     * @param id
     * @return
     */
    @Override
    public boolean refuse(Long id) {
        final LambdaUpdateWrapper<UserPartnerApply> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(UserPartnerApply::getId, id);
        wrapper.set(UserPartnerApply::getDealTime, LocalDateTime.now())
                .set(UserPartnerApply::getStatus, UserPartnerApplyStatusEnum.REFUSE.getCode());
        return update(wrapper);
    }
}
