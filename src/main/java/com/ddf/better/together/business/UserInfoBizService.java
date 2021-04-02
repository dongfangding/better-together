package com.ddf.better.together.business;

import com.ddf.better.together.model.dto.UserInfoDTO;
import com.ddf.better.together.model.request.EmailRegistryRequest;
import com.ddf.better.together.model.request.EmailRegistryValidateRequest;
import com.ddf.better.together.model.request.SearchUserRequest;
import com.ddf.better.together.model.response.EmailRegistryValidateResponse;
import java.util.List;

/**
 * <p>用户业务处理类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/01 23:07
 */
public interface UserInfoBizService {

    /**
     * 校验注册信息是否可用
     *
     * @param request
     * @return
     */
    EmailRegistryValidateResponse checkRegistryInfo(EmailRegistryValidateRequest request);

    /**
     * 邮箱注册
     *
     * @param request
     * @return
     */
    Boolean registry(EmailRegistryRequest request);

    /**
     * 查询用户
     *
     * @param request
     * @return
     */
    List<UserInfoDTO> searchUser(SearchUserRequest request);
}
