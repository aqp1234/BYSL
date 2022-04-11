package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.SelfIntroduceDAO;
import com.kms.bysl.dto.SelfIntroduceDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class SelfIntroduceServiceImpl implements SelfIntroduceService{

	@Autowired
	private SelfIntroduceDAO dao;
	
	@Override
	public void selfIntroduceInsert(SelfIntroduceDTO selfIntroduce) {
		dao.selfIntroduceInsert(selfIntroduce);
	}

	@Override
	public List<SelfIntroduceDTO> selfIntroduceSelectBySoloWorkspaceId(int soloWorkspaceId) {
		List<SelfIntroduceDTO> selfIntroduces = dao.selfIntroduceSelectBySoloWorkspaceId(soloWorkspaceId);
		return selfIntroduces;
	}

	@Override
	public SelfIntroduceDTO selfIntroduceSelectBySelfIntroduceId(int selfIntroduceId) {
		List<SelfIntroduceDTO> selfIntroduces = dao.selfIntroduceSelectBySelfIntroduceId(selfIntroduceId);
		if(selfIntroduces.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 자기소개서입니다.");
		}
		return selfIntroduces.get(0);
	}

	@Override
	public void selfIntroduceUpdate(SelfIntroduceDTO selfIntroduce) {
		dao.selfIntroduceUpdate(selfIntroduce);
	}

	@Override
	public void selfIntroduceDelete(int selfIntroduceId) {
		dao.selfIntroduceDelete(selfIntroduceId);
	}

}
