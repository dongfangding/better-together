package com.ddf.better.together.convert.mapper;

import com.ddf.better.together.model.entity.UserPartnerApply;
import com.ddf.better.together.model.response.UserPartnerApplyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/02 15:44
 */
@Mapper
public interface UserPartnerApplyMapperConvert {

    UserPartnerApplyMapperConvert INSTANCE = Mappers.getMapper(UserPartnerApplyMapperConvert.class);


    /**
     * 用户属性转换
     *
     * @param apply
     * @return
     */
    @Mappings({
            @Mapping(target = "typeName", expression = "java(com.ddf.better.together.constants.enumration.UserPartnerApplyTypeEnum.instanceOfCodeDefaultUnknown(apply.getType()).getDesc())"),
            @Mapping(target = "statusName", expression = "java(com.ddf.better.together.constants.enumration.UserPartnerApplyStatusEnum.instanceOfCodeDefaultUnknown(apply.getStatus()).getDesc())"),
    })
    UserPartnerApplyResponse convert(UserPartnerApply apply);
}
