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

import com.kms.bysl.dto.InviteDTO;

@Repository
public class InviteDAOImpl implements InviteDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<InviteDTO> inviteRowMapper = new RowMapper<InviteDTO>() {

		@Override
		public InviteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			InviteDTO invite = new InviteDTO();
			invite.setId(rs.getInt("id"));
			invite.setEmail(rs.getString("email"));
			invite.setInviteKey(rs.getString("invite_key"));
			invite.setSenderId(rs.getInt("sender_id"));
			invite.setSender(rs.getString("sender"));
			invite.setWorkspaceId(rs.getInt("workspace_id"));
			invite.setAccepted(rs.getBoolean("is_accepted"));
			invite.setCreatedAt(rs.getTimestamp("created_at"));
			invite.setUpdatedAt(rs.getTimestamp("updated_at"));
			return invite;
		}
	};

	@Override
	public void inviteInsert(InviteDTO invite, int senderUserWorkspaceId) {
		final String sql = "insert into invite(email, invite_key, sender_user_workspace_id) values(?, ?, ?)";
		
		template.update(sql, invite.getEmail(), invite.getInviteKey(), senderUserWorkspaceId);
	}

	@Override
	public List<InviteDTO> inviteSelectAll(int workspaceId) {
		List<InviteDTO> invites;
		final String sql = "select a.*, b.user_id as sender_id, b.workspace_id, c.email as sender"
				+ " from invite a left outer join user_workspace b on a.sender_user_workspace_id = b.id"
				+ " left outer join member c on b.user_id = c.id"
				+ " where b.workspace_id = ?";
		
		invites = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
			}
		}, inviteRowMapper);
		
		return invites;
	}

	@Override
	public List<InviteDTO> inviteSelectByInviteKey(InviteDTO invite) {
		List<InviteDTO> invites;
		final String sql = "select a.*, b.user_id as sender_id, b.workspace_id, c.email as sender"
				+ " from invite a left outer join user_workspace b on a.sender_user_workspace_id = b.id"
				+ " left outer join member c on b.user_id = c.id"
				+ " where b.workspace_id = ? and a.invite_key = ?";
		
		invites = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, invite.getWorkspaceId());
				ps.setString(2, invite.getInviteKey());
			}
		}, inviteRowMapper);
		
		return invites;
	}

	@Override
	public void inviteAcceptUpdate(int inviteId) {
		final String sql = "update invite set is_accepted = true where id = ?";
		
		template.update(sql, inviteId);
	}

	@Override
	public void inviteDelete(int userId, int workspaceId) {
		final String sql = "delete from invite where workspace_id = ? and email = (select email from member where id = ?)";
		
		template.update(sql, workspaceId, userId);
	}

	@Override
	public void inviteDelete(int inviteId) {
		final String sql = "delete from invite where id = ?";
		
		template.update(sql, inviteId);
	}
	
}
