package com.ddf.better.together.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddf.better.together.model.dto.UserDynamicDTO;
import com.ddf.better.together.model.entity.UserDynamic;
import com.ddf.better.together.model.query.SearchUserDynamicQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户动态 Mapper 接口
 * </p>
 *
 * @author mybatis-plus-generator
 * @since 2021-04-04
 */
public interface UserDynamicMapper extends BaseMapper<UserDynamic> {


    /**
     * 分页查询用户对象信息
     *
     * @param query
     * @param page
     * @return
     */
    Page<UserDynamicDTO> searchUserDynamic(@Param("query") SearchUserDynamicQuery query, Page<UserDynamicDTO> page);
}
