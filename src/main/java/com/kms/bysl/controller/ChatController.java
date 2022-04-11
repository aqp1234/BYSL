package com.kms.bysl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kms.bysl.dto.ChatDTO;
import com.kms.bysl.dto.RoomDTO;
import com.kms.bysl.service.ChatService;
import com.kms.bysl.service.RoomService;

@Controller
@RequestMapping(value="/chat")
public class ChatController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private ChatService chatService;
	
	@RequestMapping(value="/{workspaceId}", method=RequestMethod.GET)
	public String chatRoomList(@PathVariable int workspaceId, HttpSession session) {
		List<RoomDTO> rooms;
		rooms = roomService.roomSelectByWorkspaceId(workspaceId);
		session.setAttribute("rooms", rooms);
		return "chatRoom/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/add", method=RequestMethod.POST, produces="application/json; charset=utf8")
	public String chatRoomAdd(@PathVariable int workspaceId, RoomDTO room) {
		room.setWorkspaceId(workspaceId);
		roomService.roomInsert(room);
		return ".";
	}
	
	@RequestMapping(value="/{workspaceId}/room/{roomId}", method=RequestMethod.GET)
	public String chatRoom(@PathVariable int workspaceId, @PathVariable int roomId, HttpServletRequest request) {
		List<ChatDTO> chats;
		RoomDTO room;
		
		chats = chatService.chatSelectByRoomId(roomId);
		room = roomService.roomSelectByRoomId(roomId);
		request.setAttribute("chats", chats);
		request.setAttribute("room", room);		
		
		return "chatRoom/room";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/room/{roomId}", method=RequestMethod.DELETE, produces="application/json; charset=utf8")
	public String charRoomDelete(@PathVariable int workspaceId, @PathVariable int roomId) {
		return "ä�ù��� �����Ͽ����ϴ�.";
	}
}
