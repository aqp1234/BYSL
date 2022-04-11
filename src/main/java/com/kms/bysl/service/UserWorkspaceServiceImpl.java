package com.kms.bysl.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kms.bysl.dao.UserWorkspaceDAO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.exception.DuplicateUserWorkspaceException;
import com.kms.bysl.exception.NullObjectException;

@Service
public class UserWorkspaceServiceImpl implements UserWorkspaceService{
	
	@Autowired
	private UserWorkspaceDAO dao;

	@Override
	public void userWorkspaceInsert(UserWorkspaceDTO userWorkspace) {
		dao.userWorkspaceInsert(userWorkspace);
	}

	@Override
	public UserWorkspaceDTO userWorkspaceSelect(UserWorkspaceDTO userWorkspace) {
		List<UserWorkspaceDTO> userWorkspaces = dao.userWorkspaceSelect(userWorkspace);
		if(userWorkspaces.size() == 0) {
			throw new NullObjectException(new Exception(), "가입하지 않은 워크스페이스입니다.");
		}
		return userWorkspaces.get(0);
	}

	@Override
	public List<UserWorkspaceDTO> userWorkspaceSelectByWorkspaceId(int workspaceId) {
		List<UserWorkspaceDTO> uw = dao.userWorkspaceSelectByWorkspaceId(workspaceId);
		return uw;
	}

	@Override
	public List<Integer> userWorkspaceSelectByUser(int userId) {
		List<Integer> workspaceIds = dao.userWorkspaceAllSelect(userId);
		return workspaceIds;
	}

	@Override
	public void userWorkspaceUpdate(UserWorkspaceDTO userWorkspace) {
		try {
			dao.userWorkspaceUpdate(userWorkspace);
		}catch(DuplicateKeyException e) {
			throw new DuplicateUserWorkspaceException(e, "동일한 닉네임이 있습니다. 다른 닉네임을 입력해주세요.");
		}
	}

	@Override
	public void userWorkspaceUpdateTeamId(int userId, int teamId) {
		dao.userWorkspaceUpdateTeamId(userId, teamId);
	}

	@Override
	public void userWorkspaceUpdateAllTeamId(int teamId, int toTeamId) {
		dao.userWorkspaceUpdateAllTeamId(teamId, toTeamId);
	}
	@Override
	public void userWorkspaceDelete(UserWorkspaceDTO userWorkspace) {
		dao.userWorkspaceDelete(userWorkspace);
	}

}
