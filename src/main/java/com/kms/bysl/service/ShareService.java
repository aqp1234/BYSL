package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.ShareDTO;

public interface ShareService {
	public int shareInsert(ShareDTO share, int ownerUserWorkspaceId);
	public List<ShareDTO> shareSelectByWorkspaceId(int workspaceId);
	public ShareDTO shareSelectByShareId(int shareId);
	public void shareUpdate(ShareDTO share);
	public void shareDelete(int shareId);
}
