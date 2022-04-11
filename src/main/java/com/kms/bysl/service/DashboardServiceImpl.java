package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.DashboardDAO;
import com.kms.bysl.dto.DashboardDTO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	private DashboardDAO dao;
	
	@Autowired
	private UserWorkspaceService userWorkspaceService;
	
	@Override
	public void dashboardInsert(DashboardDTO dashboard, int ownerUserWorkspaceId, int managerUserWorkspaceId) {
		dao.dashboardInsert(dashboard, ownerUserWorkspaceId, managerUserWorkspaceId);
	}

	@Override
	public List<DashboardDTO> dashboardSelectByWorkspaceId(int workspaceId) {
		List<DashboardDTO> dashboards;
		dashboards = dao.dashboardSelectByWorkspaceId(workspaceId);
		return dashboards;
	}

	@Override
	public DashboardDTO dashboardSelectByDashboardId(int dashboardId) {
		List<DashboardDTO> dashboards;
		dashboards = dao.dashboardSelectByDashboardId(dashboardId);
		if(dashboards.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 대시보드입니다.");
		}
		return dashboards.get(0);
	}

	@Override
	public void dashboardUpdate(DashboardDTO dashboard, int managerUserWorkspaceId) {
		dao.dashboardUpdate(dashboard, managerUserWorkspaceId);
	}

	@Override
	public void dashboardDelete(int dashboardId) {
		dao.dashboardDelete(dashboardId);
	}

}
