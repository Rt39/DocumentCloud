package com.autumn.system.servlet;


import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DispatcherServlet extends
		org.springframework.web.servlet.DispatcherServlet {

	private static final long serialVersionUID = -7677752525845571027L;

	@Override
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
    @Override
    public void service(ServletRequest req, ServletResponse res)
        throws ServletException, IOException {
 
        HttpServletRequest  request;
        HttpServletResponse response;
 
        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) res;
            /*sessionUserinfo.setUserid(123);
    		sessionUserinfo.setUsername("qyadmin");
    		sessionUserinfo.setCnname("国脉qy");
    		session.setAttribute("CURR_USER", sessionUserinfo);*/
        } catch (ClassCastException e) {
            throw new ServletException("non-HTTP request or response");
        }
        service(request, response);
    }
    
}