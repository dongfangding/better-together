package com.ddf.better.together.convert.assemble;

import com.ddf.better.together.convert.mapper.UserDynamicMapperConvert;
import com.ddf.better.together.model.query.SearchUserDynamicQuery;
import com.ddf.better.together.model.request.SearchUserDynamicRequest;
import com.ddf.boot.common.authentication.util.UserContextUtil;
import org.springframework.stereotype.Component;

/**
 * <p>用户动态组装器</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/11/10 15:58
 */
@Component
public class UserDynamicAssemble {

    /**
     * 动态查询请求类转换为内部查询参数类
     *
     * @param request
     * @return
     */
    public SearchUserDynamicQuery convert(SearchUserDynamicRequest request) {
        final SearchUserDynamicQuery convert = UserDynamicMapperConvert.INSTANCE.convert(request);
        convert.setUid(UserContextUtil.getUserId());
        return convert;
    }
}
