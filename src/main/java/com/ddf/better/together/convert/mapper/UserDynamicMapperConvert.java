package com.ddf.better.together.convert.mapper;

import com.ddf.better.together.model.query.SearchUserDynamicQuery;
import com.ddf.better.together.model.request.SearchUserDynamicRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>用户动态相关对象转换</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/11/10 15:56
 */
@Mapper
public interface UserDynamicMapperConvert {

    UserDynamicMapperConvert INSTANCE = Mappers.getMapper(UserDynamicMapperConvert.class);

    /**
     * 动态查询参数
     *
     * @param request
     * @return
     */
    SearchUserDynamicQuery convert(SearchUserDynamicRequest request);
}
