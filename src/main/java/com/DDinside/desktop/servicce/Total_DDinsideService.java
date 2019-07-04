package com.DDinside.desktop.servicce;

import java.util.List;
import com.DDinside.desktop.domain.*;


public interface Total_DDinsideService {
	
	public List<BoardVO> selectAllBoards(String board_id);
	public void insertBoard(BoardVO boardVO, String board_id);
	public String selectUserId(String num, String board_id);
	public BoardVO BoardView(String num, String board_id);
	public void updateReadCount(String num, String board_id);
	public void postUpCount(String num, String board_id);
	public void postDownCount(String num, String board_id);
	public void postAhoCount(String num, String board_id);
	public void postReport(String num, String board_id);
	public int getReadCount(String num, String board_id);
	public CountVO getPostCount(String num, String board_id);
	public void updateBoard(BoardVO boardVO, String board_id);
	public void boardDelete(String num, String board_id);
	public List<BoardVO> selectAllBoard(int page, String board_id);
	public int tableCount(String board_id);
	public int searchTableCount(SearchVO searchVO, String board_id);
	public List<BoardVO> selectNotice();
	public void insertComment(CommentVO commentVO, String board_id);
	public List<CommentVO> selectComment(String num, String board_id);
	public List<BoardVO> search(SearchVO searchVO, String board_id);
	public CommentVO commentView(String num, String board_id);
	public void updateComment(CommentVO commentVO, String board_id);
	public void commentDelete(String comment_num, String board_id);
	
}
