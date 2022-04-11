package com.kms.bysl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.kms.bysl.dto.SoloCalendarDTO;

@Repository
public class SoloCalendarDAOImpl implements SoloCalendarDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<SoloCalendarDTO> soloCalendarRowMapper = new RowMapper<SoloCalendarDTO>() {

		@Override
		public SoloCalendarDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SoloCalendarDTO soloCalendar = new SoloCalendarDTO();
			soloCalendar.setId(rs.getInt("id"));
			soloCalendar.setSoloWorkspaceId(rs.getInt("solo_workspace_id"));
			soloCalendar.setOwnerId(rs.getInt("owner_id"));
			soloCalendar.setSubject(rs.getString("subject"));
			soloCalendar.setContent(rs.getString("content"));
			soloCalendar.setStartDate(rs.getTimestamp("start_date"));
			soloCalendar.setEndDate(rs.getTimestamp("end_date"));
			soloCalendar.setCreatedAt(rs.getTimestamp("created_at"));
			soloCalendar.setUpdatedAt(rs.getTimestamp("updated_at"));
			return soloCalendar;
		}
	};

	@Override
	public int soloCalendarInsert(SoloCalendarDTO soloCalendar) {
		int result;
		final String sql = "insert into solo_calendar(solo_workspace_id, subject, content, start_date, end_date) values(?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id"});
				pstmt.setInt(1, soloCalendar.getSoloWorkspaceId());
				pstmt.setString(2, soloCalendar.getSubject());
				pstmt.setString(3, soloCalendar.getContent());
				pstmt.setTimestamp(4, soloCalendar.getStartDate());
				pstmt.setTimestamp(5, soloCalendar.getEndDate());
				return pstmt;
			}
		}, keyHolder);
		
		result = keyHolder.getKey().intValue();
		
		return result;
	}

	@Override
	public List<SoloCalendarDTO> soloCalendarSelectById(int soloCalendarId) {
		List<SoloCalendarDTO> soloCalendars;
		final String sql = "select a.*, b.owner_id"
				+ " from solo_calendar a, solo_workspace b"
				+ " where a.solo_workspace_id = b.id and a.id = ?";
		
		soloCalendars = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, soloCalendarId);
			}
		}, soloCalendarRowMapper);
		
		return soloCalendars;
	}

	@Override
	public List<SoloCalendarDTO> soloCalendarSelectBySoloWorkspaceId(int soloWorkspaceId) {
		List<SoloCalendarDTO> soloCalendars;
		final String sql = "select a.*, b.owner_id"
				+ " from solo_calendar a, solo_workspace b"
				+ " where a.solo_workspace_id = b.id and a.solo_workspace_id = ?";
		
		soloCalendars = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, soloWorkspaceId);
			}
		}, soloCalendarRowMapper);
		
		return soloCalendars;
	}

	@Override
	public List<SoloCalendarDTO> soloCalendarSelectByMonth(int year, int month) {
		List<SoloCalendarDTO> soloCalendars;
		Timestamp firstTimestamp;
		Timestamp lastTimestamp;
		firstTimestamp = TimestampUtil.getFirstDate(year, month);
		lastTimestamp = TimestampUtil.getLastDate(year, month);
		final String sql = "select a.*, b.owner_id"
				+ " from solo_calendar a, solo_workspace b"
				+ " where a.solo_workspace_id = b.id and a.start_date <= ? and a.end_date >= ?";
		
		soloCalendars = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setTimestamp(1, lastTimestamp);
				ps.setTimestamp(2, firstTimestamp);
			}
		}, soloCalendarRowMapper);
		
		return soloCalendars;
	}

	@Override
	public List<SoloCalendarDTO> soloCalendarSelectByDate(int year, int month, int date) {
		List<SoloCalendarDTO> soloCalendars;
		Timestamp today;
		Timestamp tomorrow;
		today = TimestampUtil.getDate(year, month, date);
		tomorrow = TimestampUtil.getDate(year, month, date + 1);
		final String sql = "select a.*, b.owner_id"
				+ " from solo_calendar a, solo_workspace b"
				+ " where a.solo_workspace_id = b.id and a.start_date < ? and a.end_date >= ?";
		
		soloCalendars = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setTimestamp(1, tomorrow);
				ps.setTimestamp(2, today);
			}
		}, soloCalendarRowMapper);
		
		return soloCalendars;
	}

	@Override
	public void soloCalendarUpdate(SoloCalendarDTO soloCalendar) {
		final String sql = "update solo_calendar set subject = ?, content = ?, start_date = ?, end_date = ? where id = ?";
		
		template.update(sql, soloCalendar.getSubject(), soloCalendar.getContent(), soloCalendar.getStartDate(), soloCalendar.getEndDate(), soloCalendar.getId());
	}

	@Override
	public void soloCalendarDelete(int soloCalendarId) {
		final String sql = "delete from solo_calendar where id = ?";
		
		template.update(sql, soloCalendarId);
	}
	
}
