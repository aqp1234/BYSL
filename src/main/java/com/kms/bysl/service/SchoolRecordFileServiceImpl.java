package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.SchoolRecordFileDAO;
import com.kms.bysl.dto.SchoolRecordFileDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class SchoolRecordFileServiceImpl implements SchoolRecordFileService{
	
	@Autowired
	private SchoolRecordFileDAO dao;

	@Override
	public void schoolRecordFileInsert(SchoolRecordFileDTO file) {
		dao.schoolRecordFileInsert(file);
	}

	@Override
	public List<SchoolRecordFileDTO> schoolRecordFileSelectBySchoolRecordId(int schoolRecordId) {
		List<SchoolRecordFileDTO> files = dao.schoolRecordFileSelectBySchoolRecordId(schoolRecordId);
		return files;
	}

	@Override
	public SchoolRecordFileDTO schoolRecordFileSelectByFileId(int fileId) {
		List<SchoolRecordFileDTO> files = dao.schoolRecordFileSelectByFileId(fileId);
		if(files.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 파일입니다.");
		}
		return files.get(0);
	}

	@Override
	public void schoolRecordFileDelete(int fileId) {
		dao.schoolRecordDelete(fileId);
	}

}
