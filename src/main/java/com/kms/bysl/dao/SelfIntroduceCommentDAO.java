package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.SelfIntroduceCommentDTO;

public interface SelfIntroduceCommentDAO {
	public void selfIntroduceCommentInsert(SelfIntroduceCommentDTO comment);
	public List<SelfIntroduceCommentDTO> selfIntroduceCommentSelectBySelfIntroduceId(int selfIntroduceId);
	public void selfIntroduceCommentUpdate(SelfIntroduceCommentDTO comment);
	public void selfIntroduceCommentDelete(int commentId);
}
