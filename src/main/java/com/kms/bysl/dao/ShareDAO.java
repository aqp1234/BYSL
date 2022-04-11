package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.ShareDTO;

public interface ShareDAO {
	public int shareInsert(ShareDTO share, int ownerUserWorkspaceId);
	public List<ShareDTO> shareSelectByWorkspaceId(int workspaceId);
	public List<ShareDTO> shareSelectByShareId(int shareId);
	public void shareUpdate(ShareDTO share);
	public void shareDelete(int shareId);
}
