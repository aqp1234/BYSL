package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.ShareFileDTO;

public interface ShareFileDAO {
	public void fileInsert(ShareFileDTO file);
	public List<ShareFileDTO> fileSelectByShareId(int shareId);
	public List<ShareFileDTO> fileSelectByFileId(int fileId);
	public void fileDelete(int fileId);
}
