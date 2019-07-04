package com.DDinside.desktop.mapper;

import java.util.HashMap;
import java.util.List;
import com.DDinside.desktop.domain.*;


public interface Suv_DDinsideMapper {
	
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
	public void boardDelete(String num, String board_id);
	public List<BoardVO> selectAllBoard(int page, String board_id);
	public List<BoardVO> selectAllBoard(int page, String board_id, String word, String hotcount);

	/* public List<BoardVO> selectAllBoard_suv(HashMap<String, Object> map); */
	public List<BoardVO> selectAllBoard_Up(HashMap<String, Object> map);
	public List<BoardVO> selectAllBoard_Down(HashMap<String, Object> map);
	public List<BoardVO> selectAllBoard_Aho(HashMap<String, Object> map);
	public List<BoardVO> selectAllBoardTotal(HashMap<String, Object> map);
	public List<BoardVO> selectAllBoardReport(HashMap<String, Object> map);
	public List<BoardVO> selectAllBoardNotice(int page, String board_id);
	public List<MemberVO> selectAllAccount(HashMap<String, Object> map);
	public int tableCount(HashMap<String, Object> map);
	public int tableCount_Up(HashMap<String, Object> map);
	public int tableCount_Down(HashMap<String, Object> map);
	public int tableCount_Aho(HashMap<String, Object> map);
	public int tableCountTotal(String board_id);
	public int memberCountTotal();
	public int tableCountReport(HashMap<String, Object> map);
	public int tableCountNotice(String board_id);
	public int tableCountView(String board_id);
	public int searchTableCount(HashMap<String, Object> map);
	public int searchTableCountReport(HashMap<String, Object> map);
	public int searchTableCountDown(SearchVO searchVO, String board_id);
	public int searchTableCountAho(SearchVO searchVO, String board_id);
	public int searchTableCountTotal(HashMap<String, Object> map);
	public int searchMemberCount(SearchVO searchVO);
	public List<BoardVO> selectNotice();
	public int insertComment(HashMap<String, Object> map);
	public List<CommentVO> selectComment(HashMap<String, Object> map);
	public List<BoardVO> search(HashMap<String, Object> map);
	public List<BoardVO> searchReport(HashMap<String, Object> map);
	public List<BoardVO> searchDown(SearchVO searchVO, String board_id);
	public List<BoardVO> searchAho(SearchVO searchVO, String board_id);
	public List<BoardVO> searchTotal(HashMap<String, Object> map);
	public List<BoardVO> searchAccount(HashMap<String, Object> map);
	public CommentVO commentView(HashMap<String, Object> map);
	public void updateComment(HashMap<String, Object> map);
	public int commentDelete(HashMap<String, Object> map);
	public void insertBoard(BoardVO boardVO, String board_id);
	public int updateBoard(HashMap<String, Object> map);
	
}
