package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.ShareFileDAO;
import com.kms.bysl.dto.ShareFileDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class ShareFileServiceImpl implements ShareFileService{
	
	@Autowired
	private ShareFileDAO dao;

	@Override
	public void fileInsert(ShareFileDTO file) {
		dao.fileInsert(file);
	}

	@Override
	public List<ShareFileDTO> fileSelectByShareId(int shareId) {
		List<ShareFileDTO> files = dao.fileSelectByShareId(shareId);
		return files;
	}

	@Override
	public ShareFileDTO fileSelectByFileId(int fileId) {
		List<ShareFileDTO> files;
		files = dao.fileSelectByFileId(fileId);
		if(files.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 파일입니다.");
		}
		return files.get(0);
	}

	@Override
	public void fileDelete(int fileId) {
		dao.fileDelete(fileId);
	}
}
