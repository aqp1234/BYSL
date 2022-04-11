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

import com.kms.bysl.dto.SchoolRecordFileDTO;

@Repository
public class SchoolRecordFileDAOImpl implements SchoolRecordFileDAO{

	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<SchoolRecordFileDTO> schoolRecordFileRowMapper = new RowMapper<SchoolRecordFileDTO>() {

		@Override
		public SchoolRecordFileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SchoolRecordFileDTO file = new SchoolRecordFileDTO();
			file.setId(rs.getInt("id"));
			file.setSchoolRecordId(rs.getInt("schoolrecord_id"));
			file.setPath(rs.getString("path"));
			file.setName(rs.getString("name"));
			file.setOriginalName(rs.getString("original_name"));
			file.setCreatedAt(rs.getTimestamp("created_at"));
			return file;
		}
	};
	
	@Override
	public void schoolRecordFileInsert(SchoolRecordFileDTO file) {
		final String sql = "insert into schoolrecord_file(schoolrecord_id, path, name, original_name) values(?, ?, ?, ?)";
		
		template.update(sql, file.getSchoolRecordId(), file.getPath(), file.getName(), file.getOriginalName());
	}

	@Override
	public List<SchoolRecordFileDTO> schoolRecordFileSelectBySchoolRecordId(int schoolRecordId) {
		List<SchoolRecordFileDTO> files;
		final String sql = "select * from schoolrecord_file where schoolrecord_id = ?";
		
		files = template.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, schoolRecordId);
			}
			
		}, schoolRecordFileRowMapper);
		
		return files;
	}

	@Override
	public List<SchoolRecordFileDTO> schoolRecordFileSelectByFileId(int fileId) {
		List<SchoolRecordFileDTO> files;
		final String sql = "select * from schoolrecord_file where id = ?";
		
		files = template.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, fileId);
			}
			
		}, schoolRecordFileRowMapper);
		
		return files;
	}

	@Override
	public List<SchoolRecordFileDTO> schoolRecordFileSelectLatest(int schoolRecordId) {
		List<SchoolRecordFileDTO> files;
		final String sql = "select * from schoolrecord_file where solo_workspace_id = ? order by created_at desc limit 1";
		
		files = template.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, schoolRecordId);
			}
			
		}, schoolRecordFileRowMapper);

		return files;
	}

	@Override
	public void schoolRecordDelete(int schoolRecordFileId) {
		final String sql = "delete from schoolrecord_file where id = ?";
		
		template.update(sql, schoolRecordFileId);
	}

}
