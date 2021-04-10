package com.ddf.better.together.convert.mapper;

import com.ddf.better.together.model.entity.UserTaskDefinition;
import com.ddf.better.together.model.request.DefinitionTaskRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * <p>用户任务定义属性拷贝类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/10 15:22
 */
@Mapper
public interface UserTaskDefinitionMapperConvert {

    UserTaskDefinitionMapperConvert INSTANCE = Mappers.getMapper(UserTaskDefinitionMapperConvert.class);

    /**
     * 任务定义参数转换
     *
     * @param request
     * @return
     */
    @Mappings({})
    UserTaskDefinition convert(DefinitionTaskRequest request);
}
