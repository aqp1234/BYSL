package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.DashboardDTO;

public interface DashboardService {
	public void dashboardInsert(DashboardDTO dashboard, int ownerUserWorkspaceId, int managerUserWorkspaceId);
	public List<DashboardDTO> dashboardSelectByWorkspaceId(int workspaceId);
	public DashboardDTO dashboardSelectByDashboardId(int dashboardId);
	public void dashboardUpdate(DashboardDTO dashboard, int managerId);
	public void dashboardDelete(int dashboardId);
}
