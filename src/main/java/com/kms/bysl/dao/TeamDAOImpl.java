package com.kms.bysl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.kms.bysl.dto.TeamDTO;

@Repository
public class TeamDAOImpl implements TeamDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<TeamDTO> teamRowMapper = new RowMapper<TeamDTO>() {

		@Override
		public TeamDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			TeamDTO team = new TeamDTO();
			team.setId(rs.getInt("id"));
			team.setWorkspaceId(rs.getInt("workspace_id"));
			team.setName(rs.getString("name"));
			team.setAdmin(rs.getBoolean("is_admin"));
			team.setGuest(rs.getBoolean("is_guest"));
			return team;
		}
	};

	@Override
	public int teamInsert(TeamDTO team) {
		int result;
		final String sql = "insert into team(workspace_id, name) values(?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id"});
				pstmt.setInt(1, team.getWorkspaceId());
				pstmt.setString(2, team.getName());
				return pstmt;
			}
		}, keyHolder);
		
		result = keyHolder.getKey().intValue();
		
		return result;
	}

	@Override
	public int adminTeamInsert(TeamDTO team) {
		int result;
		final String sql = "insert into team(workspace_id, name, is_admin) values(?, ?, true)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id"});
				pstmt.setInt(1, team.getWorkspaceId());
				pstmt.setString(2, team.getName());
				return pstmt;
			}
		}, keyHolder);
		
		result = keyHolder.getKey().intValue();
		
		return result;
	}

	@Override
	public void guestTeamInsert(TeamDTO team) {
		final String sql = "insert into team(workspace_id, name, is_guest) values(?, ?, true)";
		
		template.update(sql, team.getWorkspaceId(), team.getName());
	}

	@Override
	public List<TeamDTO> teamSelectByWorkspaceId(int workspaceId) {
		List<TeamDTO> teams;
		final String sql = "select * from team where workspace_id = ?";
		
		teams = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
			}
		}, teamRowMapper);
		
		return teams;
	}

	@Override
	public List<TeamDTO> teamSelectByTeamId(int teamId) {
		List<TeamDTO> teams;
		final String sql = "select * from team where id = ?";
		
		teams = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, teamId);
			}
		}, teamRowMapper);
		
		return teams;
	}

	@Override
	public List<TeamDTO> adminTeamSelect(int workspaceId) {
		List<TeamDTO> teams;
		final String sql = "select * from team where workspace_id = ? and is_admin = true";
		
		teams = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
			}
		}, teamRowMapper);
		
		return teams;
	}

	@Override
	public List<TeamDTO> guestTeamSelect(int workspaceId) {
		List<TeamDTO> teams;
		final String sql = "select * from team where workspace_id = ? and is_guest = true";
		
		teams = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
			}
		}, teamRowMapper);
		
		return teams;
	}

	@Override
	public void teamUpdate(TeamDTO team) {
		final String sql = "update team set name = ? where id = ?";
		
		template.update(sql, team.getName(), team.getId());
	}

	@Override
	public void teamDelete(int teamId) {
		final String sql = "delete from team where id = ?";
		
		template.update(sql, teamId);
	}

}
