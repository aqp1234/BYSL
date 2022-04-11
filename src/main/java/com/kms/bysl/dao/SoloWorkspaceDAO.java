package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.SoloWorkspaceDTO;

public interface SoloWorkspaceDAO {
	public void soloWorkspaceInsert(SoloWorkspaceDTO soloWorkspace);
	public List<SoloWorkspaceDTO> soloWorkspaceSelect(SoloWorkspaceDTO soloWorkspace);
	public List<SoloWorkspaceDTO> soloWorkspaceAllSelect(int ownerId);
	public List<SoloWorkspaceDTO> soloWorkspaceSelectById(int id);
	public void soloWorkspaceUpdate(SoloWorkspaceDTO soloWorkspace);
	public void soloWorkspaceDelete(SoloWorkspaceDTO soloWorkspace);
}
