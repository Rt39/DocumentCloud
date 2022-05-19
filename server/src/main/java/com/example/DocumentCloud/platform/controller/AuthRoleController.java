package com.autumn.platform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.autumn.platform.model.Auth_role;
import com.autumn.platform.model.Auth_roleExample;
import com.autumn.platform.model.Userinfo;
import com.autumn.platform.service.AuthRoleService;
import com.autumn.system.entitys.PageList;
import com.autumn.system.tools.CN_MessageEnum;
import com.autumn.util.StringUtil;

/**
 * @ClassName: AuthRoleController
 * @Description: TODO
 * @author 秋雨
 * 修改历史 
 *  序号------原因------修改人---日期---
 *   1.                               
 *   2.                                
 */
@Controller
@RequestMapping("/authrole")
public class AuthRoleController {
	
	@Autowired
	AuthRoleService service;
	

	@RequestMapping(value="/i",method={RequestMethod.GET,RequestMethod.POST})
    public String auth_role(){
    	return "/WEB-INF/jsp/system/Role";
    }
	
	/**
	 * mybatis新增操作
	 * TODO
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/insertSelective",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object insertSelective(Auth_role record,HttpSession session){
		Userinfo sessionUserinfo = (Userinfo)session.getAttribute("CURR_USER");
		if (sessionUserinfo != null) {
			String sessionuserid = sessionUserinfo.getUserid().toString();
			if (StringUtil.isNotEmpty(sessionuserid)) {
				record.setAdduser(Integer.parseInt(sessionuserid));
			}
		}
		Map map = new HashMap();
		int result = 0;
		/*异常处理*/
		try {
			result = service.insertSelective(record);
		}catch (org.springframework.dao.DuplicateKeyException e){
			/*异常处理截获流程,如果是存在DuplicateKeyException则是重名,id主键为自增长,不会重复*/
			map.put("code", "fail");
			map.put("msg", "该角色名已存在!");
			return map;
		}
		/*正常返回流程*/
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
		int result = service.deleteByPrimaryKey(id);
		Map map = new HashMap();
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
	public Object updateByPrimaryKeySelective(Auth_role record){
		int result = 0;
		Map map = new HashMap();

		try {
			result = service.updateByPrimaryKeySelective(record);
		}catch (org.springframework.dao.DuplicateKeyException e){
			/*异常处理截获流程,如果是存在DuplicateKeyException则是重名,id主键为自增长,不会重复*/
			map.put("code", "fail");
			map.put("msg", "该角色名已存在!");
			return map;
		}
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
    List<Auth_role> selectByExample(Auth_roleExample example){
    	return service.selectByExample(example);
    }
    
    /**
     * mybatis根据id查询mybatis
     * @param id
     * @return
     */
	@RequestMapping(value="/selectByPrimaryKey",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Auth_role selectByPrimaryKey(Integer id){
    	return service.selectByPrimaryKey(id);
    }
    
    /**
     * 分页查询 -- jdbctemplate方式
     * @return
     */
	@RequestMapping(value="/getListByPage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object getListByPage(Auth_role info,@RequestParam("page") Integer currPage,@RequestParam("limit") Integer pageSize){
		if (currPage == null) {
			currPage = 0;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		Map map = new HashMap();
		PageList pageList = service.getListByPage(info,currPage,pageSize);
		map.put("code", 0);
		map.put("count", pageList.getTotalRows());
		map.put("msg", "");
		map.put("data", pageList.getList());
    	return map;
    }


	/**
	 * 分页查询 - mybatis方式
	 * @return
	 */
	@RequestMapping(value="/getListByPage_mybatis",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object getListByPage_mybatis(Auth_role info,@RequestParam("page") Integer currPage,@RequestParam("limit") Integer pageSize){
		if (currPage == null) {
			currPage = 0;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		Map map = new HashMap();
		List list = service.mybatis_getListByPage(info,currPage,pageSize);
		String count = service.mybatis_getAllCount(info);
		map.put("code", 0);
		map.put("count",count);
		map.put("msg", "");
		map.put("data", list);
		return map;
	}

	/**
	 * mybatis获取列表
	 * @param info
	 * @return
	 */
	@RequestMapping(value="/getList_mybatis",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List getList_mybatis(Auth_role info){
		Map map = new HashMap();
		List r = service.mybatis_getList(info);
		return r;
	}
}
