package com.DDinside.desktop.servicce;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DDinside.desktop.domain.MemberVO;
import com.DDinside.desktop.mapper.MemberMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper mapper;

	@Override
	public MemberVO getMember(String user_Num) {
		MemberVO memberVO = mapper.getMember(user_Num);
		return memberVO;
	}

	@Override
	public String getMemberPass(String num) {
		return mapper.getMemberPass(num);
	}

	@Override
	public int updateMember(MemberVO memberVO, String user_Num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberVO", memberVO);
		map.put("user_Num", user_Num);
		
		return mapper.updateMember(map);
	}

	@Override
	public int memberDelete(String user_Num) {
		return mapper.memberDelete(user_Num);
	}

	@Override
	public int idCheck(String id) {
		return mapper.idCheck(id);
	}

	@Override
	public MemberVO getAdmin(String num) {
		MemberVO memberVO = mapper.getAdmin(num);
		return memberVO;
	}

}
