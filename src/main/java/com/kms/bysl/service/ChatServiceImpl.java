package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.ChatDAO;
import com.kms.bysl.dto.ChatDTO;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatDAO dao;

	@Override
	public void chatInsert(ChatDTO chat) {
		dao.chatInsert(chat);
	}

	@Override
	public List<ChatDTO> chatSelectByRoomId(int roomId) {
		List<ChatDTO> chats;
		chats = dao.chatSelectByRoomId(roomId);
		return chats;
	}
	
}
