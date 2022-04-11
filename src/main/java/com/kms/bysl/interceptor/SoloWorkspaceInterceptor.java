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
import com.kms.bysl.exception.NullSoloWorkspaceException;
import com.kms.bysl.service.SoloShareService;
import com.kms.bysl.service.SoloWorkspaceService;
import com.kms.bysl.service.UserWorkspaceService;

public class SoloWorkspaceInterceptor implements HandlerInterceptor{

	@Autowired
	private SoloShareService soloShareService;
	
	@Autowired
	private SoloWorkspaceService soloWorkspaceService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, String> pathVariables;
		HttpSession session;
		MemberDTO member;
		String path;
		SoloShareDTO soloShare;
		int soloWorkspaceId;
		
		pathVariables = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		session = request.getSession();
		member = (MemberDTO) session.getAttribute("member");
		path = request.getRequestURI();
		
		if(session.getAttribute("workspace") != null) {
			session.removeAttribute("workspace");
		}
		if(pathVariables != null && pathVariables.containsKey("soloWorkspaceId") == true) {
			soloWorkspaceId = Integer.parseInt(pathVariables.get("soloWorkspaceId"));
			SoloWorkspaceDTO soloWorkspace = soloWorkspaceService.soloWorkspaceSelectById(soloWorkspaceId);
			if(soloWorkspace == null) {
				throw new NullSoloWorkspaceException(new Exception(), "없거나 삭제된 워크스페이스입니다.");
			}else if(soloWorkspace.getOwnerId() != member.getId()){
				soloShare = soloShareService.soloShareSelectByManagerId(member.getId(), path);
				if(soloShare == null) {
					throw new NullSoloWorkspaceException(new Exception(), "본인이 만든 워크스페이스에만 접속 가능합니다.");
				}
			}
			session.setAttribute("soloWorkspace", soloWorkspace);
		}else {
			session.removeAttribute("soloWorkspace");
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
