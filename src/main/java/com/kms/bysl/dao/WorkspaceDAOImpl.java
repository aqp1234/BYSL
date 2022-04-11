package com.kms.bysl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.WorkspaceDTO;

@Repository
public class WorkspaceDAOImpl implements WorkspaceDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<WorkspaceDTO> workspaceRowMapper = new RowMapper<WorkspaceDTO>() {

		@Override
		public WorkspaceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			WorkspaceDTO ws = new WorkspaceDTO();
			ws.setId(rs.getInt("id"));
			ws.setWorkspaceName(rs.getString("workspace_name"));
			ws.setOwnerId(rs.getInt("owner_id"));
			ws.setCreatedAt(rs.getTimestamp("created_at"));
			return ws;
		}
	};

	@Override
	public int workspaceInsert(WorkspaceDTO workspace) {
		int result;
		final String sql = "insert into workspace(workspace_name, owner_id) values(?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id"});
				pstmt.setString(1, workspace.getWorkspaceName());
				pstmt.setInt(2, workspace.getOwnerId());
				return pstmt;
			}
		}, keyHolder);
		
		result = keyHolder.getKey().intValue();
		
		return result;
	}

	@Override
	public List<WorkspaceDTO> workspaceSelectById(int workspaceId) {
		List<WorkspaceDTO> workspaces;
		final String sql = "select * from workspace where id = ?";
		
		workspaces = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
			}
		}, workspaceRowMapper);
		
		return workspaces;
	}

	@Override
	public List<WorkspaceDTO> workspaceAllSelect(List<Integer> workspaceIds) {
		List<WorkspaceDTO> workspaces;
		String sql = null;
		String parameters = String.join(", ", Collections.nCopies(workspaceIds.size(), "?"));
		
		if(workspaceIds.size() <= 0) {
			return null;
		}else if(workspaceIds.size() == 1) {
			sql = "select * from workspace where id = " + parameters;
		}else {
			sql = "select * from workspace where id in (" + parameters + ")";
		}
		
		workspaces = template.query(sql, workspaceIds.toArray(), workspaceRowMapper);

		return (workspaces.size() == 0) ? null : workspaces;
	}

	@Override
	public void workspaceUpdate(WorkspaceDTO workspace) {
		final String sql = "update workspace set workspace_name = ?, owner_id = ? where id = ?";
		
		template.update(sql, workspace.getWorkspaceName(), workspace.getOwnerId(), workspace.getId());
	}

	@Override
	public void workspaceDelete(WorkspaceDTO workspace) {
		final String sql = "delete from workspace where id = ?";
		
		template.update(sql, workspace.getId());
	}

}
