package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.TeamDTO;

public interface TeamService {
	public int teamInsert(TeamDTO team);
	public int adminTeamInsert(TeamDTO team);
	public void guestTeamInsert(TeamDTO team);
	public List<TeamDTO> teamSelectByWorkspaceId(int workspaceId);
	public TeamDTO teamSelectByTeamId(int teamId);
	public TeamDTO adminTeamSelect(int workspaceId);
	public TeamDTO guestTeamSelect(int workspaceId);
	public void teamUpdate(TeamDTO team);
	public void teamDelete(int teamId);
}
