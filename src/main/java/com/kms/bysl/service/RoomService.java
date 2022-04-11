package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.RoomDTO;

public interface RoomService {
	public void roomInsert(RoomDTO room);
	public List<RoomDTO> roomSelectByWorkspaceId(int workspaceId);
	public RoomDTO roomSelectByRoomId(int roomId);
	public void roomDelete(RoomDTO room);
}
