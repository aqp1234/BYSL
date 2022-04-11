package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.InviteDAO;
import com.kms.bysl.dto.InviteDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class InviteServiceImpl implements InviteService{

	@Autowired
	private InviteDAO dao;
	
	@Override
	public void inviteInsert(InviteDTO invite, int senderUserWorkspaceId) {
		dao.inviteInsert(invite, senderUserWorkspaceId);
	}

	@Override
	public List<InviteDTO> inviteSelectAll(int workspaceId) {
		List<InviteDTO> invites;
		invites = dao.inviteSelectAll(workspaceId);
		return invites;
	}

	@Override
	public InviteDTO inviteSelectByInviteKey(InviteDTO invite) {
		List<InviteDTO> invites;
		invites = dao.inviteSelectByInviteKey(invite);
		if(invites.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 초대입니다.");
		}
		return invites.get(0);
	}

	@Override
	public void inviteAcceptUpdate(int inviteId) {
		dao.inviteAcceptUpdate(inviteId);
	}

	@Override
	public void inviteDelete(int userId, int workspaceId) {
		dao.inviteDelete(userId, workspaceId);
	}

	@Override
	public void inviteDelete(int inviteId) {
		dao.inviteDelete(inviteId);
	}
	
}
