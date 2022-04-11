package com.kms.bysl.dao;

import com.kms.bysl.dto.MemberDTO;

public interface MemberDAO {
	public void memberJoin(MemberDTO dto);
	public boolean loginCheck(MemberDTO dto);
	public MemberDTO memberView(MemberDTO dto);
	public void memberDelete(MemberDTO dto);
}
