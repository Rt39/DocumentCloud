package com.autumn.platform.mapper;

import com.autumn.platform.model.Auth_userjoinrole;
import com.autumn.platform.model.Auth_userjoinroleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Auth_userjoinroleMapper {
    long countByExample(Auth_userjoinroleExample example);

    int deleteByExample(Auth_userjoinroleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth_userjoinrole record);

    int insertSelective(Auth_userjoinrole record);

    List<Auth_userjoinrole> selectByExample(Auth_userjoinroleExample example);

    Auth_userjoinrole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth_userjoinrole record, @Param("example") Auth_userjoinroleExample example);

    int updateByExample(@Param("record") Auth_userjoinrole record, @Param("example") Auth_userjoinroleExample example);

    int updateByPrimaryKeySelective(Auth_userjoinrole record);

    int updateByPrimaryKey(Auth_userjoinrole record);
}