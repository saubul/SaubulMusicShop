package ru.saubulprojects.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ru.saubulprojects.shop.model.Product;

public interface ProductService {
	
	Product findProductById(Long id);
	
	Page<Product> findProductsByName(int pageNo, String name);
	
}
