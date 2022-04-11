package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.TeamDTO;

public interface TeamDAO {
	public int teamInsert(TeamDTO team);
	public int adminTeamInsert(TeamDTO team);
	public void guestTeamInsert(TeamDTO team);
	public List<TeamDTO> teamSelectByWorkspaceId(int workspaceId);
	public List<TeamDTO> teamSelectByTeamId(int teamId);
	public List<TeamDTO> adminTeamSelect(int workspaceId);
	public List<TeamDTO> guestTeamSelect(int workspaceId);
	public void teamUpdate(TeamDTO team);
	public void teamDelete(int teamId);
}
