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

import com.kms.bysl.dto.SoloWorkspaceDTO;

@Repository
public class SoloWorkspaceDAOImpl implements SoloWorkspaceDAO{

	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<SoloWorkspaceDTO> soloWorkspaceRowMapper = new RowMapper<SoloWorkspaceDTO>() {

		@Override
		public SoloWorkspaceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SoloWorkspaceDTO sw = new SoloWorkspaceDTO();
			sw.setId(rs.getInt("id"));
			sw.setSoloWorkspaceName(rs.getString("solo_workspace_name"));
			sw.setOwnerId(rs.getInt("owner_id"));
			sw.setCreatedAt(rs.getTimestamp("created_at"));
			return sw;
		}
	};
	
	@Override
	public void soloWorkspaceInsert(SoloWorkspaceDTO soloWorkspace) {
		final String sql = "insert into solo_workspace(solo_workspace_name, owner_id) values(?, ?)";
		
		template.update(sql, soloWorkspace.getSoloWorkspaceName(), soloWorkspace.getOwnerId());
	}

	@Override
	public List<SoloWorkspaceDTO> soloWorkspaceSelect(SoloWorkspaceDTO soloWorkspace) {
		List<SoloWorkspaceDTO> soloWorkspaces;
		final String sql = "select * from solo_workspace where solo_workspace_name = ? and owner_id = ?";
		
		soloWorkspaces = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, soloWorkspace.getSoloWorkspaceName());
				ps.setInt(2, soloWorkspace.getOwnerId());
			}
		}, soloWorkspaceRowMapper);
		
		return soloWorkspaces;
	}

	@Override
	public List<SoloWorkspaceDTO> soloWorkspaceAllSelect(int ownerId) {
		List<SoloWorkspaceDTO> soloWorkspaces;
		final String sql = "select * from solo_workspace where owner_id = ?";
		
		soloWorkspaces = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, ownerId);				
			}
		}, soloWorkspaceRowMapper);
		
		return (soloWorkspaces.size() <= 0) ? null : soloWorkspaces;
	}

	@Override
	public List<SoloWorkspaceDTO> soloWorkspaceSelectById(int id) {
		List<SoloWorkspaceDTO> soloWorkspace;
		final String sql = "select * from solo_workspace where id = ?";
		
		soloWorkspace = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		}, soloWorkspaceRowMapper);
		
		return soloWorkspace;
	}

	@Override
	public void soloWorkspaceUpdate(SoloWorkspaceDTO soloWorkspace) {
		final String sql = "update solo_workspace set solo_workspace_name = ?, owner_id = ? where id = ?";
		
		template.update(sql, soloWorkspace.getSoloWorkspaceName(), soloWorkspace.getOwnerId(), soloWorkspace.getId());
	}

	@Override
	public void soloWorkspaceDelete(SoloWorkspaceDTO soloWorkspace) {
		final String sql = "delete from solo_workspace where id = ?";
		
		template.update(sql, soloWorkspace.getId());
	}

}
