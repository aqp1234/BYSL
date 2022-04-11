package com.kms.bysl.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.WorkspaceDAO;
import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.WorkspaceDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class WorkspaceServiceImpl implements WorkspaceService{
	
	@Autowired
	private WorkspaceDAO dao;

	@Override
	public int workspaceInsert(WorkspaceDTO workspace) {
		int result = dao.workspaceInsert(workspace);
		return result;
	}

	@Override
	public WorkspaceDTO workspaceSelectById(int workspaceId) {
		List<WorkspaceDTO> workspaces = dao.workspaceSelectById(workspaceId);
		if(workspaces.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 워크스페이스입니다.");
		}
		return workspaces.get(0);
	}

	@Override
	public List<WorkspaceDTO> workspaceAllSelect(List<Integer> workspaceIds) {
		List<WorkspaceDTO> workspaces;
		workspaces = dao.workspaceAllSelect(workspaceIds);
		return workspaces;
	}

	@Override
	public void workspaceUpdate(WorkspaceDTO workspace) {
		dao.workspaceUpdate(workspace);
	}

	@Override
	public void workspaceDelete(WorkspaceDTO workspace) {
		dao.workspaceDelete(workspace);
	}

}
