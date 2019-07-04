package com.DDinside.desktop.domain;

import java.sql.Timestamp;



import lombok.Data;

@Data
public class BoardVO {
	private String board_id;
	private int num;
	private String name;
	private String pass;
	private String title;
	private String content;
	private String userid;
	private String item;
	private int readcount;
	private int commentcount;
	private int up;
	private int down;
	private int aho;
	private int report;
	private Timestamp writedate;
	
}
