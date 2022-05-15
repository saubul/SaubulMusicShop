package ru.saubulprojects.shop.service;

import org.springframework.data.domain.Page;

import ru.saubulprojects.shop.model.Product;

public interface ProductService {
	
	Product findById(Long id);
	
	Page<Product> findAllByNameAndCategory(String searchName, String category, int pageNo);
	
}
