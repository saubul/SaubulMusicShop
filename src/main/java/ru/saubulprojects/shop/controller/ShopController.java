package ru.saubulprojects.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
	
	@GetMapping("/")
	public String homeForm() {
		return "shop/home";
	}
	
}
