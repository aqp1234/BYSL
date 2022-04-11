package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.ChatDTO;

public interface ChatDAO {
	public void chatInsert(ChatDTO chat);
	public List<ChatDTO> chatSelectByRoomId(int roomId);
}
