package com.DDinside.desktop.servicce;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DDinside.desktop.domain.BoardVO;
import com.DDinside.desktop.domain.CommentVO;
import com.DDinside.desktop.domain.CountVO;
import com.DDinside.desktop.domain.SearchVO;
import com.DDinside.desktop.mapper.DDinsideMapper;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class DDinsideServiceImpl implements DDinsideService {
	
	@Autowired
	DDinsideMapper mapper;

	@Override
	public List<BoardVO> selectAllBoards(String board_id) {
		return mapper.selectAllBoards(board_id);
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
		map.put("board_id", board_id);
		
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
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("board_id", board_id);
		mapper.boardDelete(map);

	}

	@Override
	public List<BoardVO> selectAllBoard(int page, String board_id) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<BoardVO> list = mapper.selectAllBoard(map);
		return list;
	}

	@Override
	public int tableCount(String board_id) {
		return mapper.tableCount(board_id);
	}

	@Override
	public int searchTableCount(SearchVO searchVO, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", searchVO);
		map.put("board_id", board_id);
		
		return mapper.searchTableCount(map);
	}

	@Override
	public int searchTableCountSuv(SearchVO searchVO, String word, String hotcount) {
		return mapper.searchTableCountSuv(searchVO, word, hotcount);
	}

	@Override
	public int searchTableCountTotal(SearchVO searchVO) {
		return mapper.searchTableCountTotal(searchVO);
	}

	@Override
	public List<BoardVO> selectNotice(String board_id) {
		List<BoardVO> list = mapper.selectNotice(board_id);
		return list;
	}

	@Override
	public int insertComment(CommentVO commentVO, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("commentVO", commentVO);
		map.put("board_id", board_id);
		
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
	public int commentCount(String board_num, String board_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("board_num", board_num);
		
		return mapper.commentCount(map);
	}

	@Override
	public List<BoardVO> search(int page, SearchVO searchVO, String board_id) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("searchVO", searchVO);
		map.put("board_id", board_id);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		return mapper.search(map);
	}

	@Override
	public int searchTotalCount(SearchVO searchVO, String board_id) {
		return mapper.searchTotalCount(searchVO, board_id);
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
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardVO", boardVO);
		map.put("board_id", board_id);
		
		mapper.insertBoard(map);
	}

	@Override
	public int updateBoard(BoardVO boardVO, String num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("boardVO", boardVO);
		
		return mapper.updateBoard(map);
	}

	@Override
	public List<BoardVO> mainNoticeBest() {
		List<BoardVO> list = mapper.mainNoticeBest();
		return list;
	}

	@Override
	public List<BoardVO> mainNoticeWorst() {
		List<BoardVO> list = mapper.mainNoticeWorst();
		return list;
	}

	@Override
	public List<BoardVO> mainNoticeAho() {
		List<BoardVO> list = mapper.mainNoticeAho();
		return list;
	}
	
	@Override
	public void commentCountUpdate(int commentCount, String board_num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("commentCount", commentCount);
		map.put("board_num", board_num);
		
		mapper.commentCountUpdate(map);
		
	}
}
