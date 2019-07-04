package com.DDinside.desktop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DDinside.desktop.domain.BoardVO;
import com.DDinside.desktop.domain.CommentVO;
import com.DDinside.desktop.domain.CountVO;
import com.DDinside.desktop.domain.MemberVO;
import com.DDinside.desktop.domain.PageVO;
import com.DDinside.desktop.domain.SearchVO;
import com.DDinside.desktop.servicce.DDinsideService;
import com.DDinside.desktop.servicce.Suv_DDinsideService;


import lombok.AllArgsConstructor;



@Controller
@AllArgsConstructor
public class BoardController {
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	private DDinsideService service;
	private Suv_DDinsideService suv_service;
	//DAO를 담당하는 객체
	
	@GetMapping("/BoardList")
	public String BoardList(@RequestParam("board_id") String board_id, 
							HttpSession session, HttpServletRequest request,
							Model model) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		int page = 1;
		int count = 0;
		String url = "Board/BoardList";
		String url1 = "Board/BoardList?board_id="+board_id;
		
		System.out.println("현재 게시판 : "+board_id);
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		PageVO pageVO = new PageVO();
		pageVO.setPage(page);
		
		String hotcount = "1"; 
		String word;
		if(board_id.equals("高評価")) {
			word = "up";
			list = suv_service.selectAllBoard_Up(page, board_id, hotcount);
			pageVO.setTotalCount(suv_service.tableCount_Up(board_id, hotcount));// DAO에서 총 테이블 갯수를 검색해야함
			count = suv_service.tableCount_Up(board_id, hotcount);
		} else if(board_id.equals("低評価")) {
			word = "down";
			list = suv_service.selectAllBoard_Down(page, board_id, hotcount);
			pageVO.setTotalCount(suv_service.tableCount_Down(board_id, hotcount));// DAO에서 총 테이블 갯수를 검색해야함
			count = suv_service.tableCount_Down(board_id, hotcount); 
		} else if(board_id.equals("アホ")) {
			word = "aho";
			list = suv_service.selectAllBoard_Aho(page, board_id, hotcount);
			pageVO.setTotalCount(suv_service.tableCount_Aho(board_id, hotcount));// DAO에서 총 테이블 갯수를 검색해야함
			count = suv_service.tableCount_Aho(board_id, hotcount); 
		} else if(board_id.equals("統合")) {
			list = suv_service.selectAllBoardTotal(page, board_id);
			pageVO.setTotalCount(suv_service.tableCountTotal(board_id));// DAO에서 총 테이블 갯수를 검색해야함
			count = suv_service.tableCountTotal(board_id);
		} else if(board_id.equals("申告管理")) {
			word = "report";
			list = suv_service.selectAllBoardReport(page, board_id, word);
			pageVO.setTotalCount(suv_service.tableCountReport(board_id, word));// DAO에서 총 테이블 갯수를 검색해야함
			count = suv_service.tableCountReport(board_id, word); 
		} else if(board_id.equals("アカウント管理")) {
			memberList = suv_service.selectAllAccount(page);
			pageVO.setTotalCount(suv_service.memberCountTotal());// DAO에서 총 테이블 갯수를 검색해야함
			count = suv_service.memberCountTotal();
			model.addAttribute("memberList", memberList);
			model.addAttribute("listCount", count);
			url = "MemberList";
		} else {
			list = service.selectAllBoard(page, board_id);
			pageVO.setTotalCount(service.tableCount(board_id));// DAO에서 총 테이블 갯수를 검색해야함
			count = service.tableCount(board_id);
		}
		
		model.addAttribute("boardList",list);
		model.addAttribute("listCount",count);
		model.addAttribute("page",pageVO);
		
		String noticeBoard_id = "お知らせ";
		List<BoardVO> noticeList = service.selectNotice(noticeBoard_id);
		model.addAttribute("NoticeList", noticeList);
		model.addAttribute("board_id", board_id);
		
		if(request.getParameter("message") != null) { // 리다이렉트로 날아온 메세지가 있다면 전달
			model.addAttribute("message", request.getParameter("message"));
		}
		
		return url;
	}
	
	@PostMapping("/BoardWrite")
	public String BoardWrite(@RequestParam("board_id") String board_id,
						   //RequestParam("item") MultipartFile item,
						   HttpServletRequest request, HttpServletResponse response,
						   BoardVO boardVO, Model model) {
	
		String url = "redirect:/BoardList";
		service.insertBoard(boardVO, board_id);
		model.addAttribute("board_id", board_id);
		
		return url;
	}
	
	@GetMapping("/BoardEdit")
	public String PostEdit(@RequestParam("board_id") String board_id,
						 @RequestParam("num") String num,
						 Model model) {
		
		String url = "Board/BoardEdit";
		BoardVO boardVO = new BoardVO();
		
		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
			boardVO = suv_service.BoardView(num, board_id);
		} else {
			boardVO = service.BoardView(num, board_id);
		}
		
		model.addAttribute("BoardEditView", boardVO);
		model.addAttribute("board_id", board_id);
		
		return url;		
	}
	
	@PostMapping("/BoardReWrite")
	public String BoardReWrite(@RequestParam("board_id") String board_id,
							   @RequestParam("num") String num,
							 BoardVO boardVO,Model model) {
		String url = "redirect:/BoardList";
		int result = 0;
		
		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
			result = suv_service.updateBoard(boardVO, num);
		} else {
			result = service.updateBoard(boardVO, num);
		}
		if(result == 1) { // 업데이트에 성공
			model.addAttribute("message", "スレッドを更新しました。");
		} else {
			model.addAttribute("message", "スレッド更新に失敗しました。");
		}
		model.addAttribute("board_id", board_id);		
		
		return url;
		
	}
	
	@GetMapping("/BoardDelete")
	public String BoardDelete(@RequestParam("board_id") String board_id,
							@RequestParam("num") String num,
							Model model) {
		String url = "redirect:/BoardList";
		//String url = "redirect:/BoardList?board_id="+board_id;
		
		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
			suv_service.boardDelete(num, board_id);
		} else {
			service.boardDelete(num, board_id);
		}
		
		model.addAttribute("board_id", board_id);
		model.addAttribute("message", "スレッドを削除しました。");
		
		return url;	
	}
	
	@PostMapping("/InsertComment")
	public String BoardComment(@RequestParam("board_id") String board_id,
							 @RequestParam("board_num") String board_num,
							 CommentVO commentVO,
							 Model model) {
		String url = "redirect:/BoardView?num="+board_num;
		int result = 0;
		int commentCount = 0;
		
		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
			result = suv_service.insertComment(commentVO, board_id);
		} else {
			result = service.insertComment(commentVO, board_id);
		}
		
		if(result == 1) { // 성공
			commentCount = service.commentCount(board_num, board_id);
			service.commentCountUpdate(commentCount, board_num);
			model.addAttribute("message", "コメントを作成しました。");
		} else {
			model.addAttribute("message", "コメント作成に失敗しました。");
		}
		model.addAttribute("board_id", board_id);
			
		return url;
	}
	
	@GetMapping("/PostUp")
	public String PostUp(@RequestParam("board_id") String board_id,
					   @RequestParam("num") String num,
					   HttpServletRequest request, HttpServletResponse response,
					   Model model) {
		
		String url ="redirect:/BoardView?num="+num;
		CountVO countVO = new CountVO();
		//쿠키 
		Boolean found = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		//쿠키 유뮤 검사
		for(int i=0; i < cookies.length; i++) {
			info = cookies[i];
			if(info.getName().equals("PostUp"+num)) { //이름 = 쿠키이름+보드이름+글번호
				found = true;
				break;
			}
		}
		String str = ""+System.currentTimeMillis();//시간을 쪼갬
		if(found) { //쿠키가 있다면  알림창
			model.addAttribute("message", "既にクリックしました。"); //경고창
		}else if(!found) {  //쿠키가 없다면
			info = new Cookie("PostUp"+num, str);
			info.setMaxAge(24*60*60); //쿠키 유효 시간 설정
			response.addCookie(info);
			if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
				suv_service.postUpCount(num, board_id);  //조회수 증가
			} else {
				service.postUpCount(num, board_id); 
			}	
		}
		
		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
			countVO = suv_service.getPostCount(num, board_id);
		} else {
			countVO = service.getPostCount(num, board_id);
		}	
		model.addAttribute("PostCount", countVO);
		model.addAttribute("board_id", board_id);
		
		return url;
	}
	
	@GetMapping("/PostDown")
	public String PostDown(@RequestParam("board_id") String board_id,
						   @RequestParam("num") String num,
						   HttpServletRequest request, HttpServletResponse response,
						   Model model) {
		
		String url ="redirect:/BoardView?num="+num;
		
		CountVO countVO = new CountVO();
		//쿠키 
		Boolean found = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		//쿠키 유뮤 검사
		for(int i=0; i < cookies.length; i++) {
			info = cookies[i];
			if(info.getName().equals("PostDown"+num)) { //이름 = 쿠키이름+보드이름+글번호
				found = true;
				break;
			}
		}
		String str = ""+System.currentTimeMillis();//시간을 쪼갬
		if(found) { //쿠키가 있다면  알림창
			model.addAttribute("message", "既にクリックしました。"); //경고창
		}else if(!found) {  //쿠키가 없다면
			info = new Cookie("PostDown"+num, str);
			info.setMaxAge(24*60*60); //쿠키 유효 시간 설정
			response.addCookie(info);
			if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
				suv_service.postDownCount(num, board_id); // num으로 찾기
			} else {
				service.postDownCount(num, board_id);  //아이디+num으로 찾기
			}	
		}
		
		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
			countVO = suv_service.getPostCount(num, board_id);
		} else {
			countVO = service.getPostCount(num, board_id);
		}	
		model.addAttribute("PostCount", countVO);
		model.addAttribute("board_id", board_id);
		
		return url;
	}
	
	@GetMapping("/PostAho")
	public String PostAho(@RequestParam("board_id") String board_id,
					    @RequestParam("num") String num,
					    HttpServletRequest request, HttpServletResponse response,
					    Model model) {
		
		String url ="redirect:/BoardView?num="+num;
		
		CountVO countVO = new CountVO();
		//쿠키 
		Boolean found = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		//쿠키 유뮤 검사
		for(int i=0; i < cookies.length; i++) {
			info = cookies[i];
			if(info.getName().equals("PostAho"+num)) { //이름 = 쿠키이름+보드이름+글번호
				found = true;
				break;
			}
		}
		String str = ""+System.currentTimeMillis();//시간을 쪼갬
		if(found) { //쿠키가 있다면  알림창
			model.addAttribute("message", "既にクリックしました。"); //경고창
		}else if(!found) {  //쿠키가 없다면
			info = new Cookie("PostAho"+num, str);
			info.setMaxAge(24*60*60); //쿠키 유효 시간 설정
			response.addCookie(info);
			if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
				suv_service.postAhoCount(num, board_id); // num으로 찾기
			} else {
				service.postAhoCount(num, board_id);  //아이디+num으로 찾기
			}	
		}
		
		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
			countVO = suv_service.getPostCount(num, board_id);
		} else {
			countVO = service.getPostCount(num, board_id);
		}	
		model.addAttribute("PostCount", countVO);
		model.addAttribute("board_id", board_id); // 모델에서 값을 보내고 redirect로 받을땐 url 같은 변수를 보내지 않아도 됨
		
		return url;
	}
	
	@GetMapping("/PostReport")
	public String PostReport(@RequestParam("board_id") String board_id,
						   @RequestParam("num") String num,
						   HttpServletRequest request, HttpServletResponse response,
						   Model model) {
		
		String url ="redirect:/BoardView?num="+num;
		
		//쿠키 
		Boolean found = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		//쿠키 유뮤 검사
		for(int i=0; i < cookies.length; i++) {
			info = cookies[i];
			if(info.getName().equals("PostReport"+num)) { //이름 = 쿠키이름+보드이름+글번호
				found = true;
				break;
			}
		}
		String str = ""+System.currentTimeMillis();//시간을 쪼갬
		if(found) { //쿠키가 있다면  알림창
			model.addAttribute("message", "既にクリックしました。"); //경고창
		}else if(!found) {  //쿠키가 없다면
			info = new Cookie("PostReport"+num, str);
			info.setMaxAge(24*60*60); //쿠키 유효 시간 설정
			response.addCookie(info);
			if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
				suv_service.postReport(num, board_id);
			} else {
				service.postReport(num, board_id);
			}	
		}
		
		model.addAttribute("board_id", board_id);
		return url;	
	}
	
	@GetMapping("/Search")
	public String Search_Get(@RequestParam("board_id") String board_id,
					   HttpServletRequest request, HttpServletResponse response,
					   SearchVO searchVO,
					   Model model) {
		String url = "Board/BoardSearchList";
		System.out.println("키워드 : " + searchVO.getKeyWord());
		
		List<BoardVO> list = new ArrayList<BoardVO>();	
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		PageVO pageVO = new PageVO();
		pageVO.setPage(page);
		
		int count = 0;
		String hotcount = "5";
		if(board_id.equals("高評価")) {
			String word = "up";
			list = suv_service.search(page, searchVO, board_id, word, hotcount);
			pageVO.setTotalCount(suv_service.searchTableCount(searchVO, board_id, word, hotcount));
			count = suv_service.searchTableCountReport(searchVO, board_id, word, hotcount);
		} else if(board_id.equals("低評価")) {
			String word = "down";
			list = suv_service.search(page, searchVO, board_id, word, hotcount);
			pageVO.setTotalCount(suv_service.searchTableCount(searchVO, board_id, word, hotcount));
			count = suv_service.searchTableCountReport(searchVO, board_id, word, hotcount);
		} else if(board_id.equals("アホ")) {
			String word = "aho";
			list = suv_service.search(page, searchVO, board_id, word, hotcount);
			pageVO.setTotalCount(suv_service.searchTableCount(searchVO, board_id, word, hotcount));
			count = suv_service.searchTableCountReport(searchVO, board_id, word, hotcount);
		} else if(board_id.equals("統合")) {
			list = suv_service.searchTotal(page, searchVO, board_id);
			pageVO.setTotalCount(suv_service.searchTableCountTotal(searchVO, board_id));
			count = suv_service.searchTableCountTotal(searchVO, board_id);
		} else if(board_id.equals("申告管理")) {
			String word = "report";
			list = suv_service.searchReport(page, searchVO, board_id, word, hotcount);
			pageVO.setTotalCount(suv_service.searchTableCountReport(searchVO, board_id, word, hotcount));
			count = suv_service.searchTableCountReport(searchVO, board_id, word, hotcount);
		} else if(board_id.equals("アカウント管理")) {
			list = suv_service.searchAccount(page, searchVO);
			pageVO.setTotalCount(suv_service.searchMemberCount(searchVO));
			count = suv_service.searchMemberCount(searchVO);
			url = "Board/MemberList";
		} else {
			list = service.search(page, searchVO, board_id);
			pageVO.setTotalCount(service.searchTableCount(searchVO, board_id));
			count = service.searchTableCount(searchVO, board_id);
		}
		
		model.addAttribute("message", count+"件検索されました。");
		model.addAttribute("searchList", list);
		model.addAttribute("page", pageVO);
		model.addAttribute("board_id", board_id);
		model.addAttribute("searchCount", count);
		model.addAttribute("keyWord", searchVO.getKeyWord());
		
		String noticeBoard_id = "お知らせ";
		List<BoardVO> noticeList = service.selectNotice(noticeBoard_id);
		model.addAttribute("NoticeList", noticeList);
		
		return url;
	}
	
	
	@PostMapping("/Search")
	public String Search_Post(@RequestParam("board_id") String board_id,
					   HttpServletRequest request, HttpServletResponse response,
					   SearchVO searchVO,
					   Model model) {
		String url = "Board/BoardSearchList";
		System.out.println("키워드 : " + searchVO.getKeyWord());
		
		List<BoardVO> list = new ArrayList<BoardVO>();	
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		PageVO pageVO = new PageVO();
		pageVO.setPage(page);
		
		int count = 0;
		String hotcount = "5";
		if(board_id.equals("高評価")) {
			String word = "up";
			list = suv_service.search(page, searchVO, board_id, word, hotcount);
			pageVO.setTotalCount(suv_service.searchTableCount(searchVO, board_id, word, hotcount));
			count = suv_service.searchTableCountReport(searchVO, board_id, word, hotcount);
		} else if(board_id.equals("低評価")) {
			String word = "down";
			list = suv_service.search(page, searchVO, board_id, word, hotcount);
			pageVO.setTotalCount(suv_service.searchTableCount(searchVO, board_id, word, hotcount));
			count = suv_service.searchTableCountReport(searchVO, board_id, word, hotcount);
		} else if(board_id.equals("アホ")) {
			String word = "aho";
			list = suv_service.search(page, searchVO, board_id, word, hotcount);
			pageVO.setTotalCount(suv_service.searchTableCount(searchVO, board_id, word, hotcount));
			count = suv_service.searchTableCountReport(searchVO, board_id, word, hotcount);
		} else if(board_id.equals("統合")) {
			list = suv_service.searchTotal(page, searchVO, board_id);
			pageVO.setTotalCount(suv_service.searchTableCountTotal(searchVO, board_id));
			count = suv_service.searchTableCountTotal(searchVO, board_id);
		} else if(board_id.equals("申告管理")) {
			String word = "report";
			list = suv_service.searchReport(page, searchVO, board_id, word, hotcount);
			pageVO.setTotalCount(suv_service.searchTableCountReport(searchVO, board_id, word, hotcount));
			count = suv_service.searchTableCountReport(searchVO, board_id, word, hotcount);
		} else if(board_id.equals("アカウント管理")) {
			list = suv_service.searchAccount(page, searchVO);
			pageVO.setTotalCount(suv_service.searchMemberCount(searchVO));
			count = suv_service.searchMemberCount(searchVO);
			url = "Board/MemberList";
		} else {
			list = service.search(page, searchVO, board_id);
			pageVO.setTotalCount(service.searchTableCount(searchVO, board_id));
			count = service.searchTableCount(searchVO, board_id);
		}
		
		model.addAttribute("searchList", list);
		model.addAttribute("page", pageVO);
		model.addAttribute("board_id", board_id);
		model.addAttribute("searchCount", count);
		model.addAttribute("keyWord", searchVO.getKeyWord());
		
		String noticeBoard_id = "お知らせ";
		List<BoardVO> noticeList = service.selectNotice(noticeBoard_id);
		model.addAttribute("NoticeList", noticeList);
		
		return url;
	}
	
	@GetMapping("/BoardView")
	public String BoardView(Model model,
							@RequestParam("num") String num,
							@RequestParam("board_id") String board_id,
							HttpSession session, HttpServletRequest request) {
		String url = "Board/BoardView";
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合") || board_id.equals("申告管理")) {
			List<CommentVO> commentList = suv_service.selectComment(num, board_id);
			int readcount = suv_service.getReadCount(num, board_id);
			suv_service.updateReadCount(num, board_id);
			BoardVO boardVO = suv_service.BoardView(num, board_id);
			CountVO countVO = suv_service.getPostCount(num, board_id);
			String writer = suv_service.selectUserId(num, board_id);
			int count = suv_service.tableCountView(board_id);
			
			model.addAttribute("comment", commentList);
			model.addAttribute("readcount", readcount);
			model.addAttribute("writer", writer);
			model.addAttribute("listCount", count);
			model.addAttribute("boardView", boardVO);
			model.addAttribute("PostCount", countVO);
			
		} else {
			List<CommentVO> commentList = service.selectComment(num, board_id);
			int readcount = service.getReadCount(num, board_id);
			service.updateReadCount(num, board_id);
			BoardVO boardVO = service.BoardView(num, board_id);
			CountVO countVO = service.getPostCount(num, board_id);
			String writer = service.selectUserId(num, board_id);
			int count = service.tableCount(board_id);
			
			model.addAttribute("comment", commentList);
			model.addAttribute("readcount", readcount);
			model.addAttribute("writer", writer);
			model.addAttribute("listCount", count);
			model.addAttribute("boardView", boardVO);
			model.addAttribute("PostCount", countVO);
	
		}
		
		
		String noticeBoard_id = "お知らせ";
		List<BoardVO> noticeList = service.selectNotice(noticeBoard_id);
		model.addAttribute("NoticeList", noticeList);
		model.addAttribute("board_id", board_id);
		
		if(request.getParameter("message") != null) { // 리다이렉트로 날아온 메세지가 있다면 전달
			model.addAttribute("message", request.getParameter("message"));
		}
		
		return url;
	}
	
	
	@GetMapping("/CommentEdit")
	public String CommentEdit(@RequestParam("board_id") String board_id,
							@RequestParam("num") String num,
							@RequestParam("board_num")String board_num,
							Model model) {
		String url = "Board/BoardCommentEdit";
		CommentVO commentVO = new CommentVO();
		
		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
			commentVO = suv_service.commentView(num, board_id);
		} else {
			commentVO = service.commentView(num, board_id);
		}
		
		model.addAttribute("commentEditView", commentVO);
		model.addAttribute("board_num", board_num);
		model.addAttribute("board_id", board_id);
		
		return url;
	}
	
	@GetMapping("/CommentDelete")
	public String CommentDelete(@RequestParam("board_id") String board_id,
							  @RequestParam("board_num") String board_num,
							  @RequestParam("num") String comment_num,
							  Model model) {
		String url = "redirect:/BoardView?num="+board_num;
		int result = 0;
		int commentCount = 0;
		
		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
			result = suv_service.commentDelete(comment_num, board_id);
		} else {
			result = service.commentDelete(comment_num, board_id);
		}
		
		if(result == 1) {
			commentCount = service.commentCount(board_num, board_id);
			service.commentCountUpdate(commentCount, board_num);
			model.addAttribute("message", "コメントを削除しました。");
		} else {
			model.addAttribute("message", "コメント削除に失敗しました。");
		}
		
		
		model.addAttribute("board_id", board_id);
		return url;
	}
	
	@PostMapping("/BoardCommentReWrite")
	public String BoardCommentReWrite(@RequestParam("board_id") String board_id,
									@RequestParam("board_num") String board_num,
									@RequestParam("comment_num") String comment_num,
									CommentVO commentVO,
									Model model) {
		String url = "redirect:/BoardView?num="+board_num;
		
		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
			suv_service.updateComment(commentVO, board_id, comment_num); // 콘텐트만 들어있음
		} else {
			service.updateComment(commentVO, board_id, comment_num);
		}
		model.addAttribute("board_id", board_id);
		
		return url;
	}
	
	@GetMapping("/Page")
	public void Page(@RequestParam("board_id") String board_id) {
		
	}
	

}
