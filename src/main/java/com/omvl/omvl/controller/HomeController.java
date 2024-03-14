package com.omvl.omvl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	/**
	 * 기본창
	 */
	@GetMapping("/")
	public String home() {
		return "home";
	}

	/**
	 * 로그인 성공 후 상품창
	 */
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
}
