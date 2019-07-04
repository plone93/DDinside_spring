package com.DDinside.desktop.mapper;

import java.util.HashMap;

import com.DDinside.desktop.domain.*;

public interface MemberMapper {
	
	public MemberVO getMember(String user_Num);
	public String getMemberPass(String num);
	public int updateMember(HashMap<String, Object> map);
	public int memberDelete(String user_Num);
	public int idCheck(String id);
	public MemberVO getAdmin(String num);
	
}
