package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.StudentBookCommentDAO;
import com.kms.bysl.dto.StudentBookCommentDTO;

@Service
public class StudentBookCommentServiceImpl implements StudentBookCommentService{
	
	@Autowired
	private StudentBookCommentDAO dao;

	@Override
	public void studentBookCommentInsert(StudentBookCommentDTO studentBookComment) {
		dao.studentBookCommentInsert(studentBookComment);
	}

	@Override
	public List<StudentBookCommentDTO> studentBookCommentSelectByStudentBookId(int studentBookId) {
		List<StudentBookCommentDTO> comments;
		comments = dao.studentBookCommentSelectByStudentBookId(studentBookId);
		return comments;
	}

	@Override
	public void studentBookCommentUpdate(StudentBookCommentDTO studentBookComment) {
		dao.studentBookCommentUpdate(studentBookComment);
	}

	@Override
	public void studentBookCommentDelete(int studentBookCommentId) {
		dao.studentBookCommentDelete(studentBookCommentId);
	}

}
