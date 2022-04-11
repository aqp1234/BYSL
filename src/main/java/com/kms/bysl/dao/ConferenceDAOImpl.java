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

import com.kms.bysl.dto.ConferenceDTO;

@Repository
public class ConferenceDAOImpl implements ConferenceDAO{

	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<ConferenceDTO> conferenceRowMapper = new RowMapper<ConferenceDTO>() {

		@Override
		public ConferenceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ConferenceDTO conference = new ConferenceDTO();
			conference.setId(rs.getInt("id"));
			conference.setWorkspaceId(rs.getInt("workspace_id"));
			Integer ownerId = rs.getInt("owner_id");
			conference.setOwnerId(rs.wasNull() ? null : ownerId);
			String nick = rs.getString("nick");
			conference.setNick(rs.wasNull() ? null : nick);
			conference.setSubject(rs.getString("subject"));
			conference.setContent(rs.getString("content"));
			conference.setCreatedAt(rs.getTimestamp("created_at"));
			conference.setUpdatedAt(rs.getTimestamp("updated_at"));
			return conference;
		}
	};
	
	@Override
	public void conferenceInsert(ConferenceDTO conference, int ownerUserWorkspaceId) {
		final String sql = "insert into conference(workspace_id, owner_user_workspace_id, subject, content) values (?, ?, ?, ?)";
		
		template.update(sql, conference.getWorkspaceId(), ownerUserWorkspaceId, conference.getSubject(), conference.getContent());
	}

	@Override
	public List<ConferenceDTO> conferenceSelectByWorkspaceId(int workspaceId) {
		List<ConferenceDTO> conferences;
		final String sql = "select a.*, b.user_id as owner_id, b.nick"
				+ " from conference a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " where a.workspace_id = ?";
		
		conferences = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
			}
		}, conferenceRowMapper);
		
		return conferences;
	}

	@Override
	public List<ConferenceDTO> conferenceSelectByConferenceId(int conferenceId) {
		List<ConferenceDTO> conferences;
		final String sql = "select a.*, b.user_id as owner_id, b.nick"
				+ " from conference a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " where a.id = ?";
		
		conferences = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, conferenceId);
			}
		}, conferenceRowMapper);
		
		return conferences;
	}

	@Override
	public void conferenceUpdate(ConferenceDTO conference) {
		final String sql = "update conference set subject = ?, content = ? where id = ?";
		
		template.update(sql, conference.getSubject(), conference.getContent(), conference.getId());
	}

	@Override
	public void conferenceDelete(int conferenceId) {
		final String sql = "delete from conference where id = ?";
		
		template.update(sql, conferenceId);
	}

}
