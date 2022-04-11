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

import com.kms.bysl.dto.ShareFileDTO;

@Repository
public class ShareFileDAOImpl implements ShareFileDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<ShareFileDTO> shareFileRowMapper = new RowMapper<ShareFileDTO>() {

		@Override
		public ShareFileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ShareFileDTO file = new ShareFileDTO();
			file.setId(rs.getInt("id"));
			file.setShareId(rs.getInt("share_id"));
			file.setPath(rs.getString("path"));
			file.setName(rs.getString("name"));
			file.setOriginalName(rs.getString("original_name"));
			file.setCreatedAt(rs.getTimestamp("created_at"));
			return file;
		}
	};

	@Override
	public void fileInsert(ShareFileDTO file) {
		final String sql = "insert into share_file(share_id, path, name, original_name) values(?, ?, ?, ?)";
		
		template.update(sql, file.getShareId(), file.getPath(), file.getName(), file.getOriginalName());
	}

	@Override
	public List<ShareFileDTO> fileSelectByShareId(int shareId) {
		List<ShareFileDTO> files;
		final String sql = "select * from share_file where share_id = ?";
		
		files = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, shareId);
			}
		}, shareFileRowMapper);
		
		return files;
	}

	@Override
	public List<ShareFileDTO> fileSelectByFileId(int fileId) {
		List<ShareFileDTO> files;
		final String sql = "select * from share_file where id = ?";
		
		files = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, fileId);
			}
		}, shareFileRowMapper);
		
		return files;
	}

	@Override
	public void fileDelete(int fileId) {
		final String sql = "delete from share_file where id = ?";
		
		template.update(sql, fileId);
	}

}
