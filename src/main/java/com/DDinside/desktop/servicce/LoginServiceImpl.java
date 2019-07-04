package com.DDinside.desktop.servicce;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DDinside.desktop.domain.MemberVO;
import com.DDinside.desktop.mapper.LoginMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginMapper mapper;

	@Override
	public int MemberLogin(String id, String pass) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pass", pass);
		
		return mapper.MemberLogin(map);
	}

	@Override
	public int AdminLogin(String id, String pass) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pass", pass);
		
		return mapper.AdminLogin(map);
	}

	@Override
	public MemberVO getMember(String id) {
		MemberVO memberVO = mapper.getMember(id);
		return memberVO;
	}

	@Override
	public MemberVO getAdmin(String id) {
		MemberVO memberVO = mapper.getAdmin(id);
		return memberVO;
	}

}
