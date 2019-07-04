package com.DDinside.desktop.servicce;

import com.DDinside.desktop.domain.*;

public interface MemberService {
	
	public MemberVO getMember(String user_Num);
	public MemberVO getAdmin(String num);
	public String getMemberPass(String num);
	public int updateMember(MemberVO memberVO, String user_Num);
	public int memberDelete(String user_Num);
	public int idCheck(String id);
	
}
