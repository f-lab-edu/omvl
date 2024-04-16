package com.omvl.omvl.controller;

import com.omvl.omvl.domain.Item;
import com.omvl.omvl.service.ItemService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

	private final ItemService itemService;

	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping
	String welcome() {
		return "/items/welcome";
	}

	@GetMapping("/{type}")
	String showItems(@PathVariable("type") String type, Model model) {

		List<Item> items = itemService.showItems(type);

		model.addAttribute("items", items);
		model.addAttribute("type", type);

		return "/items/showItems";
	}

	@GetMapping("/{type}/{itemId}")
	String showDetail(@PathVariable("itemId") Long id,
					  Model model) {

		Item item = itemService.showDetail(id);

		model.addAttribute("item", item);

		return "/items/showDetail";
	}
}
