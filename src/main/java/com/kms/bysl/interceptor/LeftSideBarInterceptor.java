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
import com.kms.bysl.dto.SoloShareDTO;
import com.kms.bysl.dto.SoloWorkspaceDTO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.dto.WorkspaceDTO;
import com.kms.bysl.service.SoloShareService;
import com.kms.bysl.service.SoloWorkspaceService;
import com.kms.bysl.service.UserWorkspaceService;
import com.kms.bysl.service.WorkspaceService;

public class LeftSideBarInterceptor implements HandlerInterceptor{

	@Autowired
	private SoloWorkspaceService soloWorkspaceService;
	
	@Autowired
	private UserWorkspaceService userWorkspaceService;
	
	@Autowired
	private WorkspaceService workspaceService;
	
	@Autowired
	private SoloShareService soloShareService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session;
		MemberDTO member;
		List<SoloWorkspaceDTO> mySoloWorkspaces;
		List<WorkspaceDTO> workspaces;
		List<Integer> workspaceIds;
		List<SoloShareDTO> soloShares;
		
		session = request.getSession();
		member = (MemberDTO) session.getAttribute("member");
		
		workspaceIds = userWorkspaceService.userWorkspaceSelectByUser(member.getId());
		workspaces = workspaceService.workspaceAllSelect(workspaceIds);
		
		soloShares = soloShareService.soloShareSelectAllByManagerId(member.getId());
		
		mySoloWorkspaces = soloWorkspaceService.soloWorkspaceAllSelect(member.getId());
		
		session.setAttribute("myWorkspaces", workspaces);
		session.setAttribute("mySoloWorkspaces", mySoloWorkspaces);
		session.setAttribute("soloShares", soloShares);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
