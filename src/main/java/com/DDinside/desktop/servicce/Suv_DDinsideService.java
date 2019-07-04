package com.DDinside.desktop.servicce;

import java.util.List;
import com.DDinside.desktop.domain.*;


public interface Suv_DDinsideService {
	
	public List<BoardVO> selectAllBoards(String board_id);
	public String selectUserId(String num, String board_id);
	public BoardVO BoardView(String num, String board_id);
	public void updateReadCount(String num, String board_id);
	public void postUpCount(String num, String board_id);
	public void postDownCount(String num, String board_id);
	public void postAhoCount(String num, String board_id);
	public void postReport(String num, String board_id);
	public int getReadCount(String num, String board_id);
	public CountVO getPostCount(String num, String board_id);
	public void boardDelete(String num, String board_id);
	public List<BoardVO> selectAllBoard(int page, String board_id);
	public List<BoardVO> selectAllBoard(int page, String board_id, String word, String hotcount);
	//public List<BoardVO> selectAllBoard_suv(int page, String board_id, String hotcount, String word);
	public List<BoardVO> selectAllBoard_Up(int page, String board_id, String hotcount);
	public List<BoardVO> selectAllBoard_Down(int page, String board_id, String hotcount);
	public List<BoardVO> selectAllBoard_Aho(int page, String board_id, String hotcount);
	public List<BoardVO> selectAllBoardTotal(int page, String board_id);
	public List<BoardVO> selectAllBoardReport(int page, String board_id, String word);
	public List<BoardVO> selectAllBoardNotice(int page, String board_id);
	public List<MemberVO> selectAllAccount(int page);
	public int tableCount(String board_id, String hotcount, String word);
	public int tableCount_Up(String board_id, String hotcount);
	public int tableCount_Down(String board_id, String hotcount);
	public int tableCount_Aho(String board_id, String hotcount);
	public int tableCountTotal(String board_id);
	public int memberCountTotal();
	public int tableCountReport(String board_id, String word);
	public int tableCountNotice(String board_id);
	public int tableCountView(String board_id);
	public int searchTableCount(SearchVO searchVO, String board_id);
	public int searchTableCount(SearchVO searchVO, String board_id, String word, String hotcount);
	public int searchTableCountReport(SearchVO searchVO, String board_id, String word, String hotcount);
	public int searchTableCountDown(SearchVO searchVO, String board_id);
	public int searchTableCountAho(SearchVO searchVO, String board_id);
	public int searchTableCountTotal(SearchVO searchVO, String board_id);
	public int searchMemberCount(SearchVO searchVO);
	public List<BoardVO> selectNotice();
	public int insertComment(CommentVO commentVO, String board_id);
	public List<CommentVO> selectComment(String num, String board_id);
	public List<BoardVO> search(int page, SearchVO searchVO, String board_id, String word, String hotcount);
	public List<BoardVO> searchReport(int page, SearchVO searchVO, String board_id, String word, String hotcount);
	public List<BoardVO> searchDown(SearchVO searchVO, String board_id);
	public List<BoardVO> searchAho(SearchVO searchVO, String board_id);
	public List<BoardVO> searchTotal(int page, SearchVO searchVO, String board_id);
	public List<BoardVO> searchAccount(int page, SearchVO searchVO);
	public CommentVO commentView(String num, String board_id);
	public void updateComment(CommentVO commentVO, String board_id, String comment_num);
	public int commentDelete(String comment_num, String board_id);
	public void insertBoard(BoardVO boardVO, String board_id);
	public int updateBoard(BoardVO boardVO, String num);
	
}
