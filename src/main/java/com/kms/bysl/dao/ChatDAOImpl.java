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

import com.kms.bysl.dto.ChatDTO;

@Repository
public class ChatDAOImpl implements ChatDAO{
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public void chatInsert(ChatDTO chat) {
		final String sql = "insert into chat(workspace_id, room_id, owner_id, nick, color, chat) values(?, ?, ?, ?, ?, ?)";
		
		template.update(sql, chat.getWorkspaceId(), chat.getRoomId(), chat.getOwnerId(), chat.getNick(), chat.getColor(), chat.getChat());
	}

	@Override
	public List<ChatDTO> chatSelectByRoomId(int roomId) {
		List<ChatDTO> chats;
		final String sql = "select * from chat where room_id = ?";
		
		chats = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, roomId);
			}
		}, new RowMapper<ChatDTO>() {

			@Override
			public ChatDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ChatDTO chat = new ChatDTO();
				chat.setId(rs.getInt("id"));
				chat.setWorkspaceId(rs.getInt("workspace_id"));
				chat.setRoomId(rs.getInt("room_id"));
				chat.setOwnerId(rs.getInt("owner_id"));
				chat.setNick(rs.getString("nick"));
				chat.setColor(rs.getString("color"));
				chat.setChat(rs.getString("chat"));
				chat.setCreatedAt(rs.getTimestamp("created_at"));
				return chat;
			}
		});
		
		return chats;
	}
	
}
