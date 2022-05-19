package com.autumn.platform.mapper;

import com.autumn.platform.model.Auth_module;
import com.autumn.platform.model.Auth_moduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Auth_moduleMapper {
    long countByExample(Auth_moduleExample example);

    int deleteByExample(Auth_moduleExample example);

    int deleteByPrimaryKey(Integer moduleid);

    int insert(Auth_module record);

    int insertSelective(Auth_module record);

    List<Auth_module> selectByExample(Auth_moduleExample example);

    Auth_module selectByPrimaryKey(Integer moduleid);

    int updateByExampleSelective(@Param("record") Auth_module record, @Param("example") Auth_moduleExample example);

    int updateByExample(@Param("record") Auth_module record, @Param("example") Auth_moduleExample example);

    int updateByPrimaryKeySelective(Auth_module record);

    int updateByPrimaryKey(Auth_module record);
}