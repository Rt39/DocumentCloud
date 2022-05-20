package com.autumn.system.intercepts;

import com.autumn.platform.model.Userinfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object arg2) throws Exception {
		String uri = request.getRequestURI();
		HttpSession session = request.getSession();
		if (uri.indexOf("front")>0){  //如果是前端,则不拦截
			return true;
		}

		/*不拦截静态资源spring mvc4.3以上可以用<mvc:exclude-mapping path=""/>代替*/
		if (uri.endsWith(".js")||uri.endsWith(".css")||uri.endsWith(".css")||uri.indexOf(".")>0){
			//System.out.println("不拦截资源文件"+uri);
			return true;
		}

		//如果是登录页,不拦截
		if (uri.indexOf("login")>0
				|| uri.indexOf("userinfo/register")>0
				|| uri.indexOf("uploadController/upload")>0
				|| uri.indexOf("company/register")>0
				|| uri.indexOf("company/getAllCompany")>0
				){
			return true;
		}

		Userinfo sessionUserinfo = (Userinfo)session.getAttribute("CURR_USER");
		//如果已经登录,不拦截
		if(sessionUserinfo!=null){
			return true;
		}else {  //如果未登录,跳转到登陆页
			System.out.println("后端拦截器,拦截访问"+uri);
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return false;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response,
						   Object arg2, ModelAndView arg3) throws Exception {
	}

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
