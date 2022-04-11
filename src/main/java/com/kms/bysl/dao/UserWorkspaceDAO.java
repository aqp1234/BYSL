package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.UserWorkspaceDTO;

public interface UserWorkspaceDAO {
	public void userWorkspaceInsert(UserWorkspaceDTO userWorkspace);
	public List<UserWorkspaceDTO> userWorkspaceSelect(UserWorkspaceDTO userWorkspace);
	public List<UserWorkspaceDTO> userWorkspaceSelectByWorkspaceId(int workspaceId);
	public List<Integer> userWorkspaceAllSelect(int userId);
	public void userWorkspaceUpdate(UserWorkspaceDTO userWorkspace);
	public void userWorkspaceUpdateTeamId(int userId, int teamId);
	public void userWorkspaceUpdateAllTeamId(int teamId, int toTeamId);
	public void userWorkspaceDelete(UserWorkspaceDTO userWorkspace);
}
