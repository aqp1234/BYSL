package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.TeamDAO;
import com.kms.bysl.dto.TeamDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class TeamServiceImpl implements TeamService{
	
	@Autowired
	private TeamDAO dao;

	@Override
	public int teamInsert(TeamDTO team) {
		int teamId = dao.teamInsert(team);
		return teamId;
	}

	@Override
	public int adminTeamInsert(TeamDTO team) {
		int teamId = dao.adminTeamInsert(team);
		return teamId;
	}

	@Override
	public void guestTeamInsert(TeamDTO team) {
		dao.guestTeamInsert(team);
	}

	@Override
	public List<TeamDTO> teamSelectByWorkspaceId(int workspaceId) {
		List<TeamDTO> teams;
		teams = dao.teamSelectByWorkspaceId(workspaceId);
		return teams;
	}

	@Override
	public TeamDTO teamSelectByTeamId(int teamId) {
		List<TeamDTO> teams;
		teams = dao.teamSelectByTeamId(teamId);
		if(teams.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 팀입니다.");
		}
		return teams.get(0);
	}

	@Override
	public TeamDTO adminTeamSelect(int workspaceId) {
		List<TeamDTO> teams;
		teams = dao.adminTeamSelect(workspaceId);
		if(teams.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 팀입니다.");
		}
		return teams.get(0);
	}

	@Override
	public TeamDTO guestTeamSelect(int workspaceId) {
		List<TeamDTO> teams;
		teams = dao.guestTeamSelect(workspaceId);
		if(teams.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 팀입니다.");
		}
		return teams.get(0);
	}

	@Override
	public void teamUpdate(TeamDTO team) {
		dao.teamUpdate(team);
	}

	@Override
	public void teamDelete(int teamId) {
		dao.teamDelete(teamId);
	}

}
