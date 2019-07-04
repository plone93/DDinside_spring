package com.DDinside.desktop.mapper;

import java.util.HashMap;
import java.util.List;


import com.DDinside.desktop.domain.*;




public interface DDinsideMapper {
	
	public List<BoardVO> selectAllBoards(String board_id);
	public String selectUserId(HashMap<String, Object> map);
	public BoardVO BoardView(HashMap<String, Object> map);
	public void updateReadCount(HashMap<String, Object> map);
	public void postUpCount(HashMap<String, Object> map);
	public void postDownCount(HashMap<String, Object> map);
	public void postAhoCount(HashMap<String, Object> map);
	public void postReport(HashMap<String, Object> map);
	public int getReadCount(HashMap<String, Object> map);
	public CountVO getPostCount(HashMap<String, Object> map);
	public void boardDelete(HashMap<String, Object> map);
	public List<BoardVO> selectAllBoard(HashMap<String, Object> map);
	public int tableCount(String board_id);
	public int searchTableCount(HashMap<String, Object> map);
	public int searchTableCountSuv(SearchVO searchVO, String word, String hotcount);
	public int searchTableCountTotal(SearchVO searchVO);
	public List<BoardVO> selectNotice(String board_id);
	public int insertComment(HashMap<String, Object> map);
	public List<CommentVO> selectComment(HashMap<String, Object> map);
	public int commentCount(HashMap<String, Object> map);
	public List<BoardVO> search(HashMap<String, Object> map);
	public int searchTotalCount(SearchVO searchVO, String board_id);
	public CommentVO commentView(HashMap<String, Object> map);
	public void updateComment(HashMap<String, Object> map);
	public int commentDelete(HashMap<String, Object> map);
	public void insertBoard(HashMap<String, Object> map);
	public int updateBoard(HashMap<String, Object> map);
	public List<BoardVO> mainNoticeBest();
	public List<BoardVO> mainNoticeWorst();
	public List<BoardVO> mainNoticeAho();
	public void commentCountUpdate(HashMap<String, Object> map);
	
	

}
