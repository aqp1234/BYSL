package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.StudentBookDTO;

public interface StudentBookService {
	public void studentBookInsert(StudentBookDTO studentBook);
	public List<StudentBookDTO> studentBookSelectBySoloWorkspaceId(int soloWorkspaceId);
	public StudentBookDTO studentBookSelectByStudentBookId(int studentBookId);
	public void studentBookUpdate(StudentBookDTO studentBook);
	public void studentBookDelete(int studentBookId);
}
