package com.kms.bysl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.kms.bysl.TimestampUtil;
import com.kms.bysl.dto.CalendarDTO;

@Repository
public class CalendarDAOImpl implements CalendarDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<CalendarDTO> calendarRowmapper = new RowMapper<CalendarDTO>() {
		@Override
		public CalendarDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CalendarDTO cal = new CalendarDTO();
			cal.setId(rs.getInt("id"));
			cal.setWorkspaceId(rs.getInt("workspace_id"));
			cal.setOwnerId(rs.getInt("owner_id"));
			cal.setNick(rs.getString("nick"));
			cal.setSubject(rs.getString("subject"));
			cal.setContent(rs.getString("content"));
			cal.setStartDate(rs.getTimestamp("start_date"));
			cal.setEndDate(rs.getTimestamp("end_date"));
			cal.setCreatedAt(rs.getTimestamp("created_at"));
			cal.setUpdatedAt(rs.getTimestamp("updated_at"));
			return cal;
		}
	};

	@Override
	public int calendarInsert(CalendarDTO calendar, int ownerUserWorkspaceId) {
		int result;
		final String sql = "insert into calendar(workspace_id, owner_user_workspace_id, subject, content, start_date, end_date)"
				+ " values(?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id"});
				pstmt.setInt(1, calendar.getWorkspaceId());
				pstmt.setInt(2, ownerUserWorkspaceId);
				pstmt.setString(3, calendar.getSubject());
				pstmt.setString(4, calendar.getContent());
				pstmt.setTimestamp(5, calendar.getStartDate());
				pstmt.setTimestamp(6, calendar.getEndDate());
				
				return pstmt;
			}
		}, keyHolder);
		
		result = keyHolder.getKey().intValue();
		
		return result;
	}

	@Override
	public List<CalendarDTO> calendarSelectById(int calendarId) {
		List<CalendarDTO> calendars;
		final String sql = "select a.*, b.user_id as owner_id, b.nick"
				+ " from calendar a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " where a.id = ?";
		
		calendars = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, calendarId);
			}
		}, calendarRowmapper);
		
		return calendars;
	}

	@Override
	public List<CalendarDTO> calendarSelectByWorkspaceId(int workspaceId) {
		List<CalendarDTO> calendars;
		final String sql = "select a.*, b.user_id as owner_id, b.nick"
				+ " from calendar a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " where a.workspace_id = ?";
		
		calendars = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
			}
		}, calendarRowmapper);
		
		return calendars;
	}

	@Override
	public List<CalendarDTO> calendarSelectByMonth(int year, int month, int workspaceId) {
		List<CalendarDTO> calendars;
		Timestamp firstDate;
		Timestamp lastDate;
		firstDate = TimestampUtil.getFirstDate(year, month);
		lastDate = TimestampUtil.getLastDate(year, month);
		
		final String sql = "select a.*, b.user_id as owner_id, b.nick"
				+ " from calendar a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " where a.workspace_id = ? and a.start_date <= ? and a.end_date >= ?";
		
		calendars = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
				ps.setTimestamp(2, lastDate);
				ps.setTimestamp(3, firstDate);
			}
		}, calendarRowmapper);
		
		return calendars;
	}

	@Override
	public List<CalendarDTO> calendarSelectByMonth(int year, int month) {
		List<CalendarDTO> calendars;
		Timestamp firstDate;
		Timestamp lastDate;
		firstDate = TimestampUtil.getFirstDate(year, month);
		lastDate = TimestampUtil.getLastDate(year, month);
		
		final String sql = "select a.*, b.user_id as owner_id, b.nick"
				+ " from calendar a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " where a.start_date <= ? and a.end_date >= ?";
		
		calendars = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setTimestamp(1, lastDate);
				ps.setTimestamp(2, firstDate);
			}
		}, calendarRowmapper);
		
		return calendars;
	}

	@Override
	public List<CalendarDTO> calendarSelectByDate(int year, int month, int date, int workspaceId) {
		List<CalendarDTO> calendars;
		Timestamp today;
		Timestamp tommorow;
		today = TimestampUtil.getDate(year, month, date);
		tommorow = TimestampUtil.getDate(year, month, date + 1);
		
		final String sql = "select a.*, b.user_id as owner_id, b.nick"
				+ " from calendar a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " where a.workspace_id = ? and a.start_date < ? and a.end_date >= ?";

		calendars = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, workspaceId);
				ps.setTimestamp(2, tommorow);
				ps.setTimestamp(3, today);
			}
		}, calendarRowmapper);
		
		return calendars;
	}

	@Override
	public List<CalendarDTO> calendarSelectByDate(int year, int month, int date) {
		List<CalendarDTO> calendars;
		Timestamp today;
		Timestamp tommorow;
		today = TimestampUtil.getDate(year, month, date);
		tommorow = TimestampUtil.getDate(year, month, date + 1);
		
		final String sql = "select a.*, b.user_id as owner_id, b.nick"
				+ " from calendar a left outer join user_workspace b on a.owner_user_workspace_id = b.id"
				+ " where a.start_date < ? and a.end_date >= ?";

		calendars = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setTimestamp(1, tommorow);
				ps.setTimestamp(2, today);
			}
		}, calendarRowmapper);
		
		return calendars;
	}

	@Override
	public void calendarUpdate(CalendarDTO calendar) {
		final String sql = "update calendar set subject = ?, content = ?, start_date = ?, end_date = ? where id = ?";
		
		template.update(sql, calendar.getSubject(), calendar.getContent(), calendar.getStartDate(), calendar.getEndDate(), calendar.getId());
	}

	@Override
	public void calendarDelete(CalendarDTO calendar) {
		final String sql = "delete from calendar where id = ?";
		
		template.update(sql, calendar.getId());
	}

}
