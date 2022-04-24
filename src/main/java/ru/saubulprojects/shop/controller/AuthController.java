package ru.saubulprojects.shop.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.saubulprojects.shop.dto.UserRegistrationDTO;
import ru.saubulprojects.shop.service.UserService;

@Controller
public class AuthController {

	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDTO userRegistrationDTO() {
		return new UserRegistrationDTO();
	}
	
	@ModelAttribute("userExists")
	public boolean exist() {
		return false;
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "auth/login";
	}
	
	@GetMapping("/registration")
	public String registrationForm() {
		return "auth/registration";
	}
	
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user") UserRegistrationDTO userDTO, Errors errors, Model model) {
		if(errors.hasErrors()) {
			return "auth/registration";
		}
		
		if(userService.findByUsername(userDTO.getEmail()) != null) {
			model.addAttribute("userExists", true);
			return "auth/registration";
		}
		
		userService.save(userDTO);
		
		return "redirect:/";
	}
	
}
