package ru.saubulprojects.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.saubulprojects.shop.model.User;
import ru.saubulprojects.shop.service.BasketProductService;
import ru.saubulprojects.shop.service.UserService;

@Controller
@RequestMapping("/profile")
public class BasketController {
	
	private User user;
	private final UserService userService;
	private final BasketProductService basketProductService;
	
	
	public BasketController(UserService userService, 
							 BasketProductService basketProductService) {
		this.userService = userService;
		this.basketProductService = basketProductService;
	}
	
	@PostMapping("basket/addProduct")
	public String addProductToBasket(@RequestParam("id") Long id) {
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		userService.addProduct(user, id);
		return "redirect:/product/" + id;
	}
	
	@GetMapping("/basket")
	public String basketForm(Model model) {
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		model.addAttribute("user", user);
		model.addAttribute("basketProducts", basketProductService.findAllByBasketId(user.getBasket()));
		return "account/basket";
	}
	
	@GetMapping("/basket/delete_product/{id}")
	public String deleteProductFromBasket(@PathVariable("id") Long id) {
		basketProductService.deleteBasketProduct(id);
		return "redirect:/profile/basket";
	}
}
