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

import com.kms.bysl.dto.SelfIntroduceCommentDTO;

@Repository
public class SelfIntroduceCommentDAOImpl implements SelfIntroduceCommentDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<SelfIntroduceCommentDTO> selfIntroduceCommentRowMapper = new RowMapper<SelfIntroduceCommentDTO>() {

		@Override
		public SelfIntroduceCommentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SelfIntroduceCommentDTO comment = new SelfIntroduceCommentDTO();
			comment.setId(rs.getInt("id"));
			comment.setSelfIntroduceId(rs.getInt("selfintroduce_id"));
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
	public void selfIntroduceCommentInsert(SelfIntroduceCommentDTO comment) {
		final String sql = "insert into selfintroduce_comment(selfintroduce_id, commenter_id, comment) values(?, ?, ?)";
		
		template.update(sql, comment.getSelfIntroduceId(), comment.getCommenterId(), comment.getComment());
	}

	@Override
	public List<SelfIntroduceCommentDTO> selfIntroduceCommentSelectBySelfIntroduceId(int selfIntroduceId) {
		List<SelfIntroduceCommentDTO> comments;
		final String sql = "select a.*, b.name as commenter"
				+ " from selfintroduce_comment a left outer join member b on a.commenter_id = b.id"
				+ " where a.selfintroduce_id = ?";
		
		comments = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, selfIntroduceId);
			}
		}, selfIntroduceCommentRowMapper);
		
		return comments;
	}

	@Override
	public void selfIntroduceCommentUpdate(SelfIntroduceCommentDTO comment) {
		final String sql = "update selfintroduce_comment set comment = ? where id = ?";
		
		template.update(sql, comment.getComment(), comment.getId());
	}

	@Override
	public void selfIntroduceCommentDelete(int commentId) {
		final String sql = "update selfintroduce_comment set is_deleted = true where id = ?";
		
		template.update(sql, commentId);
	}

}
