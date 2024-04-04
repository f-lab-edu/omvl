package com.omvl.omvl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

	@GetMapping
	String welcome() {
		return "/items/welcome";
	}
}
