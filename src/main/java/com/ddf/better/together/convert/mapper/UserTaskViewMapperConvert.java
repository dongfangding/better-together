package com.ddf.better.together.convert.mapper;

import com.ddf.better.together.model.entity.UserTaskDefinition;
import com.ddf.better.together.model.entity.UserTaskView;
import com.ddf.better.together.model.response.UserTaskViewResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * <p>任务视图</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/11 23:09
 */
@Mapper
public interface UserTaskViewMapperConvert {

    UserTaskViewMapperConvert INSTANCE = Mappers.getMapper(UserTaskViewMapperConvert.class);

    /**
     * 任务定义转换
     *
     * @param userTaskDefinition
     * @return
     */
    @Mappings({})
    UserTaskView convert(UserTaskDefinition userTaskDefinition);

    /**
     * 实体转换回响应类
     *
     * @param userTaskView
     * @return
     */
    @Mappings({
            @Mapping(target = "cycleName", expression = "java(com.ddf.better.together.constants.enumeration.UserTaskCycleEnum.instanceOfCodeDefaultUnknown(userTaskView.getCycle()).getDesc())"),
            @Mapping(target = "rewardTypeName", expression = "java(com.ddf.better.together.constants.enumeration.UserTaskRewardTypeEnum.instanceOfCodeDefaultUnknown(userTaskView.getRewardType()).getDesc())"),
            @Mapping(target = "statusName", expression = "java(com.ddf.better.together.constants.enumeration.UserTaskViewStatusEnum.instanceOfCodeDefaultUnknown(userTaskView.getStatus()).getDesc())"),
    })
    UserTaskViewResponse convert(UserTaskView userTaskView);

    /**
     * 列表转换
     *
     * @param userTaskView
     * @return
     */
    @Mappings({})
    List<UserTaskViewResponse> convert(List<UserTaskView> userTaskView);
}
