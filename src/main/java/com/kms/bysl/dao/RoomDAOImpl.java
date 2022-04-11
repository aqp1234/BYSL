package com.kms.bysl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kms.bysl.dto.RoomDTO;

@Repository
public class RoomDAOImpl implements RoomDAO{
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public void roomInsert(RoomDTO room) {
		final String sql = "insert into room(workspace_id, name) values (?, ?)";
		
		template.update(sql, room.getWorkspaceId(), room.getName());
	}

	@Override
	public List<RoomDTO> roomSelectByWorkspaceId(int workspaceId) {
		List<RoomDTO> rooms;
		final String sql = "select * from room where workspace_id = ?";
		
		rooms = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
			}
		}, new RowMapper<RoomDTO>() {

			@Override
			public RoomDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				RoomDTO room = new RoomDTO();
				room.setId(rs.getInt("id"));
				room.setWorkspaceId(rs.getInt("workspace_id"));
				room.setName(rs.getString("name"));
				room.setCreatedAt(rs.getTimestamp("created_at"));
				return room;
			}
		});
		
		return rooms;
	}

	@Override
	public List<RoomDTO> roomSelectByRoomId(int roomId) {
		List<RoomDTO> rooms;
		final String sql = "select * from room where id = ?";
		
		rooms = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, roomId);
			}
		}, new RowMapper<RoomDTO>() {

			@Override
			public RoomDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				RoomDTO room = new RoomDTO();
				room.setId(rs.getInt("id"));
				room.setWorkspaceId(rs.getInt("workspace_id"));
				room.setName(rs.getString("name"));
				room.setCreatedAt(rs.getTimestamp("created_at"));
				return room;
			}
		});
		
		return rooms;
	}

	@Override
	public void roomDelete(RoomDTO room) {
		final String sql = "delete from room where id = ?";
		
		template.update(sql, room.getId());
	}

}
