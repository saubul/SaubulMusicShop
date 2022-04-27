package ru.saubulprojects.shop.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.saubulprojects.shop.model.User;
import ru.saubulprojects.shop.service.UserService;

@Controller
@RequestMapping("/profile")
public class AccountController {
	
	private UserService userService;
	
	public AccountController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public String profileForm(Principal principal) {
		
		User user = userService.findByUsername(principal.getName());
		
		return "account/profile";
	}
	
	@GetMapping("/basket")
	public String basketForm() {
		return "account/basket";
	}
	
}
