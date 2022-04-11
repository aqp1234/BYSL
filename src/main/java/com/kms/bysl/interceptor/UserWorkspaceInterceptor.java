package com.kms.bysl.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.PermissionDTO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.exception.NullUserWorkspaceException;
import com.kms.bysl.service.PermissionService;
import com.kms.bysl.service.UserWorkspaceService;

public class UserWorkspaceInterceptor implements HandlerInterceptor{
	
	@Autowired
	private UserWorkspaceService userWorkspaceService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, String> pathVariables;
		int workspaceId;
		
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		pathVariables = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		if(pathVariables != null && pathVariables.containsKey("workspaceId") == true) {
			workspaceId = Integer.parseInt(pathVariables.get("workspaceId"));
			
			UserWorkspaceDTO exUserWorkspaceDTO = new UserWorkspaceDTO();
			exUserWorkspaceDTO.setUserId(member.getId());
			exUserWorkspaceDTO.setWorkspaceId(workspaceId);
			UserWorkspaceDTO userWorkspace = userWorkspaceService.userWorkspaceSelect(exUserWorkspaceDTO);
			
			if(userWorkspace == null) {
				throw new NullUserWorkspaceException(new Exception(), "본인이 가입한 워크스페이스에만 접속 가능합니다.");
			}
			session.setAttribute("userWorkspace", userWorkspace);
			
			List<PermissionDTO> myTeamPermissions;
			myTeamPermissions = permissionService.permissionSelectByTeamId(userWorkspace.getTeamId());
			session.setAttribute("myTeamPermissions", myTeamPermissions);
		}else {
			session.removeAttribute("userWorkspace");
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
