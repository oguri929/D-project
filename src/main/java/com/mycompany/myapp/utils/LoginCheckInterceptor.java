package com.mycompany.myapp.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
//		
//		HttpSession session=request.getSession();
//		if(session.getAttribute("user") !=null) {
//			return true;
//		}
//		response.sendRedirect(request.getContextPath()+"/login.do");
//		return false;
//	}
	return true;
	}
}
