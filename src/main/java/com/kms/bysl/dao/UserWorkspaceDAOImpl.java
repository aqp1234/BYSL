package com.kms.bysl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.exception.DuplicateUserWorkspaceException;

@Repository
public class UserWorkspaceDAOImpl implements UserWorkspaceDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<UserWorkspaceDTO> userWorkspaceRowMapper = new RowMapper<UserWorkspaceDTO>() {

		@Override
		public UserWorkspaceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserWorkspaceDTO uw = new UserWorkspaceDTO();
			uw.setId(rs.getInt("id"));
			uw.setUserId(rs.getInt("user_id"));
			uw.setUserEmail(rs.getString("user_email"));
			uw.setWorkspaceId(rs.getInt("workspace_id"));
			uw.setTeamId(rs.getInt("team_id"));
			uw.setNick(rs.getString("nick"));
			uw.setColor(rs.getString("color"));
			uw.setCreatedAt(rs.getTimestamp("created_at"));
			return uw;
		}
	};

	@Override
	public void userWorkspaceInsert(UserWorkspaceDTO userWorkspace) {
		final String sql = "insert into user_workspace(user_id, workspace_id, team_id, nick, color) values (?, ?, ?, ?, ?)";
		
		template.update(sql, userWorkspace.getUserId(), userWorkspace.getWorkspaceId()
				, userWorkspace.getTeamId(), userWorkspace.getNick(), userWorkspace.getColor());
	}

	@Override
	public List<UserWorkspaceDTO> userWorkspaceSelect(UserWorkspaceDTO userWorkspace) {
		List<UserWorkspaceDTO> userWorkspaces;
		final String sql = "select a.*, b.email as user_email"
				+ " from user_workspace a, member b"
				+ " where user_id = ? and workspace_id = ?";
		
		userWorkspaces = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, userWorkspace.getUserId());
				ps.setInt(2, userWorkspace.getWorkspaceId());
			}
		}, userWorkspaceRowMapper);
		
		return userWorkspaces;
	}

	@Override
	public List<UserWorkspaceDTO> userWorkspaceSelectByWorkspaceId(int workspaceId) {
		List<UserWorkspaceDTO> userWorkspaces;
		final String sql = "select a.*, b.email as user_email"
				+ " from user_workspace a left outer join member b on a.user_id = b.id"
				+ " where workspace_id = ? order by created_at asc";
		
		userWorkspaces = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
			}
		}, userWorkspaceRowMapper);
		
		return userWorkspaces;
	}

	@Override
	public List<Integer> userWorkspaceAllSelect(int userId) {
		
		List<Integer> workspaceIds;
		final String sql = "select * from user_workspace where user_id = ?";
		
		workspaceIds = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, userId);
			}
		}, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("workspace_id");
			}
		});

		return workspaceIds;
	}

	@Override
	public void userWorkspaceUpdate(UserWorkspaceDTO userWorkspace) {
		final String sql = "update user_workspace set nick = ?, color = ? where id = ?";
		
		template.update(sql, userWorkspace.getNick(), userWorkspace.getColor(), userWorkspace.getId());
	}

	@Override
	public void userWorkspaceUpdateTeamId(int userId, int teamId) {
		final String sql = "update user_workspace set team_id = ? where user_id = ?";
		
		template.update(sql, teamId, userId);
	}

	@Override
	public void userWorkspaceUpdateAllTeamId(int teamId, int toTeamId) {
		final String sql = "update user_workspace set team_id = ? where team_id = ?";
		
		template.update(sql, toTeamId, teamId);
	}

	@Override
	public void userWorkspaceDelete(UserWorkspaceDTO userWorkspace) {
		final String sql = "delete from user_workspace where workspace_id = ? and user_id = ?";
		
		template.update(sql, userWorkspace.getWorkspaceId(), userWorkspace.getUserId());
	}

}
