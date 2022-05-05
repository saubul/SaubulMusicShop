package ru.saubulprojects.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.saubulprojects.shop.model.Product;
import ru.saubulprojects.shop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/{id}")
	public String getProductForm(@PathVariable("id") Long id, Model model) {
		
		Product product = productService.findProductById(id);
		model.addAttribute("product", product);
		return "shop/product";
	}
}
