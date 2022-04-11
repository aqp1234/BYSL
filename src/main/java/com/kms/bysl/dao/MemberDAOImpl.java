package com.kms.bysl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.kms.bysl.dto.MemberDTO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public void memberJoin(MemberDTO dto) {
		final String sql = "insert into member(email, name, password, phone, school_code, school_name, location_code, location_name) values(?, ?, ?, ?, ?, ?, ?, ?)";

		template.update(sql, dto.getEmail(), dto.getName(), dto.getPassword(), dto.getPhone(), dto.getSchoolCode(), dto.getSchoolName(), dto.getLocationCode(), dto.getLocationName());
	}

	@Override
	public boolean loginCheck(final MemberDTO dto) {
		List<MemberDTO> members;
		final String sql = "select email, password from member where email=?";
		members = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getEmail());				
			}
		}, new RowMapper<MemberDTO>() {

			@Override
			public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberDTO member = new MemberDTO();
				String encryptpassword = rs.getString("password");
				if(BCrypt.checkpw(dto.getPassword(), encryptpassword)) {
					member.setEmail(rs.getString("email"));
					return member;
				}
				return null;
			}
		});
		return (members.size() == 0) ? false : (members.get(0) == null) ? false : true;
	}

	@Override
	public MemberDTO memberView(final MemberDTO dto) {
		List<MemberDTO> members;
		final String sql = "select * from member where email = ?";
		members = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getEmail());
			}
		}, new RowMapper<MemberDTO>() {

			@Override
			public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberDTO member = new MemberDTO();
				member.setId(rs.getInt("id"));
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setSchoolName(rs.getString("school_name"));
				member.setSchoolCode(rs.getString("school_code"));
				member.setLocationName(rs.getString("location_name"));
				member.setLocationCode(rs.getString("location_code"));
				member.setCreatedAt(rs.getTimestamp("created_at"));
				member.setUpdatedAt(rs.getTimestamp("updated_at"));
				return member;
			}
		});
		
		return (members.size() == 0) ? null : members.get(0);
	}

	@Override
	public void memberDelete(MemberDTO dto) {
		final String sql = "delete from member where id = ? and email = ?";

		template.update(sql, dto.getId(), dto.getEmail());
	}
	
}
