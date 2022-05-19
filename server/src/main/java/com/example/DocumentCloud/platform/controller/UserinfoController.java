package com.autumn.platform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.autumn.platform.mapper.Auth_userjoinroleMapper;
import com.autumn.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.autumn.platform.model.Auth_userjoinrole;
import com.autumn.platform.model.Auth_userjoinroleExample;
import com.autumn.platform.model.Userinfo;
import com.autumn.platform.model.UserinfoExample;
import com.autumn.platform.service.UserinfoService;
import com.autumn.system.entitys.PageList;
import com.autumn.system.tools.CN_MessageEnum;
import com.autumn.system.tools.MD5Util;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: UserinfoController
 * @Description: TODO
 * @author 秋雨
 * 修改历史 
 *  序号------原因------修改人---日期---
 *   1.                               
 *   2.                                
 */
@Controller
@RequestMapping("/userinfo")
public class UserinfoController {
	
	@Autowired
	UserinfoService userinfoService;

	@Autowired
	Auth_userjoinroleMapper userjoinroleService;

	@RequestMapping(value="/i",method={RequestMethod.GET,RequestMethod.POST})
    public String userInfo(){
    	return "/WEB-INF/jsp/system/UserInfo";
    }
	
	/**
	 * mybatis新增操作
	 * TODO
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/insertSelective",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object insertSelective(Userinfo record,HttpSession session){
		Userinfo sessionUserinfo = (Userinfo)session.getAttribute("CURR_USER");
		if (sessionUserinfo != null) {
			String sessionuserid = sessionUserinfo.getUserid().toString();
			record.setOperatorid(sessionuserid==null?"":sessionuserid);
		}
		String md5pwd = MD5Util.MD5(record.getPwd());
		record.setPwd(md5pwd);

		int result = 0;
		Map map = new HashMap();
		try {
			result = userinfoService.insertSelective(record);
		}catch (org.springframework.dao.DuplicateKeyException e){
			/*异常处理截获流程,如果是存在DuplicateKeyException则是重名,id主键为自增长,不会重复*/
			map.put("code", "fail");
			map.put("msg", "该用户名已存在!");
			return map;
		}

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
     * @param userid
     * @return
     */
	@RequestMapping(value="/deleteByPrimaryKey",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public int deleteByPrimaryKey(Integer userid){
    	return userinfoService.deleteByPrimaryKey(userid);
    }

    /**
     * mybatis修改操作
     * @param record
     * @return
     */
	@RequestMapping(value="/updateByPrimaryKeySelective",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object updateByPrimaryKeySelective(Userinfo record){
		if (StringUtil.isNotEmpty(record.getPwd())){
			String md5pwd = MD5Util.MD5(record.getPwd());
			record.setPwd(md5pwd);
		}
		int result = 0;
		Map map = new HashMap();
		try {
			result =  userinfoService.updateByPrimaryKeySelective(record);
		}catch (org.springframework.dao.DuplicateKeyException e){
			/*异常处理截获流程,如果是存在DuplicateKeyException则是重名,id主键为自增长,不会重复*/
			map.put("code", "fail");
			map.put("msg", "该用户名已存在!");
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
	 * 重置密码
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/resetPwd",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object resetPwd(Userinfo record){
		String md5pwd = MD5Util.MD5("123456");
		record.setPwd(md5pwd);
		int result = userinfoService.updateByPrimaryKeySelective(record);
		Map map = new HashMap();
		if (result == 1) {
			map.put("code", "success");
			map.put("msg", CN_MessageEnum.ResetPasswordSuccess.getName()+"重置为默认密码123456");
		}else {
			map.put("code", "fail");
			map.put("msg", CN_MessageEnum.ResetPasswordFailed.getName());
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
    List<Userinfo> selectByExample(UserinfoExample example){
    	return userinfoService.selectByExample(example);
    }
    
    /**
     * mybatis根据userid查询mybatis
     * @param userid
     * @return
     */
	@RequestMapping(value="/selectByPrimaryKey",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Userinfo selectByPrimaryKey(Integer userid){
    	return userinfoService.selectByPrimaryKey(userid);
    }
    
    /**
     * 分页查询
     * @return
     */
	@RequestMapping(value="/getListByPage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object getListByPage(Userinfo userinfo,@RequestParam("page") Integer currPage,@RequestParam("limit") Integer pageSize){
		if (currPage == null) {
			currPage = 0;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		Map map = new HashMap();
		PageList pageList = userinfoService.getListByPage(userinfo,currPage,pageSize);
		map.put("code", 0);
		map.put("count", pageList.getTotalRows());
		map.put("msg", "");
		map.put("data", pageList.getList());
    	return map;
    }
	
	/**
	 * 根据ids激活
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/activeByIds",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object activeByIds(String ids){
		StringBuffer idsBuffer = new StringBuffer();
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			idsBuffer.append("'");
			idsBuffer.append(idsArr[i]);
			idsBuffer.append("'");
			if (i!=idsArr.length-1) {
				idsBuffer.append(",");
			}
		}

		Map map = new HashMap();
		if (idsBuffer.toString().replace("'","").length() <=0 ){
			map.put("code", "fail");
			map.put("msg", "请选择用户!!!");
			return map;
		}
		boolean result= userinfoService.activeByIds(idsBuffer.toString());
		if (result) {
			map.put("code", "success");
			map.put("msg", "激活成功!");
		}else {
			map.put("code", "fail");
			map.put("msg", "激活失败!!!");
		}
    	return map;
    }
	
	/**
	 * 禁用
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/disableByIds",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object disableByIds(String ids){
		StringBuffer idsBuffer = new StringBuffer();
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			idsBuffer.append("'");
			idsBuffer.append(idsArr[i]);
			idsBuffer.append("'");
			if (i!=idsArr.length-1) {
				idsBuffer.append(",");
			}
		}

		Map map = new HashMap();
		if (idsBuffer.toString().replace("'","").length() <=0 ){
			map.put("code", "fail");
			map.put("msg", "请选择用户!!!");
			return map;
		}
		boolean result = userinfoService.disableByIds(idsBuffer.toString());
		if (result) {
			map.put("code", "success");
			map.put("msg", "禁用成功!");
		}else {
			map.put("code", "fail");
			map.put("msg", "禁用失败!!!");
		}
		return map;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteByIds",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object deleteByIds(String ids){
		StringBuffer idsBuffer = new StringBuffer();
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			idsBuffer.append("'");
			idsBuffer.append(idsArr[i]);
			idsBuffer.append("'");
			if (i!=idsArr.length-1) {
				idsBuffer.append(",");
			}
		}
		Map map = new HashMap();

		if(idsBuffer.toString().length()>2){   //为空时是两个单引号''
			boolean result= userinfoService.deleteByIds(idsBuffer.toString());
			if (result) {
				map.put("code", "success");
				map.put("msg", "删除成功!");
			}else {
				map.put("code", "fail");
				map.put("msg", "删除失败!!!");
			}
		}else {
			map.put("code", "fail");
			map.put("msg", "请先选择要删除的用户!!!");
		}
		return map;
	}
	
	/**
	 * 返回角色checkbox用
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="/getAllcheckBoxRole",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List getAllcheckBoxRole(String userid){
		return userinfoService.getAllcheckBoxRole(userid);
	}
	
	
	/**
	 * 分配角色
	 * @param roleids
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="/allotRoles",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object allotRoles(String[] roleids,String userid){
		//先删除
		Auth_userjoinroleExample userjoinroleExample = new Auth_userjoinroleExample();
		userjoinroleExample.createCriteria().andUseridEqualTo(Integer.parseInt(userid));
		int delallotRoles = userinfoService.delallotRoles(userjoinroleExample);
		
		//再添加
		int allotRolesRes =0;
		Auth_userjoinrole userjoinrole = null;
		for (int i = 0; i < roleids.length; i++) {
			userjoinrole = new Auth_userjoinrole();
			userjoinrole.setUserid(Integer.parseInt(userid));
			userjoinrole.setRoleid(Integer.parseInt(roleids[i]));
			allotRolesRes = userinfoService.allotRoles(userjoinrole);
		}
		
		Map map = new HashMap();
		if (allotRolesRes>0||delallotRoles>0) {
			map.put("code", "success");
			map.put("msg", "操作成功!");
		}else {
			map.put("code", "fail");
			map.put("msg", "操作失败!!!");
		}
		return map;
	}

	@RequestMapping(value="/selectAllByPage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	List<Userinfo> selectAllByPage(UserinfoExample example, Integer currPage, Integer pageSize) {
		if (currPage == null) {
			currPage = 0;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		return userinfoService.selectAllByPage(example,currPage,pageSize);
	}

	/**
	 * 注册
	 * @param record
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/register",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object register(Userinfo record,HttpSession session){
		Userinfo sessionUserinfo = (Userinfo)session.getAttribute("CURR_USER");
		if (sessionUserinfo != null) {
			String sessionuserid = sessionUserinfo.getUserid().toString();
			record.setOperatorid(sessionuserid==null?"":sessionuserid);
		}
		String md5pwd = MD5Util.MD5(record.getPwd());
		record.setPwd(md5pwd);

		int result = 0;
		Map map = new HashMap();
		try {
			result = userinfoService.insertSelective(record);
			UserinfoExample example = new UserinfoExample();
			UserinfoExample.Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(record.getUsername());
			criteria.andCnnameEqualTo(record.getCnname());
			criteria.andIdcardEqualTo(record.getIdcard());
			//添加相应的角色
			List<Userinfo> userinfos = userinfoService.selectByExample(example);
			Integer userId = userinfos.get(0).getUserid();
			Auth_userjoinrole userjoinrole = new Auth_userjoinrole();
			userjoinrole.setRoleid(record.getUsertype());
			userjoinrole.setUserid(userId);
			userjoinroleService.insert(userjoinrole);
		}catch (org.springframework.dao.DuplicateKeyException e){
			/*异常处理截获流程,如果是存在DuplicateKeyException则是重名,id主键为自增长,不会重复*/
			map.put("code", "fail");
			map.put("msg", "该用户名已存在!");
			return map;
		}

		if (result == 1) {
			map.put("code", "success");
			map.put("msg", CN_MessageEnum.AddSuccess.getName());
		}else {
			map.put("code", "fail");
			map.put("msg", CN_MessageEnum.AddFailed.getName());
		}
		return map;
	}

	/********************************************************员工建档界面 start***********************************************/
	@RequestMapping(value="/staffArchive",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView staffArchive(HttpSession session){
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/jsp/staff/archive");
		Userinfo sessionUserinfo = (Userinfo)session.getAttribute("CURR_USER");
		if (sessionUserinfo != null) {
			String sessionuserid = sessionUserinfo.getUserid().toString();
			if (StringUtil.isNotEmpty(sessionuserid)) {
				Userinfo userinfo = userinfoService.selectByPrimaryKey(Integer.parseInt(sessionuserid));
				modelAndView.addObject("userid",userinfo.getUserid());
				modelAndView.addObject("username",userinfo.getUsername());
				modelAndView.addObject("cnname",userinfo.getCnname());
				modelAndView.addObject("sex",userinfo.getSex());
				modelAndView.addObject("age",userinfo.getAge());
				modelAndView.addObject("usertype",userinfo.getUsertype());
				modelAndView.addObject("idcard",userinfo.getIdcard());
				modelAndView.addObject("phone",userinfo.getPhone());
				modelAndView.addObject("email",userinfo.getEmail());
				modelAndView.addObject("address",userinfo.getAddress());
				modelAndView.addObject("education",userinfo.getEducation());
				modelAndView.addObject("experience",userinfo.getExperience());
			}
		}
		return modelAndView;
	}
}
