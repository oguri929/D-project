package com.mycompany.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("interceptor start");
		HttpSession session=request.getSession();
		if(session ==null) {
			response.sendRedirect(request.getContextPath()+"/login.do");
			return false;
		}
		return true;
	}

	
}
