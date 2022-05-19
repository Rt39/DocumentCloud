package com.autumn.manage.controller;

import com.autumn.manage.model.Person;
import com.autumn.manage.model.PersonExample;
import com.autumn.manage.service.PersonService;
import com.autumn.system.tools.CN_MessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前端人员配置
 */
@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonService service;
	

	@RequestMapping(value="/i",method={RequestMethod.GET,RequestMethod.POST})
    public String index(){
    	return "/WEB-INF/jsp/manage/person";
    }
	
	/**
	 * mybatis新增操作
	 * TODO
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/insertSelective",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object insertSelective(Person record, HttpSession session){
		Map map = new HashMap();
		int result = 0;
		/*异常处理*/
		try {
			result = service.insertSelective(record);
		}catch (org.springframework.dao.DuplicateKeyException e){
			/*异常处理截获流程,如果是存在DuplicateKeyException则是重名,id主键为自增长,不会重复*/
			map.put("code", "fail");
			map.put("msg", "该人员编码已存在!");
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
	public Object updateByPrimaryKeySelective(Person record){
		int result = 0;
		Map map = new HashMap();

		try {
			result = service.updateByPrimaryKeySelective(record);
		}catch (org.springframework.dao.DuplicateKeyException e){
			/*异常处理截获流程,如果是存在DuplicateKeyException则是重名,id主键为自增长,不会重复*/
			map.put("code", "fail");
			map.put("msg", "该人员编码已存在!");
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
	List<Person> selectByExample(PersonExample example){
    	return service.selectByExample(example);
    }
    
    /**
     * mybatis根据id查询mybatis
     * @param id
     * @return
     */
	@RequestMapping(value="/selectByPrimaryKey",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Person selectByPrimaryKey(Integer id){
    	return service.selectByPrimaryKey(id);
    }
    
    /**
     * 分页查询
     * @return
     */
	@RequestMapping(value="/getListByPage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object getListByPage(Person info,@RequestParam("page") Integer currPage,@RequestParam("limit") Integer pageSize){
		if (currPage == null) {
			currPage = 0;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		Map map = new HashMap();
		List pageList = service.getListByPage(info,currPage,pageSize);
		String count = service.selectCount(info);
		map.put("code", 0);
		map.put("count", count);
		map.put("msg", "");
		map.put("data", pageList);
    	return map;
    }

}
