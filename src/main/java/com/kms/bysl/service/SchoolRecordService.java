package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.SchoolRecordDTO;

public interface SchoolRecordService {
	public int schoolRecordInsert(SchoolRecordDTO schoolRecord);
	public List<SchoolRecordDTO> schoolRecordSelectBySoloWorkspaceId(int soloWorkspaceId);
	public SchoolRecordDTO schoolRecordSelectBySchoolRecordId(int schoolRecordId);
	public void schoolRecordUpdate(SchoolRecordDTO schoolRecord);
	public void schoolRecordDelete(int schoolRecordId);
}
