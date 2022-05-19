package com.autumn.common.mapper;

import com.autumn.platform.model.AuthRoleExt;
import com.autumn.platform.model.Auth_role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: omplatform->ManagerMapper
 * @description: ${description}
 * @author: 秋雨
 * @createTime: 2020-07-13 16:43
 **/
public interface CommonMapper {

    /**
     * 分页查询列表
     * @param auth_role
     * @param currPage
     * @param pageSize
     * @return
     */
    List<AuthRoleExt> selectRoleByPage(@Param("role") Auth_role auth_role, @Param("currPage")Integer currPage, @Param("pageSize")Integer pageSize);

    /**
     * 查询所有
     * @param auth_role
     * @return
     */
    List<AuthRoleExt> selectRoleList(@Param("role") Auth_role auth_role);

    /**
     * 查询总数
     * @param auth_role
     * @return
     */
    String selectAllCount(@Param("role") Auth_role auth_role);
}
