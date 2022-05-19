package com.autumn.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.autumn.common.service.CommonService;
import com.autumn.platform.model.Userinfo;
import com.autumn.system.tools.MD5Util;
import com.autumn.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/common")
public class CommonController {

	@Resource
	CommonService commonService;

	/**
	 * 重设密码界面
	 */
	@RequestMapping(value="/rePwd",method={RequestMethod.GET,RequestMethod.POST})
	public String auth_role(){
		return "/WEB-INF/jsp/navtool/RePwd";
	}

	/**
	 * 退出界面
	 * @return
	 */
	@RequestMapping(value="/logout",method={RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session){
		session.removeAttribute("CURR_USER");
		session.invalidate();
		return "redirect:/login.jsp";
	}

	/**
	 * 修改个人信息界面
	 */
	@RequestMapping(value="/editUserInfo",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView editUserInfo(HttpSession session){
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/jsp/navtool/EditUserInfo");
		Userinfo sessionUserinfo = (Userinfo)session.getAttribute("CURR_USER");
		if (sessionUserinfo != null) {
			String sessionuserid = sessionUserinfo.getUserid().toString();
			if (StringUtil.isNotEmpty(sessionuserid)) {
				Userinfo userinfo = commonService.getUserinfoByID(Integer.parseInt(sessionuserid));
				modelAndView.addObject("userid",userinfo.getUserid());
				modelAndView.addObject("username",userinfo.getUsername());
				modelAndView.addObject("cnname",userinfo.getCnname());
				modelAndView.addObject("sex",userinfo.getSex());
				modelAndView.addObject("usertype",userinfo.getUsertype());
				modelAndView.addObject("idcard",userinfo.getIdcard());
				modelAndView.addObject("phone",userinfo.getPhone());
				modelAndView.addObject("email",userinfo.getEmail());
				modelAndView.addObject("remark",userinfo.getRemark());
				modelAndView.addObject("addtime",userinfo.getAddtime());
			}
		}
		return modelAndView;
	}

	/**
	 * 重置密码
	 * @param pwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value="/updatePwd",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object updatePwd(String pwd, String newPwd, HttpSession session){
		Map map = new HashMap();
		Userinfo sessionUserinfo = (Userinfo)session.getAttribute("CURR_USER");
		if (sessionUserinfo != null) {
			String sessionuserid = sessionUserinfo.getUserid().toString();
			if (StringUtil.isNotEmpty(sessionuserid)) {
				Userinfo userinfo = commonService.getUserinfoByID(Integer.parseInt(sessionuserid));
				String md5Pwd = MD5Util.MD5(pwd);
				if (md5Pwd.equals(userinfo.getPwd())){
					String md5newPwd = MD5Util.MD5(newPwd);
					int result = commonService.updatePwd(userinfo.getUserid(),md5newPwd);
					if (result>0){
						map.put("code","success");
						map.put("msg","密码修改成功!");
					}
				}else{
					map.put("code","falure");
					map.put("msg","原密码输入错误!");
				}
			}
		}else{
			map.put("code","falure");
			map.put("msg","请重新登录!");
		}
		return map;
	}


	/**
	 * 后台登录页
	 * @return
	 */
	@RequestMapping(value="/index",method={RequestMethod.GET,RequestMethod.POST})
	public String index(){
		return "/WEB-INF/jsp/index";
	}

	/**
	 * 登录界面
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map login(String username,String password,HttpSession session){
		Map map = new HashMap();
		String md5Pwd = MD5Util.MD5(password);
		Userinfo userinfo = new Userinfo();
		userinfo.setUsername(username);
		userinfo.setPwd(md5Pwd);
		List<Userinfo> userinfoList = commonService.getUserinfoByAccount(userinfo);

		if (userinfoList.size() == 1){
			session.setAttribute("CURR_USER",userinfoList.get(0));
			//获取用户所有权限
			List<String> path = commonService.getPermissionByUid(userinfoList.get(0).getUserid());
			session.setAttribute("PER_MODULES",path);
			map.put("code","success");
			map.put("msg","登录成功");
			//return "/WEB-INF/jsp/index";
		}else {
			map.put("code","fail");
			map.put("msg","用户名或密码不正确!");
		}
		return map;
	}

	/**
	 * 获取用户的菜单树
	 * @return
	 */
	@RequestMapping(value="/getModuleTreeByUID",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object getModuleTreeByUID(HttpSession session){
		Userinfo sessionUserinfo = (Userinfo)session.getAttribute("CURR_USER");
		List moduleTree = commonService.getModuleTreeByUID(sessionUserinfo.getUserid());
		return moduleTree;
	}

}
