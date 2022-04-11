package com.kms.bysl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kms.bysl.dto.TeamPermissionDTO;

@Repository
public class TeamPermissionDAOImpl implements TeamPermissionDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<TeamPermissionDTO> teamPermissionRowMapper = new RowMapper<TeamPermissionDTO>() {

		@Override
		public TeamPermissionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			TeamPermissionDTO teamPermission = new TeamPermissionDTO();
			teamPermission.setId(rs.getInt("id"));
			teamPermission.setTeamId(rs.getInt("team_id"));
			teamPermission.setPermissionId(rs.getInt("permission_id"));
			return teamPermission;
		}
	};

	@Override
	public void teamPermissionInsert(int teamId, List<Integer> permissionIds) {
		String parameter = "";
		for(int i = 0; i < permissionIds.size(); i++) {
			parameter += "(" + teamId + ", " + permissionIds.get(i) + ")";
			if(i != permissionIds.size() - 1) {
				parameter += ",";
			}
		}
		final String sql = "insert into team_permission(team_id, permission_id) values" + parameter;
		
		template.update(sql);
	}

	@Override
	public void adminTeamPermissionInsert(int teamId) {
		String parameter = "";
		for(int i = 1; i <= 25; i++) {
			parameter += "(" + teamId + ", " + i + ")";
			if(i != 25) {
				parameter += ",";
			}
		}
		final String sql = "insert into team_permission(team_id, permission_id) values " + parameter;
		
		template.update(sql);
	}

	@Override
	public List<TeamPermissionDTO> teamPermissionSelectByTeamId(int teamId) {
		List<TeamPermissionDTO> teamPermissions;
		final String sql = "select * from team_permission where team_id = ?";
		
		teamPermissions = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, teamId);
			}
		}, teamPermissionRowMapper);
		
		return teamPermissions;
	}

	@Override
	public void teamPermissionDelete(int teamId) {
		final String sql = "delete from team_permission where team_id = ?";
		
		template.update(sql, teamId);
	}

}
