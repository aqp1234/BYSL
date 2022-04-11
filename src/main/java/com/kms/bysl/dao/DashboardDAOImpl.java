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

import com.kms.bysl.dto.DashboardDTO;

@Repository
public class DashboardDAOImpl implements DashboardDAO{

	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<DashboardDTO> dashboardRowMapper = new RowMapper<DashboardDTO>() {

		@Override
		public DashboardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			DashboardDTO dashboard = new DashboardDTO();
			dashboard.setId(rs.getInt("id"));
			dashboard.setWorkspaceId(rs.getInt("workspace_id"));
			dashboard.setOwnerId(rs.getInt("owner_id"));
			dashboard.setNick(rs.getString("nick"));
			dashboard.setColor(rs.getString("color"));
			dashboard.setManagerId(rs.getInt("manager_id"));
			dashboard.setManagerNick(rs.getString("manager_nick"));
			dashboard.setManagerColor(rs.getString("manager_color"));
			dashboard.setSubject(rs.getString("subject"));
			dashboard.setContent(rs.getString("content"));
			dashboard.setStartDate(rs.getTimestamp("start_date"));
			dashboard.setEndDate(rs.getTimestamp("end_date"));
			dashboard.setFlag(rs.getInt("flag"));
			dashboard.setCreatedAt(rs.getTimestamp("created_at"));
			dashboard.setUpdatedAt(rs.getTimestamp("updated_at"));
			return dashboard;
		}
	};
	
	@Override
	public void dashboardInsert(DashboardDTO dashboard, int ownerUserWorkspaceId, int managerUserWorkspaceId) {
		final String sql = "insert into dashboard(workspace_id, owner_user_workspace_id, manager_user_workspace_id, subject, content, start_date, end_date, flag)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		template.update(sql, dashboard.getWorkspaceId(), ownerUserWorkspaceId, managerUserWorkspaceId, 
				dashboard.getSubject(), dashboard.getContent(), dashboard.getStartDate(), dashboard.getEndDate(), dashboard.getFlag());
	}

	@Override
	public List<DashboardDTO> dashboardSelectByWorkspaceId(int workspaceId) {
		List<DashboardDTO> dashboards;
		final String sql = "select a.*, b.user_id as owner_id, b.nick, b.color, c.user_id as manager_id, c.nick as manager_nick, c.color as manager_color"
				+ " from dashboard a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " left outer join user_workspace c on a.manager_user_workspace_id = c.id"
				+ " where a.workspace_id = ?";
		
		dashboards = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
			}
		}, dashboardRowMapper);
		
		return dashboards;
	}

	@Override
	public List<DashboardDTO> dashboardSelectByDashboardId(int dashboardId) {
		List<DashboardDTO> dashboards;
		final String sql = "select a.*, b.user_id as owner_id, b.nick, b.color, c.user_id as manager_id, c.nick as manager_nick, c.color as manager_color"
				+ " from dashboard a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " left outer join user_workspace c on a.manager_user_workspace_id = c.id"
				+ " where a.id = ?";
		
		dashboards = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, dashboardId);
			}
		}, dashboardRowMapper);
		
		return dashboards;
	}

	@Override
	public void dashboardUpdate(DashboardDTO dashboard, int managerUserWorkspaceId) {
		final String sql = "update dashboard set manager_user_workspace_id = ?, subject = ?, content = ?, start_date = ?, end_date = ?, flag = ? where id = ?";
		
		template.update(sql, managerUserWorkspaceId, dashboard.getSubject(), dashboard.getContent(), dashboard.getStartDate()
				, dashboard.getEndDate(), dashboard.getFlag(), dashboard.getId());
	}

	@Override
	public void dashboardDelete(int dashboardId) {
		final String sql = "delete from dashboard where id = ?";
		
		template.update(sql, dashboardId);
	}

}
