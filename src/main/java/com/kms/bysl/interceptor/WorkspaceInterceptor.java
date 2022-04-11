package com.kms.bysl.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.dto.WorkspaceDTO;
import com.kms.bysl.exception.NullWorkspaceException;
import com.kms.bysl.service.UserWorkspaceService;
import com.kms.bysl.service.WorkspaceService;

public class WorkspaceInterceptor implements HandlerInterceptor{
	
	@Autowired
	private WorkspaceService workspaceService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, String> pathVariables;
		int workspaceId;

		pathVariables = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		HttpSession session = request.getSession();
		
		if(session.getAttribute("soloWorkspace") != null) {
			session.removeAttribute("soloWorkspace");
		}
		if(pathVariables != null && pathVariables.containsKey("workspaceId") == true) {
			workspaceId = Integer.parseInt(pathVariables.get("workspaceId"));
			WorkspaceDTO workspace = workspaceService.workspaceSelectById(workspaceId);
			if(workspace == null) {
				throw new NullWorkspaceException(new Exception(), "없거나 삭제된 워크스페이스입니다.");
			}
			session.setAttribute("workspace", workspace);
		}else {
			session.removeAttribute("workspace");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
