package com.DDinside.desktop.servicce;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DDinside.desktop.domain.MemberVO;
import com.DDinside.desktop.mapper.RegisterMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	RegisterMapper mapper;
	
	@Override
	public void insertMember(MemberVO memberVO) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberVO", memberVO);
		mapper.insertMember(map);
		// 스프링은 마이바티스를 이용해 xml에서  vo.get이 안되기 때문에  해쉬맵에 넣어서 사용해야함
	}

}
