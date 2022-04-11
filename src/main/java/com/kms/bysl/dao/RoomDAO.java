package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.RoomDTO;

public interface RoomDAO {
	public void roomInsert(RoomDTO room);
	public List<RoomDTO> roomSelectByWorkspaceId(int workspaceId);
	public List<RoomDTO> roomSelectByRoomId(int roomId);
	public void roomDelete(RoomDTO room);
}
