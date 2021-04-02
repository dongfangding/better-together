package com.ddf.better.together.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddf.better.together.constants.ExceptionCode;
import com.ddf.better.together.mapper.UserInfoMapper;
import com.ddf.better.together.model.entity.UserInfo;
import com.ddf.better.together.model.request.SearchUserRequest;
import com.ddf.better.together.service.IUserInfoService;
import com.ddf.boot.common.core.util.PreconditionUtil;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息表 服务实现类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-01
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    /**
     * 根据uid获取用户信息
     *
     * @param uid
     * @return
     */
    @Override
    public UserInfo getByUid(String uid, boolean isCheckExist) {
        final LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserInfo::getUid, uid);
        final UserInfo one = getOne(wrapper);
        if (isCheckExist) {
            PreconditionUtil.checkArgument(Objects.nonNull(one), ExceptionCode.USER_INFO_NOT_EXIST);
        }
        return one;
    }

    /**
     * 根据邮箱查询匹配数
     *
     * @param email
     * @return
     */
    @Override
    public int countByEmail(String email) {
        final LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserInfo::getEmail, email);
        return count(wrapper);
    }

    /**
     * 根据继承查询匹配数
     *
     * @param nickname
     * @return
     */
    @Override
    public int countByNickname(String nickname) {
        final LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserInfo::getNickname, nickname);
        return count(wrapper);
    }

    /**
     * 查询用户
     *
     * @param request
     * @return
     */
    @Override
    public List<UserInfo> searchUser(SearchUserRequest request) {
        final LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(request.getKeyword())) {
            wrapper.likeLeft(UserInfo::getEmail, request.getKeyword())
                    .or().likeLeft(UserInfo::getNickname, request.getKeyword());
        }
        return list(wrapper);
    }
}
