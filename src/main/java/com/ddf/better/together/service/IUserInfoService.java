package com.ddf.better.together.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddf.better.together.model.entity.UserInfo;
import com.ddf.better.together.model.request.SearchUserRequest;
import java.util.List;

/**
 * <p>
 * 用户基本信息表 服务类, 由于plus功能的封装， 该service用来替代dao的作用，禁止在该类中也业务代码， 建议另外用bizService承载业务
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-01
 */
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 根据uid获取用户信息
     *
     * @param uid
     * @param isCheckExist 是否校验用户信息是否存在
     * @return
     */
    UserInfo getByUid(String uid, boolean isCheckExist);

    /**
     * 根据uid
     * 获取并校验用户信息是否存在
     *
     * @param uid
     * @return
     */
    default UserInfo getByUidAndCheck(String uid) {
        return getByUid(uid, Boolean.TRUE);
    }

    /**
     * 根据邮箱查询匹配数
     *
     * @param email
     * @return
     */
    int countByEmail(String email);

    /**
     * 根据继承查询匹配数
     *
     * @param nickname
     * @return
     */
    int countByNickname(String nickname);

    /**
     * 查询用户
     *
     * @param request
     * @return
     */
    List<UserInfo> searchUser(SearchUserRequest request);
}
