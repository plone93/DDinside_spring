package com.DDinside.desktop.servicce;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.DDinside.desktop.domain.*;



//DAO
public interface DDinsideService {
	
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
	public int tableCount(String board_id);
	public int searchTableCount(SearchVO searchVO, String board_id);
	public int searchTableCountSuv(SearchVO searchVO, String word, String hotcount);
	public int searchTableCountTotal(SearchVO searchVO);
	public List<BoardVO> selectNotice(String board_id);
	public int insertComment(CommentVO commentVO, String board_id);
	public List<CommentVO> selectComment(String num, String board_id);
	public int commentCount(String board_num, String board_id);
	public List<BoardVO> search(int page, SearchVO searchVO, String board_id);
	public int searchTotalCount(SearchVO searchVO, String board_id);
	public CommentVO commentView(String num, String board_id);
	public void updateComment(CommentVO commentVO, String board_id, String comment_num);
	public int commentDelete(String comment_num, String board_id);
	public void insertBoard(BoardVO boardVO, String board_id);
	public int updateBoard(BoardVO boardVO, String num);
	public List<BoardVO> mainNoticeBest();
	public List<BoardVO> mainNoticeWorst();
	public List<BoardVO> mainNoticeAho();
	public void commentCountUpdate(int commentCount, String board_num);
	
}
