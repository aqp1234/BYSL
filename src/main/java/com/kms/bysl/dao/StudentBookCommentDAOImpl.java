package com.kms.bysl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kms.bysl.dto.StudentBookCommentDTO;

@Repository
public class StudentBookCommentDAOImpl implements StudentBookCommentDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<StudentBookCommentDTO> studentBookCommentRowMapper = new RowMapper<StudentBookCommentDTO>() {

		@Override
		public StudentBookCommentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			StudentBookCommentDTO comment = new StudentBookCommentDTO();
			comment.setId(rs.getInt("id"));
			comment.setStudentBookId(rs.getInt("studentbook_id"));
			comment.setCommenterId(rs.getInt("commenter_id"));
			comment.setCommenter(rs.getString("commenter"));
			comment.setComment(rs.getString("comment"));
			comment.setDeleted(rs.getBoolean("is_deleted"));
			comment.setCreatedAt(rs.getTimestamp("created_at"));
			comment.setUpdatedAt(rs.getTimestamp("updated_at"));
			return comment;
		}
	};

	@Override
	public void studentBookCommentInsert(StudentBookCommentDTO studentBookComment) {
		final String sql = "insert into studentbook_comment(studentbook_id, commenter_id, comment) values(?, ?, ?)";
		
		template.update(sql, studentBookComment.getStudentBookId(), studentBookComment.getCommenterId()
				,studentBookComment.getComment());
	}

	@Override
	public List<StudentBookCommentDTO> studentBookCommentSelectByStudentBookId(int studentBookId) {
		List<StudentBookCommentDTO> comments;
		final String sql = "select a.*, b.name as commenter"
				+ " from studentbook_comment a left outer join member b on a.commenter_id = b.id"
				+ " where studentbook_id = ?";
		
		comments = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, studentBookId);
			}
		}, studentBookCommentRowMapper);
		
		return comments;
	}

	@Override
	public void studentBookCommentUpdate(StudentBookCommentDTO studentBookComment) {
		final String sql = "update studentbook_comment set comment = ?, updated_at = ? where id = ?";
		
		template.update(sql, studentBookComment.getComment(), new Timestamp(System.currentTimeMillis()), studentBookComment.getId());
	}

	@Override
	public void studentBookCommentDelete(int studentBookCommentId) {
		final String sql = "update studentbook_comment set is_deleted = true where id = ?";

		template.update(sql, studentBookCommentId);
	}

}
