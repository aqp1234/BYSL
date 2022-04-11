package com.kms.bysl.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.WorkspaceDTO;

public interface WorkspaceService {
	public int workspaceInsert(WorkspaceDTO workspace);
	public WorkspaceDTO workspaceSelectById(int workspaceId);
	public List<WorkspaceDTO> workspaceAllSelect(List<Integer> workspaceIds);
	public void workspaceUpdate(WorkspaceDTO workspace);
	public void workspaceDelete(WorkspaceDTO workspace);
}
