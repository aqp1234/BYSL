package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.SelfIntroduceCommentDTO;

public interface SelfIntroduceCommentService {
	public void selfIntroduceCommentInsert(SelfIntroduceCommentDTO comment);
	public List<SelfIntroduceCommentDTO> selfIntroduceCommentSelectBySelfIntroduceId(int selfIntroduceId);
	public void selfIntroduceCommentUpdate(SelfIntroduceCommentDTO comment);
	public void selfIntroduceCommentDelete(int commentId);
}
