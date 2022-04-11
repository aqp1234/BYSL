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

import com.kms.bysl.dto.StudentBookDTO;

@Repository
public class StudentBookDAOImpl implements StudentBookDAO{

	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<StudentBookDTO> studentBookRowMapper = new RowMapper<StudentBookDTO>() {

		@Override
		public StudentBookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			StudentBookDTO studentBook = new StudentBookDTO();
			studentBook.setId(rs.getInt("id"));
			studentBook.setSoloWorkspaceId(rs.getInt("solo_workspace_id"));
			studentBook.setOwnerId(rs.getInt("owner_id"));
			studentBook.setName(rs.getString("name"));
			studentBook.setSubject(rs.getString("subject"));
			studentBook.setContent(rs.getString("content"));
			studentBook.setCreatedAt(rs.getTimestamp("created_at"));
			studentBook.setUpdatedAt(rs.getTimestamp("updated_at"));
			return studentBook;
		}
	};
	
	@Override
	public void studentBookInsert(StudentBookDTO studentBook) {
		final String sql = "insert into studentbook(solo_workspace_id, subject, content) values (?, ?, ?)";
		
		template.update(sql, studentBook.getSoloWorkspaceId(), studentBook.getSubject(), studentBook.getContent());
	}

	@Override
	public List<StudentBookDTO> studentBookSelectBySoloWorkspaceId(int soloWorkspaceId) {
		List<StudentBookDTO> studentBooks;
		final String sql = "select a.*, b.owner_id, c.name"
				+ " from studentbook a left outer join solo_workspace b on a.solo_workspace_id = b.id"
				+ " left outer join member c on b.owner_id = c.id"
				+ " where a.solo_workspace_id = ?";
		
		studentBooks = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, soloWorkspaceId);
			}
		}, studentBookRowMapper);
		
		return studentBooks;
	}

	@Override
	public List<StudentBookDTO> studnetBookSelectByStudentBookId(int studentBookId) {
		List<StudentBookDTO> studentBooks;
		final String sql = "select a.*, b.owner_id, c.name"
				+ " from studentbook a left outer join solo_workspace b on a.solo_workspace_id = b.id"
				+ " left outer join member c on b.owner_id = c.id"
				+ " where a.id = ?";
		
		studentBooks = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, studentBookId);
			}
		}, studentBookRowMapper);
		
		return studentBooks;
	}

	@Override
	public void studentBookUpdate(StudentBookDTO studentBook) {
		final String sql = "update studentbook set subject = ?, content = ? where id = ?";
		
		template.update(sql, studentBook.getSubject(), studentBook.getContent(), studentBook.getId());
	}

	@Override
	public void studentBookDelete(int studentBookId) {
		final String sql = "delete from studentbook where id = ?";
		
		template.update(sql, studentBookId);
	}

}
