package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.ShareDAO;
import com.kms.bysl.dto.ShareDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class ShareServiceImpl implements ShareService{

	@Autowired
	private ShareDAO dao;
	
	@Override
	public int shareInsert(ShareDTO share, int ownerUserWorkspaceId) {
		int result = dao.shareInsert(share, ownerUserWorkspaceId);
		return result;
	}

	@Override
	public List<ShareDTO> shareSelectByWorkspaceId(int workspaceId) {
		List<ShareDTO> shares;
		
		shares = dao.shareSelectByWorkspaceId(workspaceId);
		
		return shares;
	}

	@Override
	public ShareDTO shareSelectByShareId(int shareId) {
		List<ShareDTO> shares;
		
		shares = dao.shareSelectByShareId(shareId);
		
		if(shares.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 자료공유입니다.");
		}
		
		return shares.get(0);
	}

	@Override
	public void shareUpdate(ShareDTO share) {
		dao.shareUpdate(share);
	}

	@Override
	public void shareDelete(int shareId) {
		dao.shareDelete(shareId);
	}

}
