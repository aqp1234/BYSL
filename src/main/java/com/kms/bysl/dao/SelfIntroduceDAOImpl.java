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

import com.kms.bysl.dto.SelfIntroduceDTO;

@Repository
public class SelfIntroduceDAOImpl implements SelfIntroduceDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<SelfIntroduceDTO> selfIntroduceRowMapper = new RowMapper<SelfIntroduceDTO>() {

		@Override
		public SelfIntroduceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SelfIntroduceDTO selfIntroduce = new SelfIntroduceDTO();
			selfIntroduce.setId(rs.getInt("id"));
			selfIntroduce.setSoloWorkspaceId(rs.getInt("solo_workspace_id"));
			selfIntroduce.setOwnerId(rs.getInt("owner_id"));
			selfIntroduce.setName(rs.getString("name"));
			selfIntroduce.setSubject(rs.getString("subject"));
			selfIntroduce.setContent(rs.getString("content"));
			selfIntroduce.setCreatedAt(rs.getTimestamp("created_at"));
			selfIntroduce.setUpdatedAt(rs.getTimestamp("updated_at"));
			return selfIntroduce;
		}
	};

	@Override
	public void selfIntroduceInsert(SelfIntroduceDTO selfIntroduce) {
		final String sql = "insert into selfintroduce(solo_workspace_id, subject, content) values(?, ?, ?)";
		
		template.update(sql, selfIntroduce.getSoloWorkspaceId(), selfIntroduce.getSubject(), selfIntroduce.getContent());
	}

	@Override
	public List<SelfIntroduceDTO> selfIntroduceSelectBySoloWorkspaceId(int soloWorkspaceId) {
		List<SelfIntroduceDTO> selfIntroduces;
		final String sql = "select a.*, b.owner_id, c.name"
				+ " from selfintroduce a, solo_workspace b, member c"
				+ " where a.solo_workspace_id = b.id and b.owner_id = c.id and a.solo_workspace_id = ?";
		
		selfIntroduces = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, soloWorkspaceId);
			}
		}, selfIntroduceRowMapper);
		
		return selfIntroduces;
	}

	@Override
	public List<SelfIntroduceDTO> selfIntroduceSelectBySelfIntroduceId(int selfIntroduceId) {
		List<SelfIntroduceDTO> selfIntroduces;
		final String sql = "select a.*, b.owner_id, c.name"
				+ " from selfintroduce a, solo_workspace b, member c"
				+ " where a.solo_workspace_id = b.id and b.owner_id = c.id and a.id = ?";
		
		selfIntroduces = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, selfIntroduceId);
			}
		}, selfIntroduceRowMapper);
		
		return selfIntroduces;
	}

	@Override
	public void selfIntroduceUpdate(SelfIntroduceDTO selfIntroduce) {
		final String sql = "update selfintroduce set subject = ?, content = ? where id = ?";
		
		template.update(sql, selfIntroduce.getSubject(), selfIntroduce.getContent(), selfIntroduce.getId());
	}

	@Override
	public void selfIntroduceDelete(int selfIntroduceId) {
		final String sql = "delete from selfintroduce where id = ?";
		
		template.update(sql, selfIntroduceId);
	}

}
