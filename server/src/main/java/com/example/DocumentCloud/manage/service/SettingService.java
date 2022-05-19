package com.autumn.manage.service;

import com.autumn.manage.mapper.ManagerMapper;
import com.autumn.manage.mapper.SettingMapper;
import com.autumn.manage.model.Setting;
import com.autumn.manage.model.SettingExample;
import com.autumn.system.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 参数配置
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class SettingService {

	@Autowired
	ManagerMapper managerMapper;

	@Autowired
	SettingMapper mapper;

	@Resource(name="commonDao")
    CommonDao dao;
	
	/**
	 * mybatis新增操作
	 * @param record
	 * @return
	 */
    public int insertSelective(Setting record){
    	return mapper.insertSelective(record);
    }
    
    /**
     * mybatis删除操作
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Integer id){
    	return mapper.deleteByPrimaryKey(id);
    }

    /**
     * mybatis修改操作
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Setting record){
    	return mapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * mybatis查询所有list
     * @param example
     * @return
     */
    public List<Setting> selectByExample(SettingExample example){
    	return mapper.selectByExample(example);
    }
    
    /**
     * mybatis根据id查询mybatis
     * @param id
     * @return
     */
    public Setting selectByPrimaryKey(Integer id){
    	return mapper.selectByPrimaryKey(id);
    }

	/**
	 * 获取总数
	 * @param example
	 * @return
	 */
	public long countByExample(SettingExample example){
    	return mapper.countByExample(example);
	}

    /**
     * mybatis分页查询
     * @return
     */
    public List getListByPage(Setting info, Integer currPage, Integer pageSize){
		return managerMapper.selectSettingByPage(info,currPage,pageSize);
	}

	/**
	 * 获取总数
	 * @param info
	 * @return
	 */
	public String selectCount(Setting info){
		return managerMapper.selectSettingCount(info);
	}

	/**
	 * 获取配置表所有配置
	 * @param keywords
	 * @return
	 */
	public List getSettingType(String keywords){
		return managerMapper.getSettingType(keywords);
	}
    
}
