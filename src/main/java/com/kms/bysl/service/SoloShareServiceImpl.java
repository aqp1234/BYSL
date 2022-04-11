package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.SoloShareDAO;
import com.kms.bysl.dto.SoloShareDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class SoloShareServiceImpl implements SoloShareService{
	
	@Autowired
	private SoloShareDAO dao;

	@Override
	public void soloShareInsert(SoloShareDTO soloShare) {
		dao.soloShareInsert(soloShare);
	}

	@Override
	public List<SoloShareDTO> soloShareSelect(int ownerId, String url) {
		List<SoloShareDTO> soloShares = dao.soloShareSelect(ownerId, url);
		return soloShares;
	}

	@Override
	public List<SoloShareDTO> soloShareSelectAllByManagerId(int ownerId) {
		List<SoloShareDTO> soloShares = dao.soloShareSelectAllByManagerId(ownerId);
		return soloShares;
	}

	@Override
	public SoloShareDTO soloShareSelectByManagerId(int ownerId, String url) {
		List<SoloShareDTO> soloShares = dao.soloShareSelectAllByManagerId(ownerId);
		if(soloShares.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 공유입니다.");
		}
		return soloShares.get(0);
	}

	@Override
	public void soloShareDelete(int soloShareId) {
		dao.soloShareDelete(soloShareId);
	}

}
