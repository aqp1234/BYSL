package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.ChatDTO;

public interface ChatService {
	public void chatInsert(ChatDTO chat);
	public List<ChatDTO> chatSelectByRoomId(int roomId);
}
