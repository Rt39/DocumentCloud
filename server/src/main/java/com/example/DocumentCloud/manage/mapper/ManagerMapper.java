package com.autumn.manage.mapper;

import com.autumn.manage.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: omplatform->ManagerMapper
 * @description: ${description}
 * @author: 秋雨
 * @createTime: 2020-07-13 16:43
 **/
public interface ManagerMapper {
    /*Setting 配置*/
    List<Setting> selectSettingByPage(@Param("i") Setting info, @Param("currPage")Integer currPage, @Param("pageSize")Integer pageSize);
    String selectSettingCount(@Param("i") Setting info);
    /*获取Setting下的所有type*/
    List<Setting> getSettingType(@Param("keywords") String keywords);


    /*person 人员表*/
    List selectPersonByPage(@Param("i") Person info, @Param("currPage")Integer currPage, @Param("pageSize")Integer pageSize);
    String selectPersonCount(@Param("i") Person info);
}
