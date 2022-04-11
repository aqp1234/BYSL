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

import com.kms.bysl.dto.ShareDTO;

@Repository
public class ShareDAOImpl implements ShareDAO{

	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<ShareDTO> shareRowMapper = new RowMapper<ShareDTO>() {

		@Override
		public ShareDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ShareDTO share = new ShareDTO();
			share.setId(rs.getInt("id"));
			share.setWorkspaceId(rs.getInt("workspace_id"));
			share.setOwnerId(rs.getInt("owner_id"));
			share.setNick(rs.getString("nick"));
			share.setSubject(rs.getString("subject"));
			share.setContent(rs.getString("content"));
			share.setCreatedAt(rs.getTimestamp("created_at"));
			share.setUpdatedAt(rs.getTimestamp("updated_at"));
			return share;
		}
	};
	
	@Override
	public int shareInsert(ShareDTO share, int ownerUserWorkspaceId) {
		int result;
		final String sql = "insert into share(workspace_id, owner_user_workspace_id, subject, content) values (?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id"});
				pstmt.setInt(1, share.getWorkspaceId());
				pstmt.setInt(2, ownerUserWorkspaceId);
				pstmt.setString(3, share.getSubject());
				pstmt.setString(4, share.getContent());
				
				return pstmt;
			}
		}, keyHolder);
		result = keyHolder.getKey().intValue();
		
		return result;
	}

	@Override
	public List<ShareDTO> shareSelectByWorkspaceId(int workspaceId) {
		List<ShareDTO> shares;
		final String sql = "select a.*, b.user_id as owner_id, b.nick"
				+ " from share a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " where a.workspace_id = ?";
		
		shares = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
			}
		}, shareRowMapper);
		
		return shares;
	}

	@Override
	public List<ShareDTO> shareSelectByShareId(int shareId) {
		List<ShareDTO> shares;
		final String sql = "select a.*, b.user_id as owner_id, b.nick"
				+ " from share a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " where a.id = ?";
		
		shares = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, shareId);
			}
		}, shareRowMapper);
		
		return shares;
	}

	@Override
	public void shareUpdate(ShareDTO share) {
		final String sql = "update share set subject = ?, content = ? where id = ?";
		
		template.update(sql, share.getSubject(), share.getContent(), share.getId());
	}

	@Override
	public void shareDelete(int shareId) {
		final String sql = "delete from share where id = ?";
		
		template.update(sql, shareId);
	}

}
