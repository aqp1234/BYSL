package com.kms.bysl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kms.bysl.ResponseData;
import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.StudentBookCommentDTO;
import com.kms.bysl.dto.StudentBookDTO;
import com.kms.bysl.service.StudentBookCommentService;
import com.kms.bysl.service.StudentBookService;

@Controller
@RequestMapping(value="/solo/studentbook")
public class StudentBookController {
	
	@Autowired
	private StudentBookService studentBookService;
	
	@Autowired
	private StudentBookCommentService studentBookCommentService;
	
	@RequestMapping(value="/{soloWorkspaceId}", method=RequestMethod.GET)
	public String studentBookList(@PathVariable int soloWorkspaceId, HttpServletRequest request) {
		List<StudentBookDTO> studentBooks;
		
		studentBooks = studentBookService.studentBookSelectBySoloWorkspaceId(soloWorkspaceId);
		
		request.setAttribute("studentBooks", studentBooks);
		return "studentBook/main";
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/add", method=RequestMethod.GET)
	public String studentBookAddForm() {
		return "studentBook/add";
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> studentBookAdd(@PathVariable int soloWorkspaceId, StudentBookDTO studentBook, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		studentBook.setSoloWorkspaceId(soloWorkspaceId);
		studentBook.setOwnerId(member.getId());
		studentBook.setName(member.getName());
		studentBookService.studentBookInsert(studentBook);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("학생부 추가가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/{studentBookId}", method=RequestMethod.GET)
	public String studentBookDetail(@PathVariable int studentBookId, HttpServletRequest request) {
		StudentBookDTO studentBook = studentBookService.studentBookSelectByStudentBookId(studentBookId);
		List<StudentBookCommentDTO> comments = studentBookCommentService.studentBookCommentSelectByStudentBookId(studentBookId);
		
		request.setAttribute("studentBook", studentBook);
		request.setAttribute("comments", comments);
		return "studentBook/detail";
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/{studentBookId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> studentBookDelete(@PathVariable int studentBookId) {
		studentBookService.studentBookDelete(studentBookId);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("학생부 삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/update/{studentBookId}", method=RequestMethod.GET)
	public String studentBookUpdateForm(@PathVariable int studentBookId, HttpServletRequest request) {
		StudentBookDTO studentBook = studentBookService.studentBookSelectByStudentBookId(studentBookId);
		
		request.setAttribute("studentBook", studentBook);
		return "studentBook/update";
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/update/{studentBookId}", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> studentBookUpdate(@PathVariable int studentBookId, StudentBookDTO studentBook) {
		studentBook.setId(studentBookId);
		studentBookService.studentBookUpdate(studentBook);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("학생부 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/{studentBookId}/comment", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> studentBookCommentAdd(@PathVariable int soloWorkspaceId, @PathVariable int studentBookId, StudentBookCommentDTO comment, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		comment.setStudentBookId(studentBookId);
		comment.setCommenterId(member.getId());
		comment.setCommenter(member.getName());
		
		studentBookCommentService.studentBookCommentInsert(comment);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("댓글 추가가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/{studentBookId}/comment/{commentId}", method=RequestMethod.PUT)
	public ResponseEntity<ResponseData> studentBookCommentUpdate(@PathVariable int commentId, StudentBookCommentDTO comment) {	
		comment.setId(commentId);
		
		studentBookCommentService.studentBookCommentUpdate(comment);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("댓글 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/{studentBookId}/comment/{commentId}", method= RequestMethod.DELETE)
	public ResponseEntity<ResponseData> studentBookCommentUpdate(@PathVariable int commentId) {
		studentBookCommentService.studentBookCommentDelete(commentId);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("댓글 삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
}
