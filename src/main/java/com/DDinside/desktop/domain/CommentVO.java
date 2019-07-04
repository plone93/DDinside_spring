package com.DDinside.desktop.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CommentVO {
	private String board_id;
	private int num;
	private int board_num;
	private String board_title;
	private String writer_id;
	private String writer_name;
	private String content;
	private Timestamp writedate;	

}
