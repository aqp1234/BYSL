package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.StudentBookDTO;

public interface StudentBookDAO {
	public void studentBookInsert(StudentBookDTO studentBook);
	public List<StudentBookDTO> studentBookSelectBySoloWorkspaceId(int soloWorkspaceId);
	public List<StudentBookDTO> studnetBookSelectByStudentBookId(int studentBookId);
	public void studentBookUpdate(StudentBookDTO studentBook);
	public void studentBookDelete(int studentBookId);
}
