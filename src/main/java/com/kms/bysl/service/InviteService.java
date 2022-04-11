package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.InviteDTO;

public interface InviteService {
	public void inviteInsert(InviteDTO invite, int senderUserWorkspaceId);
	public List<InviteDTO> inviteSelectAll(int workspaceId);
	public InviteDTO inviteSelectByInviteKey(InviteDTO invite);
	public void inviteAcceptUpdate(int inviteId);
	public void inviteDelete(int userId, int workspaceId);
	public void inviteDelete(int inviteId);
}
