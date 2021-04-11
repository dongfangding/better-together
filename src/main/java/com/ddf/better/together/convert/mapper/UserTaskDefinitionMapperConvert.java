package com.ddf.better.together.convert.mapper;

import com.ddf.better.together.model.entity.UserTaskDefinition;
import com.ddf.better.together.model.request.DefinitionTaskRequest;
import com.ddf.better.together.model.response.UserTaskDefinitionResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    /**
     * 实体转换回响应类
     *
     * @param userTaskDefinition
     * @return
     */
    @Mappings({
            @Mapping(target = "cycleName", expression = "java(com.ddf.better.together.constants.enumeration.UserTaskCycleEnum.instanceOfCodeDefaultUnknown(userTaskDefinition.getCycle()).getDesc())"),
            @Mapping(target = "rewardTypeName", expression = "java(com.ddf.better.together.constants.enumeration.UserTaskRewardTypeEnum.instanceOfCodeDefaultUnknown(userTaskDefinition.getRewardType()).getDesc())"),
    })
    UserTaskDefinitionResponse convert(UserTaskDefinition userTaskDefinition);

    /**
     * 实体转换回响应类
     *
     * @param userTaskDefinition
     * @return
     */
    @Mappings({})
    List<UserTaskDefinitionResponse> convert(List<UserTaskDefinition> userTaskDefinition);
}
