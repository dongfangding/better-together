package com.ddf.better.together.convert.mapper;

import com.ddf.better.together.model.dto.UserInfoDTO;
import com.ddf.better.together.model.entity.UserInfo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/02 13:17
 */
@Mapper
public interface UserInfoMapperConvert {

    UserInfoMapperConvert INSTANCE = Mappers.getMapper(UserInfoMapperConvert.class);

    /**
     * 用户属性转换
     *
     * @param userInfo
     * @return
     */
    @Mappings({})
    UserInfoDTO convert(UserInfo userInfo);

    /**
     * 用户属性转换
     *
     * @param userInfo
     * @return
     */
    @Mappings({})
    List<UserInfoDTO> convert(List<UserInfo> userInfo);
}
