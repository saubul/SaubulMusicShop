package ru.saubulprojects.shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.saubulprojects.shop.model.Product;
import ru.saubulprojects.shop.service.ProductService;

@Controller
public class ShopController {
	
	private final ProductService productService;
	
	public ShopController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/")
	public String homeForm(Model model) {
		
		return homePageForm(1, null, model);
	}
	
	@GetMapping("/page/{pageNo}")
	public String homePageForm(@PathVariable("pageNo") int pageNo, 
							   @RequestParam("searchName") String searchName,
							   Model model) {
		Page<Product> page = Page.empty();
		if(searchName == null) {
			page = productService.findProductsByName(pageNo, "");
		} else {
			page = productService.findProductsByName(pageNo, searchName);
		}
		
		model.addAttribute("products", page.getContent());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		return "shop/home";
		
	}
}
