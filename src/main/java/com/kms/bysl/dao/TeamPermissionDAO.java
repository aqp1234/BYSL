package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.TeamPermissionDTO;

public interface TeamPermissionDAO {
	public void teamPermissionInsert(int teamId, List<Integer> permissionIds);
	public void adminTeamPermissionInsert(int teamId);
	public List<TeamPermissionDTO> teamPermissionSelectByTeamId(int teamId);
	public void teamPermissionDelete(int teamId);
}
