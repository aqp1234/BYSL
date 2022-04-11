package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.SelfIntroduceDTO;

public interface SelfIntroduceDAO {
	public void selfIntroduceInsert(SelfIntroduceDTO selfIntroduce);
	public List<SelfIntroduceDTO> selfIntroduceSelectBySoloWorkspaceId(int soloWorkspaceId);
	public List<SelfIntroduceDTO> selfIntroduceSelectBySelfIntroduceId(int selfIntroduceId);
	public void selfIntroduceUpdate(SelfIntroduceDTO selfIntroduce);
	public void selfIntroduceDelete(int selfIntroduceId);
}
