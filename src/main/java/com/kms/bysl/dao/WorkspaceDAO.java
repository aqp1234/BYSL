package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.WorkspaceDTO;

public interface WorkspaceDAO {
	public int workspaceInsert(WorkspaceDTO workspace);
	public List<WorkspaceDTO> workspaceSelectById(int workspaceId);
	public List<WorkspaceDTO> workspaceAllSelect(List<Integer> workspaceIds);
	public void workspaceUpdate(WorkspaceDTO workspace);
	public void workspaceDelete(WorkspaceDTO workspace);
}
