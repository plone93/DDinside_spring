package com.DDinside.desktop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.DDinside.desktop.domain.BoardVO;
import com.DDinside.desktop.servicce.DDinsideService;
import com.DDinside.desktop.servicce.Suv_DDinsideService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {
	
	private DDinsideService service;
	private Suv_DDinsideService suv_service;
	
	@GetMapping("Main")
	public String Main(Model model) {
		String url = "index";
		
		List<BoardVO> BestList = service.mainNoticeBest();
		List<BoardVO> WorstList = service.mainNoticeWorst();
		List<BoardVO> AhoList = service.mainNoticeAho();
		
		model.addAttribute("Best", BestList);
		model.addAttribute("Worst", WorstList);
		model.addAttribute("Aho", AhoList);
		
		return url;
	}

}
