package com.ddf.better.together.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddf.better.together.mapper.UserDynamicReceiveBoxMapper;
import com.ddf.better.together.model.entity.UserDynamicReceiveBox;
import com.ddf.better.together.service.IUserDynamicReceiveBoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户动态收件箱，推模式下使用,注意发布人自己的动态也会推送一条给自己的数据 服务实现类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-06
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserDynamicReceiveBoxServiceImpl extends ServiceImpl<UserDynamicReceiveBoxMapper, UserDynamicReceiveBox> implements IUserDynamicReceiveBoxService {

    /**
     * 删除自己好友动态中的伙伴的动态
     *
     * @param partnerUid
     * @return
     */
    @Override
    public boolean deletePartnerDynamic(String partnerUid) {
        final LambdaQueryWrapper<UserDynamicReceiveBox> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserDynamicReceiveBox::getUid, partnerUid);
        return remove(wrapper);
    }
}
