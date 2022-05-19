package com.autumn.manage.service;

import com.autumn.manage.mapper.ManagerMapper;
import com.autumn.manage.mapper.PersonMapper;
import com.autumn.manage.model.Person;
import com.autumn.manage.model.PersonExample;
import com.autumn.system.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class PersonService {

	@Autowired
	ManagerMapper managerMapper;

	@Autowired
	PersonMapper mapper;

	@Resource(name="commonDao")
	CommonDao dao;
	
	/**
	 * mybatis新增操作
	 * TODO
	 * @param record
	 * @return
	 */
    public int insertSelective(Person record){
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
    public int updateByPrimaryKeySelective(Person record){
    	return mapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * mybatis查询所有list
     * @param example
     * @return
     */
    public List<Person> selectByExample(PersonExample example){
    	return mapper.selectByExample(example);
    }
    
    /**
     * mybatis根据id查询mybatis
     * @param id
     * @return
     */
    public Person selectByPrimaryKey(Integer id){
    	return mapper.selectByPrimaryKey(id);
    }

	/**
	 * 获取总数
	 * @param example
	 * @return
	 */
	public long countByExample(PersonExample example){
    	return mapper.countByExample(example);
	}

    /**
     * mybatis分页查询
     * @return
     */
    public List getListByPage(Person info,Integer currPage,Integer pageSize){
		return managerMapper.selectPersonByPage(info,currPage,pageSize);
	}

	/**
	 * 查询列表
	 * @param person
	 * @return
	 */
	public String selectCount(Person person){
		return managerMapper.selectPersonCount(person);
	}
    
}
