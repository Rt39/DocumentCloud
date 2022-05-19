package com.autumn.platform.mapper;

import com.autumn.platform.model.Auth_modulejoinrole;
import com.autumn.platform.model.Auth_modulejoinroleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Auth_modulejoinroleMapper {
    long countByExample(Auth_modulejoinroleExample example);

    int deleteByExample(Auth_modulejoinroleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth_modulejoinrole record);

    int insertSelective(Auth_modulejoinrole record);

    List<Auth_modulejoinrole> selectByExample(Auth_modulejoinroleExample example);

    Auth_modulejoinrole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth_modulejoinrole record, @Param("example") Auth_modulejoinroleExample example);

    int updateByExample(@Param("record") Auth_modulejoinrole record, @Param("example") Auth_modulejoinroleExample example);

    int updateByPrimaryKeySelective(Auth_modulejoinrole record);

    int updateByPrimaryKey(Auth_modulejoinrole record);
}