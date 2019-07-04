package com.DDinside.desktop.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {
	private int num;
	private String id;
	private String pass;
	private String name;
	private String email;
	private Timestamp joindate;
	
}
