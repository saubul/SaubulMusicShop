package ru.saubulprojects.shop.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.saubulprojects.shop.model.Basket;
import ru.saubulprojects.shop.model.BasketProduct;
import ru.saubulprojects.shop.model.Product;
import ru.saubulprojects.shop.model.User;
import ru.saubulprojects.shop.service.BasketProductService;
import ru.saubulprojects.shop.service.BasketService;
import ru.saubulprojects.shop.service.ProductService;
import ru.saubulprojects.shop.service.UserService;

@Controller
@RequestMapping("/profile")
public class BasketController {
	
	private User user;
	private final UserService userService;
	private final BasketProductService basketProductService;
	private final ProductService productService;
	private final BasketService basketService;
	
	
	public BasketController(UserService userService, 
							 BasketProductService basketProductService,
							 ProductService productService,
							 BasketService basketService) {
		this.userService = userService;
		this.basketProductService = basketProductService;
		this.productService = productService;
		this.basketService = basketService;
	}
	
	@PostMapping("basket/addProduct")
	public String addProductToBasket(@RequestParam("id") Long id) {
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		Product product = productService.findById(id);
		userService.addProduct(user, id);
		Basket basket = user.getBasket();
		basket.setPrice(user.getBasket().getPrice().add(product.getPrice()));
		basketService.save(basket);
		return "redirect:/product/" + id;
	}
	
	@GetMapping("/basket")
	public String basketForm(Model model) {
		user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Basket basket = user.getBasket();
		List<BasketProduct> basketProducts = basketProductService.findAllByBasketId(basket);
		model.addAttribute("user", user);
		model.addAttribute("basketProducts", basketProducts);
		model.addAttribute("price", basket.getPrice());
		return "account/basket";
	}
	
	@GetMapping("/basket/delete_product/{id}")
	public String deleteProductFromBasket(@PathVariable("id") Long id) {
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		BasketProduct basketProduct = basketProductService.findById(id);
		basketProductService.delete(basketProduct);
		Basket basket = user.getBasket();
		basket.setPrice(basket.getPrice().subtract(basketProduct.getProduct().getPrice().multiply(BigDecimal.valueOf(basketProduct.getCount()))));
		basketService.save(basket);
		return "redirect:/profile/basket";
	}
}
