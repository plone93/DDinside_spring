package com.DDinside.desktop.mapper;

import java.util.HashMap;

import com.DDinside.desktop.domain.*;

public interface LoginMapper {
	
	public int MemberLogin(HashMap<String, Object> map);
	public int AdminLogin(HashMap<String, Object> map);
	public MemberVO getMember(String id);
	public MemberVO getAdmin(String id);
	
}
