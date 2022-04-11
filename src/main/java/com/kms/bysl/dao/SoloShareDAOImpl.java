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

import com.kms.bysl.dto.SoloShareDTO;

@Repository
public class SoloShareDAOImpl implements SoloShareDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<SoloShareDTO> soloShareRowMapper = new RowMapper<SoloShareDTO>() {

		@Override
		public SoloShareDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SoloShareDTO soloShare = new SoloShareDTO();
			soloShare.setId(rs.getInt("id"));
			soloShare.setUrl(rs.getString("url"));
			soloShare.setType(rs.getString("type"));
			soloShare.setOwnerId(rs.getInt("owner_id"));
			soloShare.setName(rs.getString("name"));
			soloShare.setManagerId(rs.getInt("manager_id"));
			soloShare.setManagerName(rs.getString("manager_name"));
			soloShare.setManagerEmail(rs.getString("manager_email"));
			soloShare.setCreatedAt(rs.getTimestamp("created_at"));
			soloShare.setUpdatedAt(rs.getTimestamp("updated_at"));
			return soloShare;
		}
	};

	@Override
	public void soloShareInsert(SoloShareDTO soloShare) {
		final String sql = "insert into solo_share(url, type, owner_id, manager_id, manager_email) values (?, ?, ?, ?, ?)";
		
		template.update(sql, soloShare.getUrl(), soloShare.getType(), soloShare.getOwnerId()
				, soloShare.getManagerId(), soloShare.getManagerEmail());
	}

	@Override
	public List<SoloShareDTO> soloShareSelect(int ownerId, String url) {
		List<SoloShareDTO> soloShares;
		final String sql = "select a.*, b.name, c.name as manager_name"
				+ " from solo_share a left outer join member b on a.owner_id = b.id"
				+ " left outer join member c on a.manager_id = c.id"
				+ " where a.owner_id = ? and a.url = ?";
		
		soloShares = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, ownerId);
				ps.setString(2, url);
			}
		}, soloShareRowMapper);
		
		return soloShares;
	}

	@Override
	public List<SoloShareDTO> soloShareSelectAllByManagerId(int ownerId) {
		List<SoloShareDTO> soloShares;
		final String sql = "select a.*, b.name, c.name as manager_name"
				+ " from solo_share a left outer join member b on a.owner_id = b.id"
				+ " left outer join member c on a.manager_id = c.id"
				+ " where a.manager_id = ?";
		
		soloShares = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, ownerId);
			}
		}, soloShareRowMapper);
		
		return soloShares;
	}

	@Override
	public List<SoloShareDTO> soloShareSelectByManagerId(int ownerId, String url) {
		List<SoloShareDTO> soloShares;
		final String sql = "select a.*, b.name, c.name as manager_name"
				+ " from solo_share a left outer join member b on a.owner_id = b.id"
				+ " left outer join member c on a.manager_id = c.id"
				+ " where a.manager_id = ? and a.url = ?";
		
		soloShares = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, ownerId);
				ps.setString(2, url);
			}
		}, soloShareRowMapper);
		
		return soloShares;
	}

	@Override
	public void soloShareDelete(int soloShareId) {
		final String sql = "delete from solo_share where id = ?";
		
		template.update(sql, soloShareId);
	}
	
}
