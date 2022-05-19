package com.autumn.platform.service;

import com.autumn.platform.config.PermissionConfig;
import com.autumn.platform.mapper.Auth_moduleMapper;
import com.autumn.platform.mapper.Auth_modulejoinroleMapper;
import com.autumn.platform.model.*;
import com.autumn.system.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class PermissionService {
	
	@Autowired
	Auth_moduleMapper moduleMapper;

	@Autowired
	Auth_modulejoinroleMapper modulejoinroleMapper;

	@Resource(name="commonDao")
	CommonDao dao;
	
	/**
	 * mybatis新增操作
	 * @param record
	 * @return
	 */
    public int insertSelective(Auth_module record){
    	return moduleMapper.insertSelective(record);
    }
    
    /**
     * mybati删除操作
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Integer id){
    	return moduleMapper.deleteByPrimaryKey(id);
    }

    /**
     * mybatis修改操作
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Auth_module record){
    	return moduleMapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * mybatis查询所有list
     * @param example
     * @return
     */
    public List<Auth_module> selectByExample(Auth_moduleExample example){
    	return moduleMapper.selectByExample(example);
    }
    
    /**
     * mybatis根据id查询mybatis
     * @param id
     * @return
     */
    public Auth_module selectByPrimaryKey(Integer id){
    	return moduleMapper.selectByPrimaryKey(id);
    }

	/**
	 * 获取module的tree型目录
	 * @return
	 */
	public List<PermissionTreemodule> selectPermissionTree() throws Exception {
		List<PermissionTreemodule> result = new ArrayList<PermissionTreemodule>();
		//查询库中所有module的list
		String sql = PermissionConfig.getPermissionTree;
		List<PermissionTreemodule> modulelist = dao.queryForObjectList(sql,new Object[]{},PermissionTreemodule.class);
		/*设置根结点*/
		PermissionTreemodule root = new PermissionTreemodule();
		root.setId(0);
		root.setHref("");
		root.setTitle("模块设置");
		root.setSpread(true);
		root.setDisabled(true);
		//找到id为0的菜单的下级结点,并添加到root结点的children中
		if (modulelist != null) {
			for (int i = 0; i < modulelist.size(); i++) {
				PermissionTreemodule m = modulelist.get(i);
				if (m.getParentId()!=null && m.getParentId().equals(0)){  //如果是根结点
					m.setSpread(true);
					resetModules(m,modulelist);  //给m添加子结点
					root.getChildren().add(m);
				}
			}
		}else {
			throw new Exception("modulelist为空");
		}
		result.add(root);
		return result;
	}

	/**
	 * 为m设置子节点
	 * @param m  父节点
	 * @param ms 在ms中找到m的子节点
	 */
	private void resetModules(PermissionTreemodule m,List<PermissionTreemodule> ms){
		for(int i=0;i<ms.size();i++){   //循环modules
			PermissionTreemodule c=ms.get(i);    //获取module
			if (c.getParentId()!=null) {  //当module的父code不为空时,即是子菜单
				c.setSpread(true);
				if(c.getParentId().equals(m.getId())){   //如果module的的父code等于m的code,也就是遍历到的module是m的子菜单
					m.getChildren().add(c);   //直接加入到m
					resetModules(c,ms);    //把这个二级菜单c加入到m中
				}
			}
		}
	}

	/**
	 * 添加角色,模块之间关系
	 * @param moduleids
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean addmodulejoinrole(Object[] moduleids,String roleId) throws Exception {
		Auth_modulejoinrole modulejoinrole = null;
		//先删除
		Auth_modulejoinroleExample example = new Auth_modulejoinroleExample();
		example.createCriteria().andRoleidEqualTo(Integer.parseInt(roleId));
		int result_del = modulejoinroleMapper.deleteByExample(example);

		//再添加
		int result_add = 0;
		for(Object moduleid : moduleids){
			modulejoinrole = new Auth_modulejoinrole();
			modulejoinrole.setModuleid(Integer.parseInt(moduleid.toString()));
			modulejoinrole.setRoleid(Integer.parseInt(roleId));
			int r = modulejoinroleMapper.insertSelective(modulejoinrole);
			result_add += r;
		}
		if (result_del>0||result_add>0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 角色配置权限树回显
	 * @return
	 * @throws Exception
	 */
	public List<PermissionTreemodule> selectPermissionTree_role(String roleid) throws Exception {
		List<PermissionTreemodule> result = new ArrayList<PermissionTreemodule>();
		//查询库中所有module的list
		String sql = PermissionConfig.selectPermissionTree_role;
		List<PermissionTreemodule> modulelist = dao.queryForObjectList(sql,new Object[]{roleid},PermissionTreemodule.class);
		/*设置根结点*/
		PermissionTreemodule root = new PermissionTreemodule();
		root.setId(0);
		root.setHref("");
		root.setTitle("模块设置");
		root.setSpread(true);
		root.setDisabled(true);
		//找到id为0的菜单的下级结点,并添加到root结点的children中
		if (modulelist != null) {
			for (int i = 0; i < modulelist.size(); i++) {
				PermissionTreemodule m = modulelist.get(i);
				if (m.getParentId()!=null && m.getParentId().equals(0)){  //如果是根结点
					m.setSpread(true);
					resetModules(m,modulelist);  //给m添加子结点
					root.getChildren().add(m);
				}
			}
		}else {
			throw new Exception("modulelist为空");
		}
		result.add(root);
		return result;
	}
}
