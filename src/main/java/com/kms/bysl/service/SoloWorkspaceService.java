package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.SoloWorkspaceDTO;

public interface SoloWorkspaceService {
	public void soloWorkspaceInsert(SoloWorkspaceDTO soloWorkspace);
	public SoloWorkspaceDTO soloWorkspaceSelect(SoloWorkspaceDTO soloWorkspace);
	public List<SoloWorkspaceDTO> soloWorkspaceAllSelect(int ownerId);
	public SoloWorkspaceDTO soloWorkspaceSelectById(int id);
	public void soloWorkspaceUpdate(SoloWorkspaceDTO soloWorkspace);
	public void soloWorkspaceDelete(SoloWorkspaceDTO soloWorkspace);
}
