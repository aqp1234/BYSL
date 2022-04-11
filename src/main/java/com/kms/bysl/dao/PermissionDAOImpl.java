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

import com.kms.bysl.dto.PermissionDTO;

@Repository
public class PermissionDAOImpl implements PermissionDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<PermissionDTO> permissionRowMapper = new RowMapper<PermissionDTO>() {

		@Override
		public PermissionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			PermissionDTO permission = new PermissionDTO();
			permission.setId(rs.getInt("id"));
			permission.setContentTypeId(rs.getInt("content_type_id"));
			permission.setName(rs.getString("name"));
			permission.setCodeName(rs.getString("code_name"));
			return permission;
		}
	};

	@Override
	public List<PermissionDTO> permissionSelectByTeamId(int teamId) {
		List<PermissionDTO> permissions;
		final String sql = "select * from permission a, team_permission b where a.id = b.permission_id and b.team_id = ?";
		
		permissions = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, teamId);
			}
		}, permissionRowMapper);
		
		return permissions;
	}

	@Override
	public List<PermissionDTO> permissionSelectAll() {
		List<PermissionDTO> permissions;
		final String sql = "select * from permission";
		
		permissions = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
			}
		}, permissionRowMapper);
		
		return permissions;
	}

}
