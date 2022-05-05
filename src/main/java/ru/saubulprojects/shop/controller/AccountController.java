package ru.saubulprojects.shop.controller;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.saubulprojects.shop.model.User;
import ru.saubulprojects.shop.service.UserService;

@Controller
@RequestMapping("/profile")
public class AccountController {
	
	private UserService userService;
	private User user;
	
	public AccountController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public String profileForm(Model model) {
		
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		model.addAttribute("user", user);
		return "account/profile";
	}
	
	@GetMapping("/basket")
	public String basketForm(Model model) {
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		model.addAttribute("user", user);
		model.addAttribute("basketProducts", user.getBasket().getBasketProducts());
		return "account/basket";
	}
	
	@PutMapping("/edit")
	public String editUser(@Valid @ModelAttribute("user") User user, Errors errors) {
		
		if(errors.hasErrors()) {
			return "account/profile";
		}
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		userService.update(this.user, user);
		
		return "redirect:/profile";
	}
	
	@PostMapping("/addProduct")
	public String addProductToBasket(@RequestParam("id") Long id) {
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		userService.addProduct(user, id);
		return "redirect:/product/" + id;
	}
}
