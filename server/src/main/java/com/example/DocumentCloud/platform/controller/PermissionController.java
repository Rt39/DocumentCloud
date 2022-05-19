package com.autumn.platform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.autumn.platform.model.Auth_module;
import com.autumn.platform.model.Auth_moduleExample;
import com.autumn.platform.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.autumn.system.tools.CN_MessageEnum;


@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
	PermissionService service;
	

	@RequestMapping(value="/i",method={RequestMethod.GET,RequestMethod.POST})
    public String userInfo(){
    	return "/WEB-INF/jsp/system/permission";
    }
	
	/**
	 * mybatis新增操作
	 * TODO
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/insertSelective",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object insertSelective(Auth_module record){
		System.out.println(record.toString());
		int result = service.insertSelective(record);
		Map map = new HashMap();
		if (result == 1) {
			map.put("code", "success");
			map.put("msg", CN_MessageEnum.AddSuccess.getName());
		}else {
			map.put("code", "fail");
			map.put("msg", CN_MessageEnum.AddFailed.getName());
		}
		return map;
    }
    
    /**
     * mybatis删除操作
     * @param id
     * @return
     */
	@RequestMapping(value="/deleteByPrimaryKey",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Map deleteByPrimaryKey(Integer id){
		Map map = new HashMap();
		Auth_moduleExample example = new Auth_moduleExample();
		example.createCriteria().andParentidEqualTo(id);
		List<Auth_module> auth_modules = service.selectByExample(example);
		if (auth_modules !=null && auth_modules.size()>0){
			map.put("code", "fail");
			map.put("msg", "该节点下存在子节点，无法删除！");
			return map;
		}
		int result = service.deleteByPrimaryKey(id);
		if (result == 1) {
			map.put("code", "success");
			map.put("msg", CN_MessageEnum.DeleteSuccess.getName());
		}else {
			map.put("code", "fail");
			map.put("msg", CN_MessageEnum.DeleteFailed.getName());
		}
		return map;
    }

    /**
     * mybatis修改操作
     * @param record
     * @return
     */
	@RequestMapping(value="/updateByPrimaryKeySelective",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object updateByPrimaryKeySelective(Auth_module record){
		int result = service.updateByPrimaryKeySelective(record);
		Map map = new HashMap();
		if (result == 1) {
			map.put("code", "success");
			map.put("msg", CN_MessageEnum.ModifySuccess.getName());
		}else {
			map.put("code", "fail");
			map.put("msg", CN_MessageEnum.ModifyFailed.getName());
		}
    	return map;
    }
	
    /**
     * mybatis查询所有list
     * @param example
     * @return
     */
	@RequestMapping(value="/selectByExample",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    List<Auth_module> selectByExample(Auth_moduleExample example){
    	return service.selectByExample(example);
    }
    
    /**
     * mybatis根据id查询mybatis
     * @param id
     * @return
     */
	@RequestMapping(value="/selectByPrimaryKey",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Auth_module selectByPrimaryKey(Integer id){
    	return service.selectByPrimaryKey(id);
    }

	/**
	 * 返回树形结构
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectPermissionTree",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List selectPermissionTree() throws Exception {
		return service.selectPermissionTree();
	}

	/**
	 * 分配角色回显
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectPermissionTree_role",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List selectPermissionTree_role(String roleid) throws Exception {
		return service.selectPermissionTree_role(roleid);
	}

	/**
	 * 添加角色和菜单的关系映射
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addmodulejoinrole",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map addmodulejoinrole(@RequestParam List<String> moduleids, String roleId) throws Exception {
		boolean result = service.addmodulejoinrole(moduleids.toArray(),roleId);
		Map map = new HashMap();
		if (result) {
			map.put("code", "success");
			map.put("msg", CN_MessageEnum.OperatSuccess.getName());
		}else {
			map.put("code", "fail");
			map.put("msg", CN_MessageEnum.OperatFailed.getName());
		}
		return map;
	}

}
