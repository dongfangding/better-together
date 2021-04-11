package com.ddf.better.together.business;

import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.model.request.AgreePartnerApplyRequest;
import com.ddf.better.together.model.request.RefusePartnerApplyRequest;
import com.ddf.better.together.model.request.UserPartnerApplyRequest;
import com.ddf.better.together.model.response.UserPartnerApplyResponse;
import com.ddf.boot.common.core.model.UserClaim;
import com.ddf.boot.common.core.util.JsonUtil;
import com.ddf.boot.common.core.util.UserContextUtil;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/02 16:54
 */
public class UserPartnerApplyBizServiceTest extends ApplicationTest {

    @Autowired
    private UserPartnerApplyBizService userPartnerApplyBizService;

    /**
     * 测试申请好友请求
     */
    @Test
    public void testApply() {
        UserContextUtil.setUserClaim(new UserClaim().setUserId("1377909795931066461").setUsername("chen"));
        final UserPartnerApplyRequest request = new UserPartnerApplyRequest();
        request.setTargetUid("1377910295816605698");
        request.setApplyRemark("我是小小猪");
        request.setTargetNameRemark("小猫咪");
        final UserPartnerApplyResponse apply = userPartnerApplyBizService.apply(request);
        System.out.println(JsonUtil.asString(apply));
    }

    /**
     * 拒绝好友请求
     */
    @Test
    public void testRefuse() {
        UserContextUtil.setUserClaim(new UserClaim().setUserId("1377910295816605698").setUsername("chen"));
        final RefusePartnerApplyRequest request = new RefusePartnerApplyRequest();
        request.setApplyId(1377913219889053697L);
        userPartnerApplyBizService.refuse(request);

        userPartnerApplyBizService.refuse(request);
    }

    /**
     * 同意好友请求
     */
    @Test
    public void testAgree() throws InterruptedException {
        UserContextUtil.setUserClaim(new UserClaim().setUserId("1377910295816605698").setUsername("add"));
        final AgreePartnerApplyRequest request = new AgreePartnerApplyRequest();
        request.setApplyId(1377916311195922434L);
        request.setPartnerNameRemark("小小猪");
        userPartnerApplyBizService.agree(request);
        TimeUnit.SECONDS.sleep(5);
    }
}
