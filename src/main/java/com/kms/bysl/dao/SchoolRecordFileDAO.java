package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.SchoolRecordFileDTO;

public interface SchoolRecordFileDAO {
	public void schoolRecordFileInsert(SchoolRecordFileDTO file);
	public List<SchoolRecordFileDTO> schoolRecordFileSelectBySchoolRecordId(int schoolRecordId);
	public List<SchoolRecordFileDTO> schoolRecordFileSelectByFileId(int fileId);
	public List<SchoolRecordFileDTO> schoolRecordFileSelectLatest(int schoolRecordId);
	public void schoolRecordDelete(int schoolRecordFileId);
}
