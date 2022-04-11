package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.SoloWorkspaceDAO;
import com.kms.bysl.dto.SoloWorkspaceDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class SoloWorkspaceServiceImpl implements SoloWorkspaceService{
	
	@Autowired
	private SoloWorkspaceDAO dao;

	@Override
	public void soloWorkspaceInsert(SoloWorkspaceDTO soloWorkspace) {
		dao.soloWorkspaceInsert(soloWorkspace);
	}

	@Override
	public SoloWorkspaceDTO soloWorkspaceSelect(SoloWorkspaceDTO soloWorkspace) {
		List<SoloWorkspaceDTO> soloWorkspaces = dao.soloWorkspaceSelect(soloWorkspace);
		if(soloWorkspaces == null) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 워크스페이스입니다.");
		}
		return soloWorkspaces.get(0);
	}

	@Override
	public List<SoloWorkspaceDTO> soloWorkspaceAllSelect(int ownerId) {
		List<SoloWorkspaceDTO> soloWorkspaces;
		soloWorkspaces = dao.soloWorkspaceAllSelect(ownerId);
		
		return soloWorkspaces;
	}

	@Override
	public SoloWorkspaceDTO soloWorkspaceSelectById(int id) {
		List<SoloWorkspaceDTO> soloWorkspaces = dao.soloWorkspaceSelectById(id);
		if(soloWorkspaces.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 워크스페이스입니다.");
		}
		return soloWorkspaces.get(0);
	}

	@Override
	public void soloWorkspaceUpdate(SoloWorkspaceDTO soloWorkspace) {
		dao.soloWorkspaceUpdate(soloWorkspace);
	}

	@Override
	public void soloWorkspaceDelete(SoloWorkspaceDTO soloWorkspace) {
		dao.soloWorkspaceDelete(soloWorkspace);
	}

}
