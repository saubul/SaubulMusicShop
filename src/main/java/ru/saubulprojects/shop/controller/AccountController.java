package ru.saubulprojects.shop.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.saubulprojects.shop.model.BasketProduct;
import ru.saubulprojects.shop.model.Order;
import ru.saubulprojects.shop.model.OrderProduct;
import ru.saubulprojects.shop.model.OrderStatus;
import ru.saubulprojects.shop.model.User;
import ru.saubulprojects.shop.service.BasketProductService;
import ru.saubulprojects.shop.service.OrderProductService;
import ru.saubulprojects.shop.service.OrderService;
import ru.saubulprojects.shop.service.UserService;

@Controller
@RequestMapping("/profile")
public class AccountController {
	
	private User user;
	private final UserService userService;
	private final BasketProductService basketProductService;
	private final OrderService orderService;
	private final OrderProductService orderProductService;
	
	
	public AccountController(UserService userService, 
							 BasketProductService basketProductService,
							 OrderService orderService,
							 OrderProductService orderProductService) {
		this.userService = userService;
		this.basketProductService = basketProductService;
		this.orderService = orderService;
		this.orderProductService = orderProductService;
	}
	
	@GetMapping
	public String profileForm(Model model) {
		
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		model.addAttribute("user", user);
		return "account/profile";
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
	
	@GetMapping("/orders/new_order")
	public String getNewOrderForm(Model model) {
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		model.addAttribute("user", user);
		model.addAttribute("basketProducts", basketProductService.findAllByBasketId(user.getBasket()));
		return "account/new_order";
	}
	
	@PostMapping("/orders/new_order")
	public String newOrderForm(Model model) {
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		List<BasketProduct> basketProducts = basketProductService.findAllByBasketId(user.getBasket());
		Order order = new Order();
		order.setPrice(BigDecimal.ZERO);
		order.setStatus(OrderStatus.NEW);
		order.setUser(user);
		List<OrderProduct> orderProducts = basketProducts.stream()
				 			.map(it -> {
								 OrderProduct op = new OrderProduct(order,
															 		it.getProduct(), 
															 		it.getCount(), 
															 		it.getProduct().getPrice().multiply(BigDecimal.valueOf(it.getCount())));
								 order.getPrice().add(op.getPrice());
								 return op;
							 })
						     .collect(Collectors.toList());
		order.setOrderProducts(orderProducts);
		orderService.save(order);
		basketProductService.deleteBasketProducts(basketProducts);
		model.addAttribute("user", user);
		return "account/new_order";
	}
	
	@GetMapping("/orders")
	public String getOrdersForm(Model model) {
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		model.addAttribute("user", user);
		
		model.addAttribute("orderProducts", user.getOrders());
		
		return "account/orders";
	}
}
