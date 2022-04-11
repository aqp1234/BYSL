package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.SchoolRecordDAO;
import com.kms.bysl.dto.SchoolRecordDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class SchoolRecordServiceImpl implements SchoolRecordService{
	
	@Autowired
	private SchoolRecordDAO dao;

	@Override
	public int schoolRecordInsert(SchoolRecordDTO schoolRecord) {
		int result = dao.schoolRecordInsert(schoolRecord);
		return result;
	}

	@Override
	public List<SchoolRecordDTO> schoolRecordSelectBySoloWorkspaceId(int soloWorkspaceId) {
		List<SchoolRecordDTO> schoolRecords = dao.schoolRecordSelectBySoloWorkspaceId(soloWorkspaceId);
		return schoolRecords;
	}

	@Override
	public SchoolRecordDTO schoolRecordSelectBySchoolRecordId(int schoolRecordId) {
		List<SchoolRecordDTO> schoolRecords = dao.schoolRecordSelectBySchoolRecordId(schoolRecordId);
		if(schoolRecords.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 생활기록부입니다.");
		}
		return schoolRecords.get(0);
	}

	@Override
	public void schoolRecordUpdate(SchoolRecordDTO schoolRecord) {
		dao.schoolRecordUpdate(schoolRecord);
	}

	@Override
	public void schoolRecordDelete(int schoolRecordId) {
		dao.schoolRecordDelete(schoolRecordId);
	}

}
