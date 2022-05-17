package ru.saubulprojects.shop.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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
import ru.saubulprojects.shop.model.Order;
import ru.saubulprojects.shop.model.OrderProduct;
import ru.saubulprojects.shop.model.OrderStatus;
import ru.saubulprojects.shop.model.User;
import ru.saubulprojects.shop.service.BasketProductService;
import ru.saubulprojects.shop.service.BasketService;
import ru.saubulprojects.shop.service.OrderService;
import ru.saubulprojects.shop.service.UserService;

@Controller
@RequestMapping("/profile")
public class OrderController {

	private User user;
	private final UserService userService;
	private final BasketProductService basketProductService;
	private final OrderService orderService;
	private final BasketService basketService;
	
	
	public OrderController(UserService userService, 
						   BasketProductService basketProductService,
						   OrderService orderService,
						   BasketService basketService) {
		this.userService = userService;
		this.basketProductService = basketProductService;
		this.orderService = orderService;
		this.basketService = basketService;
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
		
		Basket basket = user.getBasket();
		basket.setPrice(BigDecimal.ZERO);
		basketService.save(basket);
		model.addAttribute("user", user);
		return "redirect:/profile/orders";
	}
	
	@GetMapping("/orders")
	public String getOrdersForm(@RequestParam(value = "date", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model) {
		getOrdersPageForm(1, date, model);
		
		return "account/user_orders";
	}
	
	@GetMapping("/orders/page/{pageNo}")
	public String getOrdersPageForm(@PathVariable("pageNo") int pageNo, 
									@RequestParam(value = "date", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, 
									Model model) {
		user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		Page<Order> pageOrder =  Page.empty();
		
		System.out.println(date);
		if(date!=null) {
			pageOrder = orderService.findAllByUserAndDate(user, date, pageNo);
			model.addAttribute("date", date);
		} else {
			pageOrder = orderService.findAllByUser(user, pageNo);
			model.addAttribute("date","");
		}
		model.addAttribute("user", user);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageOrder.getTotalPages());
		model.addAttribute("orders", pageOrder.getContent());
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
