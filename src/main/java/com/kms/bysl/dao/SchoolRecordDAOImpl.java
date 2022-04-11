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

import com.kms.bysl.dto.SchoolRecordDTO;

@Repository
public class SchoolRecordDAOImpl implements SchoolRecordDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<SchoolRecordDTO> schoolRecordRowMapper = new RowMapper<SchoolRecordDTO>() {

		@Override
		public SchoolRecordDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SchoolRecordDTO schoolRecord = new SchoolRecordDTO();
			schoolRecord.setId(rs.getInt("id"));
			schoolRecord.setSoloWorkspaceId(rs.getInt("solo_workspace_id"));
			schoolRecord.setOwnerId(rs.getInt("owner_id"));
			schoolRecord.setName(rs.getString("name"));
			schoolRecord.setSubject(rs.getString("subject"));
			schoolRecord.setContent(rs.getString("content"));
			schoolRecord.setCreatedAt(rs.getTimestamp("created_at"));
			schoolRecord.setUpdatedAt(rs.getTimestamp("updated_at"));
			return schoolRecord;
		}
	};

	@Override
	public int schoolRecordInsert(SchoolRecordDTO schoolRecord) {
		int result = 0;
		final String sql = "insert into schoolrecord(solo_workspace_id, subject, content) values (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id"});
				pstmt.setInt(1, schoolRecord.getSoloWorkspaceId());
				pstmt.setString(2, schoolRecord.getSubject());
				pstmt.setString(3, schoolRecord.getContent());
				
				return pstmt;
			}
		}, keyHolder);
		
		result = keyHolder.getKey().intValue();
		
		return result;
	}

	@Override
	public List<SchoolRecordDTO> schoolRecordSelectBySoloWorkspaceId(int soloWorkspaceId) {
		List<SchoolRecordDTO> schoolRecords;
		final String sql = "select a.*, c.id as owner_id, c.name"
				+ " from schoolrecord a, solo_workspace b, member c"
				+ " where a.solo_workspace_id = b.id and b.owner_id = c.id and a.solo_workspace_id = ?";
		
		schoolRecords = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, soloWorkspaceId);
			}
		}, schoolRecordRowMapper);
		
		return schoolRecords;
	}

	@Override
	public List<SchoolRecordDTO> schoolRecordSelectBySchoolRecordId(int schoolRecordId) {
		List<SchoolRecordDTO> schoolRecords;
		final String sql = "select a.*, c.id as owner_id, c.name"
				+ " from schoolrecord a, solo_workspace b, member c"
				+ " where a.solo_workspace_id = b.id and b.owner_id = c.id and a.id = ?";
		
		schoolRecords = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, schoolRecordId);
			}
		}, schoolRecordRowMapper);
		
		return schoolRecords;
	}

	@Override
	public void schoolRecordUpdate(SchoolRecordDTO schoolRecord) {
		final String sql = "update schoolrecord set subject = ?, content = ? where id = ?";
		
		template.update(sql, schoolRecord.getSubject(), schoolRecord.getContent(), schoolRecord.getId());
	}

	@Override
	public void schoolRecordDelete(int schoolRecordId) {
		final String sql = "delete from schoolrecord where id = ?";
		
		template.update(sql, schoolRecordId);
	}

}
