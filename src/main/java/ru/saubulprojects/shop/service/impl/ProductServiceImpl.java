package ru.saubulprojects.shop.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ru.saubulprojects.shop.model.Product;
import ru.saubulprojects.shop.repository.ProductRepository;
import ru.saubulprojects.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepo;
	
	public ProductServiceImpl(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	@Override
	public Page<Product> findProductsByName(int pageNo, String name) {

		
		return productRepo.findProductsByName(name, PageRequest.of(pageNo - 1, 3));
	}
	
	@Override
	public Product findProductById(Long id) {
		Product product = productRepo.findById(id).get();
		
		if(product == null) {
			throw new RuntimeException("No such product found.");
		}
		
		return product;
	}
	

}
