package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.SelfIntroduceDTO;

public interface SelfIntroduceService {
	public void selfIntroduceInsert(SelfIntroduceDTO selfIntroduce);
	public List<SelfIntroduceDTO> selfIntroduceSelectBySoloWorkspaceId(int soloWorkspaceId);
	public SelfIntroduceDTO selfIntroduceSelectBySelfIntroduceId(int selfIntroduceId);
	public void selfIntroduceUpdate(SelfIntroduceDTO selfIntroduce);
	public void selfIntroduceDelete(int selfIntroduceId);
}
