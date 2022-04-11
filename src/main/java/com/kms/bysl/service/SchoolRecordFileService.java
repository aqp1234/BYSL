package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.SchoolRecordFileDTO;

public interface SchoolRecordFileService {
	public void schoolRecordFileInsert(SchoolRecordFileDTO file);
	public List<SchoolRecordFileDTO> schoolRecordFileSelectBySchoolRecordId(int schoolRecordId);
	public SchoolRecordFileDTO schoolRecordFileSelectByFileId(int fileId);
	public void schoolRecordFileDelete(int fileId);
}
