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
import com.kms.bysl.dto.SelfIntroduceCommentDTO;
import com.kms.bysl.dto.SelfIntroduceDTO;
import com.kms.bysl.service.SelfIntroduceCommentService;
import com.kms.bysl.service.SelfIntroduceService;

@Controller
@RequestMapping(value="/solo/selfIntroduce")
public class SelfIntroduceController {
	
	@Autowired
	private SelfIntroduceService selfIntroduceService;
	
	@Autowired
	private SelfIntroduceCommentService selfIntroduceCommentService;
	
	@RequestMapping(value="/{soloWorkspaceId}", method=RequestMethod.GET)
	public String selfIntroduceList(@PathVariable int soloWorkspaceId, HttpServletRequest request) {
		List<SelfIntroduceDTO> selfIntroduces = selfIntroduceService.selfIntroduceSelectBySoloWorkspaceId(soloWorkspaceId);
		
		request.setAttribute("selfIntroduces", selfIntroduces);
		return "selfIntroduce/main";
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/add", method=RequestMethod.GET)
	public String selfIntroduceAddForm() {
		return "selfIntroduce/add";
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> selfIntroduceAdd(@PathVariable int soloWorkspaceId, SelfIntroduceDTO selfIntroduce, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		selfIntroduce.setSoloWorkspaceId(soloWorkspaceId);
		selfIntroduce.setOwnerId(member.getId());
		selfIntroduce.setName(member.getName());
		
		selfIntroduceService.selfIntroduceInsert(selfIntroduce);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("자기소개서 추가가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/{selfIntroduceId}", method=RequestMethod.GET)
	public String selfIntroduceDetail(@PathVariable int selfIntroduceId, HttpServletRequest request) {
		SelfIntroduceDTO selfIntroduce = selfIntroduceService.selfIntroduceSelectBySelfIntroduceId(selfIntroduceId);
		List<SelfIntroduceCommentDTO> comments = selfIntroduceCommentService.selfIntroduceCommentSelectBySelfIntroduceId(selfIntroduceId);
		
		request.setAttribute("selfIntroduce", selfIntroduce);
		request.setAttribute("comments", comments);
		return "selfIntroduce/detail";
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/update/{selfIntroduceId}", method=RequestMethod.GET)
	public String selfIntroduceUpdateForm(@PathVariable int selfIntroduceId, HttpServletRequest request) {
		SelfIntroduceDTO selfIntroduce = selfIntroduceService.selfIntroduceSelectBySelfIntroduceId(selfIntroduceId);
		
		request.setAttribute("selfIntroduce", selfIntroduce);
		return "selfIntroduce/update";
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/update/{selfIntroduceId}", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> selfIntroduceUpdate(@PathVariable int selfIntroduceId, SelfIntroduceDTO selfIntroduce) {
		selfIntroduce.setId(selfIntroduceId);
		
		selfIntroduceService.selfIntroduceUpdate(selfIntroduce);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("자기소개서 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/{selfIntroduceId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> selfIntroduceDelete(@PathVariable int selfIntroduceId) {
		selfIntroduceService.selfIntroduceDelete(selfIntroduceId);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("자기소개서 삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/{selfIntroduceId}/comment", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> selfIntroduceCommentAdd(@PathVariable int selfIntroduceId, SelfIntroduceCommentDTO comment, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		comment.setSelfIntroduceId(selfIntroduceId);
		comment.setCommenterId(member.getId());
		comment.setCommenter(member.getName());
		
		selfIntroduceCommentService.selfIntroduceCommentInsert(comment);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("댓글 추가가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/{selfIntroduceId}/comment/{commentId}", method=RequestMethod.PUT)
	public ResponseEntity<ResponseData> selfIntroduceCommentUpdate(@PathVariable int commentId, SelfIntroduceCommentDTO comment) {
		comment.setId(commentId);
		
		selfIntroduceCommentService.selfIntroduceCommentUpdate(comment);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("댓글 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/{selfIntroduceId}/comment/{commentId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> selfIntroduceCommentDelete(@PathVariable int commentId) {
		selfIntroduceCommentService.selfIntroduceCommentDelete(commentId);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("댓글 삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
}
