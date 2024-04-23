package com.omvl.omvl.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	/**
	 * 기본창
	 */
	@GetMapping("/")
	public String home(HttpSession httpSession) {
		httpSession.removeAttribute("member");
		return "home";
	}

}
