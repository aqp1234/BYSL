package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.ShareFileDTO;

public interface ShareFileService {
	public void fileInsert(ShareFileDTO file);
	public List<ShareFileDTO> fileSelectByShareId(int shareId);
	public ShareFileDTO fileSelectByFileId(int fileId);
	public void fileDelete(int fileId);
}
