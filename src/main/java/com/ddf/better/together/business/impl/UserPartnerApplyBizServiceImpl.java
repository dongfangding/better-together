package com.ddf.better.together.business.impl;

import com.ddf.better.together.business.UserPartnerApplyBizService;
import com.ddf.better.together.constants.ExceptionCode;
import com.ddf.better.together.constants.enumration.UserPartnerApplyStatusEnum;
import com.ddf.better.together.constants.enumration.UserPartnerApplyTypeEnum;
import com.ddf.better.together.constants.enumration.UserPartnerStatusEnum;
import com.ddf.better.together.convert.mapper.UserPartnerApplyMapperConvert;
import com.ddf.better.together.model.entity.UserInfo;
import com.ddf.better.together.model.entity.UserPartner;
import com.ddf.better.together.model.entity.UserPartnerApply;
import com.ddf.better.together.model.request.AgreePartnerApplyRequest;
import com.ddf.better.together.model.request.RefusePartnerApplyRequest;
import com.ddf.better.together.model.request.UserPartnerApplyRequest;
import com.ddf.better.together.model.response.UserPartnerApplyResponse;
import com.ddf.better.together.service.IUserInfoService;
import com.ddf.better.together.service.IUserPartnerApplyService;
import com.ddf.better.together.service.IUserPartnerService;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.boot.common.core.util.UserContextUtil;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>用户伙伴申请</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/02 14:20
 */
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Service
public class UserPartnerApplyBizServiceImpl implements UserPartnerApplyBizService {

    private final IUserInfoService userInfoService;

    private final IUserPartnerApplyService userPartnerApplyService;

    private final IUserPartnerService userPartnerService;

    /**
     * 伙伴关系申请
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserPartnerApplyResponse apply(UserPartnerApplyRequest request) {
        final UserInfo targetUserInfo = userInfoService.getByUidAndCheck(request.getTargetUid());
        final String currentUid = UserContextUtil.getUserId();
        PreconditionUtil.checkArgument(
                Objects.isNull(userPartnerApplyService.checkNotDealApplyIsExist(currentUid, request.getTargetUid())),
                ExceptionCode.APPLY_RECORD_EXIST
        );

        final UserPartnerApply apply = new UserPartnerApply();
        apply.setFromUid(currentUid);
        apply.setTargetUid(request.getTargetUid());
        apply.setType(UserPartnerApplyTypeEnum.BE_PARTNER.getCode());
        final LocalDateTime now = LocalDateTime.now();
        apply.setDealTime(now);
        apply.setApplyTime(now);
        apply.setStatus(UserPartnerApplyStatusEnum.NOT_DEAL.getCode());
        userPartnerApplyService.save(apply);

        return UserPartnerApplyMapperConvert.INSTANCE.convert(apply);
    }

    /**
     * 拒绝成为伙伴
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean agree(AgreePartnerApplyRequest request) {
        final UserPartnerApply apply = updateApplyStatus(request.getApplyId(), Boolean.TRUE);

        // 双向增加伙伴关系表记录
        final UserPartner partner = new UserPartner();
        final LocalDateTime now = LocalDateTime.now();
        partner.setCreateTime(now);
        partner.setUid(apply.getFromUid());
        partner.setPartnerUid(apply.getTargetUid());
        partner.setStatus(UserPartnerStatusEnum.ACTIVE.getCode());
        partner.setStatusUpdateTime(now);
        userPartnerService.save(partner);

        partner.setId(null);
        partner.setUid(apply.getTargetUid());
        partner.setPartnerUid(apply.getFromUid());
        userPartnerService.save(partner);

        return Boolean.TRUE;
    }

    /**
     * 拒绝成为伙伴
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean refuse(RefusePartnerApplyRequest request) {
        final UserPartnerApply apply = updateApplyStatus(request.getApplyId(), Boolean.FALSE);
        return Boolean.TRUE;
    }


    /**
     * 更改申请记录表状态
     *
     * @param applyId
     * @param agree
     */
    private UserPartnerApply updateApplyStatus(Long applyId, boolean agree) {
        final UserPartnerApply apply = userPartnerApplyService.getByPK(applyId);
        PreconditionUtil.checkArgument(Objects.nonNull(apply), ExceptionCode.APPLY_RECORD_NOT_EXIST);
        PreconditionUtil.checkArgument(
                Objects.equals(UserPartnerApplyStatusEnum.NOT_DEAL.getCode(), apply.getStatus()),
                ExceptionCode.APPLY_RECORD_HAD_DEAL
        );

        final String currentUid = UserContextUtil.getUserId();
        PreconditionUtil.checkArgument(
                Objects.equals(currentUid, apply.getTargetUid()), ExceptionCode.NOT_ALLOW_OPERATE_OTHER_DATA);
        // 修改记录表状态
        if (agree) {
            userPartnerApplyService.agree(applyId);
        } else {
            userPartnerApplyService.refuse(applyId);
        }

        return apply;
    }
}
