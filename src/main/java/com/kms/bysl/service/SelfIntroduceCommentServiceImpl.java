package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.SelfIntroduceCommentDAO;
import com.kms.bysl.dto.SelfIntroduceCommentDTO;

@Service
public class SelfIntroduceCommentServiceImpl implements SelfIntroduceCommentService{
	
	@Autowired
	private SelfIntroduceCommentDAO dao;

	@Override
	public void selfIntroduceCommentInsert(SelfIntroduceCommentDTO comment) {
		dao.selfIntroduceCommentInsert(comment);
	}

	@Override
	public List<SelfIntroduceCommentDTO> selfIntroduceCommentSelectBySelfIntroduceId(int selfIntroduceId) {
		List<SelfIntroduceCommentDTO> comments = dao.selfIntroduceCommentSelectBySelfIntroduceId(selfIntroduceId);
		return comments;
	}

	@Override
	public void selfIntroduceCommentUpdate(SelfIntroduceCommentDTO comment) {
		dao.selfIntroduceCommentUpdate(comment);
	}

	@Override
	public void selfIntroduceCommentDelete(int commentId) {
		dao.selfIntroduceCommentDelete(commentId);
	}

}
