package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.StudentBookDAO;
import com.kms.bysl.dto.StudentBookDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class StudentBookServiceImpl implements StudentBookService{
	
	@Autowired
	private StudentBookDAO dao;

	@Override
	public void studentBookInsert(StudentBookDTO studentBook) {
		dao.studentBookInsert(studentBook);
	}

	@Override
	public List<StudentBookDTO> studentBookSelectBySoloWorkspaceId(int soloWorkspaceId) {
		List<StudentBookDTO> studentBooks;
		studentBooks = dao.studentBookSelectBySoloWorkspaceId(soloWorkspaceId);
		return studentBooks;
	}

	@Override
	public StudentBookDTO studentBookSelectByStudentBookId(int studentBookId) {
		List<StudentBookDTO> studentBooks;
		studentBooks = dao.studnetBookSelectByStudentBookId(studentBookId);
		if(studentBooks.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 학생부입니다.");
		}
		return studentBooks.get(0);
	}

	@Override
	public void studentBookUpdate(StudentBookDTO studentBook) {
		dao.studentBookUpdate(studentBook);
	}

	@Override
	public void studentBookDelete(int studentBookId) {
		dao.studentBookDelete(studentBookId);
	}

}
