package com.ddf.better.together.convert.mapper;

import com.ddf.better.together.model.entity.UserTaskDefinition;
import com.ddf.better.together.model.entity.UserTaskView;
import org.mapstruct.Mapper;
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
}
