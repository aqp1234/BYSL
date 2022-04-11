package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.SchoolRecordDTO;

public interface SchoolRecordDAO {
	public int schoolRecordInsert(SchoolRecordDTO schoolRecord);
	public List<SchoolRecordDTO> schoolRecordSelectBySoloWorkspaceId(int soloWorkspaceId);
	public List<SchoolRecordDTO> schoolRecordSelectBySchoolRecordId(int schoolRecordId);
	public void schoolRecordUpdate(SchoolRecordDTO schoolRecord);
	public void schoolRecordDelete(int schoolRecordId);
}
