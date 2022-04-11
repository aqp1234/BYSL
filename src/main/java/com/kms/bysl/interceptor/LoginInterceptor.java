package com.kms.bysl.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kms.bysl.dto.MemberDTO;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("member");
		if(dto == null) {
			Enumeration<String> paramKeys = request.getParameterNames();
			String urlParameter = "";
			while (paramKeys.hasMoreElements()) {
			    String key = paramKeys.nextElement();
			    urlParameter += key + "=" + request.getParameter(key) + "&";
			}
			response.sendRedirect("/bysl/member/login?next=" + request.getRequestURI() + "?" + urlParameter);
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
