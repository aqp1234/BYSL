package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.DashboardDTO;

public interface DashboardDAO {
	public void dashboardInsert(DashboardDTO dashboard, int ownerUserWorkspaceId, int managerUserWorkspaceId);
	public List<DashboardDTO> dashboardSelectByWorkspaceId(int workspaceId);
	public List<DashboardDTO> dashboardSelectByDashboardId(int dashboardId);
	public void dashboardUpdate(DashboardDTO dashboard, int managerUserWorkspaceId);
	public void dashboardDelete(int dashboardId);
}
