package ru.saubulprojects.shop.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.saubulprojects.shop.model.BasketProduct;
import ru.saubulprojects.shop.model.Order;
import ru.saubulprojects.shop.model.OrderProduct;
import ru.saubulprojects.shop.model.OrderStatus;
import ru.saubulprojects.shop.model.User;
import ru.saubulprojects.shop.service.BasketProductService;
import ru.saubulprojects.shop.service.OrderService;
import ru.saubulprojects.shop.service.UserService;

@Controller
@RequestMapping("/profile")
public class OrderController {

	private User user;
	private final UserService userService;
	private final BasketProductService basketProductService;
	private final OrderService orderService;
	
	
	public OrderController(UserService userService, 
						   BasketProductService basketProductService,
						   OrderService orderService) {
		this.userService = userService;
		this.basketProductService = basketProductService;
		this.orderService = orderService;
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
								 order.setPrice(order.getPrice().add(op.getPrice()));
								 return op;
							 })
						     .collect(Collectors.toList());
		order.setOrderProducts(orderProducts);
		orderService.save(order);
		basketProductService.deleteBasketProducts(basketProducts);
		model.addAttribute("user", user);
		return "redirect:/profile/orders";
	}
	
	@GetMapping("/orders")
	public String getOrdersForm(Model model) {
		user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("user", user);
		
		model.addAttribute("orders", user.getOrders());
		
		return "account/user_orders";
	}
	
	@GetMapping("/orders/{id}")
	public String getOrderForm(@PathVariable("id") Long id, Model model) {
		if(user == null) {
			user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		model.addAttribute("user", user);
		model.addAttribute("order", orderService.findById(id));
		return "account/order";
	}
	
}
