package ru.saubulprojects.shop.service.impl;

import org.springframework.stereotype.Service;

import ru.saubulprojects.shop.repository.ProductRepository;
import ru.saubulprojects.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepo;
	
	public ProductServiceImpl(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
}
