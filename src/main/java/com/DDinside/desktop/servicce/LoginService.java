package com.DDinside.desktop.servicce;

import com.DDinside.desktop.domain.*;

public interface LoginService {
	
	public int MemberLogin(String id, String pass);
	public int AdminLogin(String id, String pass);
	public MemberVO getMember(String id);
	public MemberVO getAdmin(String id);
	
}
