package com.ddf.better.together.config;

import com.ddf.better.together.model.entity.UserInfo;
import com.ddf.better.together.service.IUserInfoService;
import com.ddf.boot.common.authentication.interfaces.UserClaimService;
import com.ddf.boot.common.authentication.model.UserClaim;
import com.ddf.boot.common.core.util.JsonUtil;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>用户认证信息实现</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/11/26 18:21
 */
@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserClaimServiceImpl implements UserClaimService {

    private final IUserInfoService iUserInfoService;

    @Override
    public void storeRequest(HttpServletRequest request, String host) {

    }

    @Override
    public UserClaim getStoreUserInfo(UserClaim userClaim) {
        final UserInfo userInfo = iUserInfoService.getByUidAndCheck(userClaim.getUserId());

        final UserClaim claim = new UserClaim();
        claim.setUserId(userInfo.getUid());
        claim.setUsername(userInfo.getNickname());
        claim.setCredit(userClaim.getCredit());
        claim.setLastModifyPasswordTime(null);
        claim.setRemarks("");
        claim.setDetail(JsonUtil.asString(userInfo));
        return claim;
    }
}
