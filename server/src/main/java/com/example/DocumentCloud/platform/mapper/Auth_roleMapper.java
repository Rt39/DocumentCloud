package com.autumn.platform.mapper;

import com.autumn.platform.model.Auth_role;
import com.autumn.platform.model.Auth_roleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Auth_roleMapper {
    long countByExample(Auth_roleExample example);

    int deleteByExample(Auth_roleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth_role record);

    int insertSelective(Auth_role record);

    List<Auth_role> selectByExample(Auth_roleExample example);

    Auth_role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth_role record, @Param("example") Auth_roleExample example);

    int updateByExample(@Param("record") Auth_role record, @Param("example") Auth_roleExample example);

    int updateByPrimaryKeySelective(Auth_role record);

    int updateByPrimaryKey(Auth_role record);
}