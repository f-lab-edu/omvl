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

}
