package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.StudentBookCommentDTO;

public interface StudentBookCommentDAO {
	public void studentBookCommentInsert(StudentBookCommentDTO studentBookComment);
	public List<StudentBookCommentDTO> studentBookCommentSelectByStudentBookId(int studentBookId);
	public void studentBookCommentUpdate(StudentBookCommentDTO studentBookComment);
	public void studentBookCommentDelete(int studentBookCommentId);
}
