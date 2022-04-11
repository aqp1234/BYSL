package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.TeamPermissionDAO;
import com.kms.bysl.dto.TeamPermissionDTO;

@Service
public class TeamPermissionServiceImpl implements TeamPermissionService{
	
	@Autowired
	private TeamPermissionDAO dao;

	@Override
	public void teamPermissionInsert(int teamId, List<Integer> permissionIds) {
		dao.teamPermissionInsert(teamId, permissionIds);
	}

	@Override
	public void adminTeamPermissionInsert(int teamId) {
		dao.adminTeamPermissionInsert(teamId);
	}

	@Override
	public List<TeamPermissionDTO> teamPermissionSelectByTeamId(int teamId) {
		List<TeamPermissionDTO> teamPermissions;
		teamPermissions = dao.teamPermissionSelectByTeamId(teamId);
		return teamPermissions;
	}

	@Override
	public void teamPermissionDelete(int teamId) {
		dao.teamPermissionDelete(teamId);
	}
	
}
