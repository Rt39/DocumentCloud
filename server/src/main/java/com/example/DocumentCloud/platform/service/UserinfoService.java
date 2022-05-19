package com.autumn.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.autumn.platform.config.UserinfoConfig;
import com.autumn.platform.mapper.Auth_userjoinroleMapper;
import com.autumn.platform.mapper.UserinfoMapper;
import com.autumn.platform.model.Auth_userjoinrole;
import com.autumn.platform.model.Auth_userjoinroleExample;
import com.autumn.platform.model.Auth_userjoinroleExt;
import com.autumn.platform.model.Userinfo;
import com.autumn.platform.model.UserinfoExample;
import com.autumn.system.dao.CommonDao;
import com.autumn.system.entitys.PageList;
import com.autumn.util.CastUtil;
import com.autumn.util.StringUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class UserinfoService {
	
	@Autowired
	UserinfoMapper userinfoMapper;

	@Autowired
	Auth_userjoinroleMapper userjoinroleMapper;

	@Resource(name="commonDao")
	CommonDao dao;
	
	/**
	 * mybatis新增操作
	 * @param record
	 * @return
	 */
    public int insertSelective(Userinfo record){
    	return userinfoMapper.insertSelective(record);
    }
    
    /**
     * mybatis删除操作
     * @param userid
     * @return
     */
    public int deleteByPrimaryKey(Integer userid){
    	return userinfoMapper.deleteByPrimaryKey(userid);
    }

    /**
     * mybatis修改操作
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Userinfo record){
    	return userinfoMapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * mybatis查询所有list
     * @param example
     * @return
     */
    public List<Userinfo> selectByExample(UserinfoExample example){
    	return userinfoMapper.selectByExample(example);
    }
    
    /**
     * mybatis根据userid查询mybatis
     * @param userid
     * @return
     */
    public Userinfo selectByPrimaryKey(Integer userid){
    	return userinfoMapper.selectByPrimaryKey(userid);
    }
    
    /**
     * mybatis分页查询
     * @return
     */
    public PageList getListByPage(Userinfo userinfo,int currPage,int pageSize){
    	String sql = UserinfoConfig.getAllByPage;
    	StringBuffer whereBuffer = new StringBuffer();
    	if (StringUtil.isNotEmpty(userinfo.getUsername())) {
    		whereBuffer.append("and userName like '%"+userinfo.getUsername()+"%'");
		}
    	if (StringUtil.isNotEmpty(userinfo.getCnname())) {
    		whereBuffer.append("and cnName like '%"+userinfo.getCnname()+"%'");
    	}
    	if (StringUtil.isNotEmpty(CastUtil.castString(userinfo.getIsActive()))) {
    		whereBuffer.append("and is_active = '"+userinfo.getIsActive()+"'");
    	}
    	if (StringUtil.isNotEmpty(CastUtil.castString(userinfo.getPhone()))) {
    		whereBuffer.append("and phone like '%"+userinfo.getPhone()+"%'");
    	}
    	if (StringUtil.isNotEmpty(CastUtil.castString(userinfo.getIdcard()))) {
    		whereBuffer.append("and idcard like '%"+userinfo.getIdcard()+"%'");
    	}
    	sql=sql.replace("#where", whereBuffer.toString());
    	PageList pageList = dao.queryByPageForMySQL(sql, new Object[]{}, currPage, pageSize, Userinfo.class);
    	return pageList;
    }
    
    /**
     * 根据ids激活
     * @param ids 待激活的userid
     * @return
     */
    public boolean activeByIds(String ids){
    	String sql = UserinfoConfig.activeByIds;
    	sql += "where userId in("+ids+")";
    	return dao.execute(sql, new Object[]{});
    }
    
    /**
     * 根据ids禁用
     * @param ids
     * @return
     */
    public boolean disableByIds(String ids){
    	String sql = UserinfoConfig.disableByIds;
    	sql += "where userId in("+ids+")";
    	return dao.execute(sql, new Object[]{});
    }
    
    /**
     * 删除用户
     * @param ids
     * @return
     */
    public boolean deleteByIds(String ids){
    	String sql = UserinfoConfig.deleteByIds;
    	sql += "where userId in("+ids+")";
    	return dao.execute(sql, new Object[]{});
    }

    /**
     * 分配所有角色
     * @param userid
     * @return
     */
    public List getAllcheckBoxRole(String userid){
    	String sql = UserinfoConfig.getAllcheckBoxRole;
    	return dao.queryForObjectList(sql, new Object[]{userid}, Auth_userjoinroleExt.class);
    }
    
    /**
     * 删除当前用户所有权限
     * @param example
     * @return
     */
    public int delallotRoles(Auth_userjoinroleExample example){
    	return userjoinroleMapper.deleteByExample(example);
    }
    /**
     * 分配权限
     * @param record
     * @return
     */
    public int allotRoles(Auth_userjoinrole record){
    	return userjoinroleMapper.insertSelective(record);
    }

	public List<Userinfo> selectAllByPage(UserinfoExample example, Integer currPage, Integer pageSize){
		return userinfoMapper.selectAllByPage(example,currPage,pageSize);
	}

}
