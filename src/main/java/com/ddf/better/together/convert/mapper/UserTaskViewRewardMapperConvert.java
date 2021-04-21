package com.ddf.better.together.convert.mapper;

import com.ddf.better.together.model.entity.UserTaskDefinitionReward;
import com.ddf.better.together.model.entity.UserTaskViewReward;
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
public interface UserTaskViewRewardMapperConvert {

    UserTaskViewRewardMapperConvert INSTANCE = Mappers.getMapper(UserTaskViewRewardMapperConvert.class);

    /**
     * 任务定义奖励转换
     *
     * @param userTaskDefinitionReward
     * @return
     */
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    UserTaskViewReward convert(UserTaskDefinitionReward userTaskDefinitionReward);
}