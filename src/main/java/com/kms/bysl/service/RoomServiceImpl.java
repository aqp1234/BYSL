package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.RoomDAO;
import com.kms.bysl.dto.RoomDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomDAO dao;
	
	@Override
	public void roomInsert(RoomDTO room) {
		dao.roomInsert(room);
	}

	@Override
	public List<RoomDTO> roomSelectByWorkspaceId(int workspaceId) {
		List<RoomDTO> rooms;
		rooms = dao.roomSelectByWorkspaceId(workspaceId);
		return rooms;
	}

	@Override
	public RoomDTO roomSelectByRoomId(int roomId) {
		List<RoomDTO> rooms;
		rooms = dao.roomSelectByRoomId(roomId);
		if(rooms.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 채팅방입니다.");
		}
		return rooms.get(0);
	}

	@Override
	public void roomDelete(RoomDTO room) {
		dao.roomDelete(room);
	}

}
