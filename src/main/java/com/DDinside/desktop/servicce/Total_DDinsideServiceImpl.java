package com.DDinside.desktop.servicce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DDinside.desktop.domain.BoardVO;
import com.DDinside.desktop.domain.CommentVO;
import com.DDinside.desktop.domain.CountVO;
import com.DDinside.desktop.domain.SearchVO;
import com.DDinside.desktop.mapper.Total_DDinsideMapper;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class Total_DDinsideServiceImpl implements Total_DDinsideService {
	
	@Autowired
	Total_DDinsideMapper mapper;

	@Override
	public List<BoardVO> selectAllBoards(String board_id) {
		List<BoardVO> list = mapper.selectAllBoards(board_id);
		return list;
	}

	@Override
	public void insertBoard(BoardVO boardVO, String board_id) {
		mapper.insertBoard(boardVO, board_id);
	}

	@Override
	public String selectUserId(String num, String board_id) {
		return mapper.selectUserId(num, board_id);
	}

	@Override
	public BoardVO BoardView(String num, String board_id) {
		BoardVO boardVO = mapper.BoardView(num, board_id);
		return boardVO;
	}

	@Override
	public void updateReadCount(String num, String board_id) {
		mapper.updateReadCount(num, board_id);
	}

	@Override
	public void postUpCount(String num, String board_id) {
		mapper.postUpCount(num, board_id);
	}

	@Override
	public void postDownCount(String num, String board_id) {
		mapper.postDownCount(num, board_id);
	}

	@Override
	public void postAhoCount(String num, String board_id) {
		mapper.postAhoCount(num, board_id);
	}

	@Override
	public void postReport(String num, String board_id) {
		mapper.postReport(num, board_id);

	}

	@Override
	public int getReadCount(String num, String board_id) {
		return mapper.getReadCount(num, board_id);
	}

	@Override
	public CountVO getPostCount(String num, String board_id) {
		CountVO countVO = mapper.getPostCount(num, board_id);
		return countVO;
	}

	@Override
	public void updateBoard(BoardVO boardVO, String board_id) {
		mapper.updateBoard(boardVO, board_id);
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
	public int tableCount(String board_id) {
		return mapper.tableCount(board_id);
	}

	@Override
	public int searchTableCount(SearchVO searchVO, String board_id) {
		return mapper.searchTableCount(searchVO, board_id);
	}

	@Override
	public List<BoardVO> selectNotice() {
		List<BoardVO> list = mapper.selectNotice();
		return list;
	}

	@Override
	public void insertComment(CommentVO commentVO, String board_id) {
		mapper.insertComment(commentVO, board_id);
	}

	@Override
	public List<CommentVO> selectComment(String num, String board_id) {
		List<CommentVO> list = mapper.selectComment(num, board_id);
		return list;
	}

	@Override
	public List<BoardVO> search(SearchVO searchVO, String board_id) {
		List<BoardVO> list = mapper.search(searchVO, board_id);
		return list;
	}

	@Override
	public CommentVO commentView(String num, String board_id) {
		CommentVO commentVO = mapper.commentView(num, board_id);
		return commentVO;
	}

	@Override
	public void updateComment(CommentVO commentVO, String board_id) {
		mapper.updateComment(commentVO, board_id);
	}

	@Override
	public void commentDelete(String comment_num, String board_id) {
		mapper.commentDelete(comment_num, board_id);
	}

}
