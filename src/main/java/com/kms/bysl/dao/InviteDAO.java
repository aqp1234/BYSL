package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.InviteDTO;

public interface InviteDAO {
	public void inviteInsert(InviteDTO invite, int senderUserWorkspaceId);
	public List<InviteDTO> inviteSelectAll(int workspaceId);
	public List<InviteDTO> inviteSelectByInviteKey(InviteDTO invite);
	public void inviteAcceptUpdate(int inviteId);
	public void inviteDelete(int userId, int workspaceId);
	public void inviteDelete(int inviteId);
}
