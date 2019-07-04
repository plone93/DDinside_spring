package com.DDinside.desktop.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DDinside.desktop.domain.MemberVO;
import com.DDinside.desktop.servicce.LoginService;
import com.DDinside.desktop.servicce.MemberService;
import com.DDinside.desktop.servicce.RegisterService;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class MemberController {
	
	//RequestMapping("/Board/*")
	//private DDinsideService service;
	private RegisterService register_service;
	private LoginService login_service;
	private MemberService member_service;
	
	@PostMapping("/Register")
	public String Register(MemberVO memberVO,
						   Model model) {
		String url = "index";
		
		register_service.insertMember(memberVO);
		model.addAttribute("message", "アカウント登録に成功しました。");
		
		return url;
	}
	
	@PostMapping("/MemberLogin")
	public String MemberLogin(Model model,
					  @RequestParam("login_id") String login_id,
					  @RequestParam("login_pass") String login_pass,
					  HttpSession session, HttpServletRequest request) {
		String url = "index";
		int result = login_service.MemberLogin(login_id, login_pass);
		
		if(result == 1) { //로그인에 성공 했을 경우
			MemberVO memberVO = login_service.getMember(login_id);
			session = request.getSession();
			session.setAttribute("loginUser", memberVO);
			
			model.addAttribute("message", "ログインしました。");
		} else if(result == 0) { // 비밀번호가 틀렸을 경우
			model.addAttribute("message", "IDやパスワードを確認してください。");
		} else if(result == -1) { // 존재 하지 않을 경우
			model.addAttribute("message", "登録されていません。");
		}
		return url;	
	}
	
	@PostMapping("/AdminLogin")
	public String AdminLogin(Model model,
						   @RequestParam("login_id") String login_id,
						   @RequestParam("login_pass") String login_pass,
						   HttpSession session, HttpServletRequest request) {
		String url = "index";
		int result = login_service.AdminLogin(login_id, login_pass);
		
		if(result == 1) {
			MemberVO memberVO = login_service.getAdmin(login_id);
			session = request.getSession();
			session.setAttribute("Admin", memberVO);
			model.addAttribute("message", "ログインしました。");
			
		} else if(result == 0) { // 비밀번호가 틀렸을 경우
			model.addAttribute("message", "パスワードを確認してください。");
		} else if(result == -1) { // 존재 하지 않을 경우
			model.addAttribute("message", "登録されていません。");
		}
		
		return url;
	}
	
	@GetMapping("/Logout")
	public String Logout(Model model,
					   HttpSession session, HttpServletRequest request) {
		String url = "index";
		session = request.getSession();
		session.invalidate();
		
		model.addAttribute("message", "ログアウトしました。");
		return url;
	}
	
	@PostMapping("/EditProfile")
	public String EditProfile(Model model,
							  MemberVO memberVO,
							  @RequestParam("num") String user_Num) {
		
		String url = "index";
		int result = 0;
		result = member_service.updateMember(memberVO, user_Num);
		
		if(result == 1) {
			model.addAttribute("message", "プロフィールが更新されました。");
		} else if(result == 0){
			model.addAttribute("message", "プロフィール更新に失敗しました。");
		}
	
		return url;
	}
	
	@GetMapping("/GetProfile")
	public String GetProfile(@RequestParam("num") String user_Num,
							 Model model) {
		String url = "Login/EditProfile";		
		MemberVO memberVO = member_service.getMember(user_Num);
	
		model.addAttribute("memberVO", memberVO);
		model.addAttribute("edit", "edit");
		return url;
	}
	
	@GetMapping("/MemberDelete")
	public String MemberDelete(Model model,
							 @RequestParam("num") String user_Num,
							 @RequestParam("board_id") String board_id) {
		String url = "BoardList?board_id="+board_id;

		int result = 0;
		result = member_service.memberDelete(user_Num);
		
		if(result == 1) {
			model.addAttribute("message", "アカウントを削除しました。");
		} else if(result == 0){
			model.addAttribute("message", "アカウント削除に失敗しました。");
		}
		
		return url;
	}
	
	@GetMapping("/AccountClose")
	public String AccountClose(Model model,
							 @RequestParam("num") String user_Num,
							 HttpSession session, HttpServletRequest request) {
		MemberVO memberVO = member_service.getMember(user_Num);
		model.addAttribute("memberVO", memberVO);
		
		String url ="Login/AccountClose";
		
		return url;	
	}
	
	@PostMapping("/AccountCloseComplete")
	public String AccountCloseComplete(@RequestParam("num") String user_Num,
									   @RequestParam("pass") String pass,
									   Model model) {
		String url = "Main";
		String pass2 = member_service.getMemberPass(user_Num);
		
		if(pass == pass2) {
			member_service.memberDelete(user_Num);
			model.addAttribute("Message", "アカウント削除に完了しました。");
		} else {
			model.addAttribute("Message", "パスワードを確認してください。");
			url = "redirect:/AccountClose?num="+user_Num;
		}
		return url;
	}
	
	@ResponseBody
	@PostMapping("/IdCheck")
	public int IdCheck(@RequestParam("id") String id,
						Model model)  {

		int check = member_service.idCheck(id); // 있으면 1 이상, 없으면 0
		int result = 0;
		if(check == 1) { // 중복이 있으면
			result = 1;  // 사용불가
			model.addAttribute("result", result);
		} else {  // 사용가능
			model.addAttribute("result", result);
		}
		return result;
	}
}
