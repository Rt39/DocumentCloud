package com.autumn.platform.service;

import java.util.List;

import javax.annotation.Resource;

import com.autumn.common.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.autumn.platform.config.AuthRoleConfig;
import com.autumn.platform.mapper.Auth_roleMapper;
import com.autumn.platform.model.AuthRoleExt;
import com.autumn.platform.model.Auth_role;
import com.autumn.platform.model.Auth_roleExample;
import com.autumn.system.dao.CommonDao;
import com.autumn.system.entitys.PageList;
import com.autumn.util.StringUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class AuthRoleService {
	
	@Autowired
	Auth_roleMapper roleMapper;

	@Resource(name="commonDao")
	CommonDao dao;

	@Autowired
	CommonMapper commonMapper;
	
	/**
	 * mybatis新增操作
	 * TODO
	 * @param record
	 * @return
	 */
    public int insertSelective(Auth_role record){
    	return roleMapper.insertSelective(record);
    }
    
    /**
     * mybatis修改操作
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Integer id){
    	return roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * mybatis删除操作
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Auth_role record){
    	return roleMapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * mybatis查询所有list
     * @param example
     * @return
     */
    public List<Auth_role> selectByExample(Auth_roleExample example){
    	return roleMapper.selectByExample(example);
    }
    
    /**
     * mybatis根据roleid查询mybatis
     * @param roleid
     * @return
     */
    public Auth_role selectByPrimaryKey(Integer roleid){
    	return roleMapper.selectByPrimaryKey(roleid);
    }
    
    /**
     * 分页查询 -- jdbctemplate方式
     * @param role
     * @return
     */
    public PageList getListByPage(Auth_role role,int currPage,int pageSize){
    	String sql = AuthRoleConfig.getAllByPage;
    	StringBuffer whereBuffer = new StringBuffer();
    	if (StringUtil.isNotEmpty(role.getRolename())) {
    		whereBuffer.append("where rolename like '%"+role.getRolename()+"%'");
		}
    	sql=sql.replace("#where", whereBuffer.toString());
    	PageList pageList = dao.queryByPageForMySQL(sql, new Object[]{}, currPage, pageSize, AuthRoleExt.class);
    	return pageList;
    }

	/**
	 * mybatis分页查询 -- mybatis拦截器方式
	 * @param role
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public List mybatis_getListByPage(Auth_role role,int currPage,int pageSize){
		List<AuthRoleExt>  list = commonMapper.selectRoleByPage(role,currPage,pageSize);
		return list;
	}

	/**
	 * mybatis获取所有
	 * @param role
	 * @return
	 */
	public List mybatis_getList(Auth_role role){
		List<AuthRoleExt>  list = commonMapper.selectRoleList(role);
		return list;
	}

	/**
	 * 获取
	 * @param role
	 * @return
	 */
	public String mybatis_getAllCount(Auth_role role){
		String result = commonMapper.selectAllCount(role);
		return result;
	}

}
