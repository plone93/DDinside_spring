package com.DDinside.desktop.servicce;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DDinside.desktop.domain.BoardVO;
import com.DDinside.desktop.domain.CommentVO;
import com.DDinside.desktop.domain.CountVO;
import com.DDinside.desktop.domain.MemberVO;
import com.DDinside.desktop.domain.SearchVO;
import com.DDinside.desktop.mapper.Suv_DDinsideMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Suv_DDinsideServiceImpl implements Suv_DDinsideService {
	
	@Autowired
	Suv_DDinsideMapper mapper;

	@Override
	public List<BoardVO> selectAllBoards(String board_id) {
		List<BoardVO> list = mapper.selectAllBoards(board_id);
		return list;
	}

	@Override
	public String selectUserId(String num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("board_id", board_id);
		
		return mapper.selectUserId(map);
	}

	@Override
	public BoardVO BoardView(String num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		//map.put("board_id", board_id);
		
		return mapper.BoardView(map);
	}

	@Override
	public void updateReadCount(String num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("board_id", board_id);
		
		mapper.updateReadCount(map);
	}

	@Override
	public void postUpCount(String num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("board_id", board_id);
		
		mapper.postUpCount(map);
	}

	@Override
	public void postDownCount(String num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("board_id", board_id);
		
		mapper.postDownCount(map);
	}

	@Override
	public void postAhoCount(String num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("board_id", board_id);
		
		mapper.postAhoCount(map);
	}

	@Override
	public void postReport(String num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("board_id", board_id);
		
		mapper.postReport(map);
	}

	@Override
	public int getReadCount(String num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("board_id", board_id);
		
		return mapper.getReadCount(map);
	}

	@Override
	public CountVO getPostCount(String num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("board_id", board_id);
		
		CountVO countVO = mapper.getPostCount(map);
		return countVO;
	}

	@Override
	public void boardDelete(String num, String board_id) {
		mapper.boardDelete(num, board_id);
	}

	@Override
	public List<BoardVO> selectAllBoard(int page, String board_id) {
		List<BoardVO> list = mapper.selectAllBoard(page, board_id);
		return list;
	}

	@Override
	public List<BoardVO> selectAllBoard(int page, String board_id, String word, String hotcount) {
		List<BoardVO> list = mapper.selectAllBoard(page, board_id, word, hotcount);
		return list;
	}

	/*
	 * @Override public List<BoardVO> selectAllBoard_suv(int page, String board_id,
	 * String word, String hotcount) { int startNum = (page-1)*10+1; int endNum =
	 * page*10;
	 * 
	 * HashMap<String, Object> map = new HashMap<String, Object>();
	 * map.put("board_id", board_id); map.put("startNum", startNum);
	 * map.put("endNum", endNum); map.put("word", word); map.put("hotcount",
	 * hotcount);
	 * 
	 * List<BoardVO> list = mapper.selectAllBoard_suv(map); return list; }
	 */

	@Override
	public List<BoardVO> selectAllBoardTotal(int page, String board_id) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("board_id", board_id);
		
		List<BoardVO> list = mapper.selectAllBoardTotal(map);
		return list;
	}

	@Override
	public List<BoardVO> selectAllBoardReport(int page, String board_id, String word) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("board_id", board_id);
		map.put("word", word);
		
		List<BoardVO> list = mapper.selectAllBoardReport(map);
		return list;
	}

	@Override
	public List<BoardVO> selectAllBoardNotice(int page, String board_id) {
		List<BoardVO> list = mapper.selectAllBoardNotice(page, board_id);
		return list;
	}

	@Override
	public List<MemberVO> selectAllAccount(int page) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<MemberVO> list = mapper.selectAllAccount(map);
		return list;
	}

	@Override
	public int tableCount(String board_id, String hotcount, String word) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("hotcount", hotcount);
		map.put("word", word);
		
		return mapper.tableCount(map);
	}

	@Override
	public int tableCountTotal(String board_id) {
		return mapper.tableCountTotal(board_id);
	}

	@Override
	public int memberCountTotal() {
		return mapper.memberCountTotal();
	}

	@Override
	public int tableCountReport(String board_id, String word) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("word", word);
		
		return mapper.tableCountReport(map);
	}

	@Override
	public int tableCountNotice(String board_id) {
		return mapper.tableCountNotice(board_id);
	}

	@Override
	public int tableCountView(String board_id) {
		return mapper.tableCountView(board_id);
	}

	@Override
	public int searchTableCount(SearchVO searchVO, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", searchVO);
		map.put("board_id", board_id);
		
		return mapper.searchTableCount(map);
	}

	@Override
	public int searchTableCount(SearchVO searchVO, String board_id, String word, String hotcount) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", searchVO);
		map.put("board_id", board_id);
		map.put("word", word);
		map.put("hotcount", hotcount);
		
		return mapper.searchTableCount(map);
	}

	@Override
	public int searchTableCountReport(SearchVO searchVO, String board_id, String word, String hotcount) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", searchVO);
		map.put("board_id", board_id);
		map.put("word", word);
		map.put("hotcount", hotcount);
		
		return mapper.searchTableCountReport(map);
	}

	@Override
	public int searchTableCountDown(SearchVO searchVO, String board_id) {
		return mapper.searchTableCountDown(searchVO, board_id);
	}

	@Override
	public int searchTableCountAho(SearchVO searchVO, String board_id) {
		return mapper.searchTableCountAho(searchVO, board_id);
	}

	@Override
	public int searchTableCountTotal(SearchVO searchVO, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", searchVO);
		map.put("board_id", board_id);
		
		return mapper.searchTableCountTotal(map);
	}

	@Override
	public int searchMemberCount(SearchVO searchVO) {
		return mapper.searchMemberCount(searchVO);
	}

	@Override
	public List<BoardVO> selectNotice() {
		List<BoardVO> boardVO = mapper.selectNotice();
		return boardVO;
	}

	@Override
	public int insertComment(CommentVO commentVO, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("commentVO", commentVO);
		
		return mapper.insertComment(map);

	}

	@Override
	public List<CommentVO> selectComment(String num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("board_id", board_id);
		
		List<CommentVO> list = mapper.selectComment(map);
		return list;
	}

	@Override
	public List<BoardVO> search(int page, SearchVO searchVO, String board_id, String word, String hotcount) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("searchVO", searchVO);
		map.put("board_id", board_id);
		map.put("word", word);
		map.put("hotcount", hotcount);
		
		List<BoardVO> list = mapper.search(map);
		return list;
	}

	@Override
	public List<BoardVO> searchReport(int page, SearchVO searchVO, String board_id, String word, String hotcount) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("searchVO", searchVO);
		map.put("board_id", board_id);
		map.put("word", word);
		map.put("hotcount", hotcount);
		
		List<BoardVO> list = mapper.searchReport(map);
		return list;
	}

	@Override
	public List<BoardVO> searchDown(SearchVO searchVO, String board_id) {
		List<BoardVO> list = mapper.searchDown(searchVO, board_id);
		return list;
	}

	@Override
	public List<BoardVO> searchAho(SearchVO searchVO, String board_id) {
		List<BoardVO> list = mapper.searchAho(searchVO, board_id);
		return list;
	}

	@Override
	public List<BoardVO> searchTotal(int page, SearchVO searchVO, String board_id) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("searchVO", searchVO);
		map.put("board_id", board_id);
		
		List<BoardVO> list = mapper.searchTotal(map);
		return list;
	}

	@Override
	public List<BoardVO> searchAccount(int page, SearchVO searchVO) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("searchVO", searchVO);
		
		List<BoardVO> list = mapper.searchAccount(map);
		return list;
	}

	@Override
	public CommentVO commentView(String num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("board_id", board_id);
		
		CommentVO commentVO = mapper.commentView(map);
		return commentVO;
	}

	@Override
	public void updateComment(CommentVO commentVO, String board_id, String comment_num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("commentVO", commentVO);
		map.put("board_id", board_id);
		map.put("comment_num", comment_num);
		
		mapper.updateComment(map);
	}

	@Override
	public int commentDelete(String comment_num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("comment_num", comment_num);
		map.put("board_id", board_id);
		
		return mapper.commentDelete(map);
	}

	@Override
	public void insertBoard(BoardVO boardVO, String board_id) {
		mapper.insertBoard(boardVO, board_id);
	}

	@Override
	public int updateBoard(BoardVO boardVO, String num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardVO", boardVO);
		map.put("num", num);
		
		return mapper.updateBoard(map);
	}

	@Override
	public List<BoardVO> selectAllBoard_Up(int page, String board_id, String hotcount) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("board_id", board_id);
		map.put("hotcount", hotcount);
		
		return mapper.selectAllBoard_Up(map);
	}

	@Override
	public List<BoardVO> selectAllBoard_Down(int page, String board_id, String hotcount) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("board_id", board_id);
		map.put("hotcount", hotcount);
		
		return mapper.selectAllBoard_Down(map);
	}

	@Override
	public List<BoardVO> selectAllBoard_Aho(int page, String board_id, String hotcount) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("board_id", board_id);
		map.put("hotcount", hotcount);
		
		return mapper.selectAllBoard_Aho(map);
	}

	@Override
	public int tableCount_Up(String board_id, String hotcount) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("hotcount", hotcount);
		
		return mapper.tableCount_Up(map);
	}

	@Override
	public int tableCount_Down(String board_id, String hotcount) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("hotcount", hotcount);
		
		return mapper.tableCount_Down(map);
	}

	@Override
	public int tableCount_Aho(String board_id, String hotcount) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("hotcount", hotcount);
		
		return mapper.tableCount_Aho(map);
	}

}
