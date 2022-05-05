package ru.saubulprojects.shop.service.impl;

import org.springframework.stereotype.Service;

import ru.saubulprojects.shop.model.Basket;
import ru.saubulprojects.shop.model.BasketProduct;
import ru.saubulprojects.shop.repository.BasketRepository;
import ru.saubulprojects.shop.service.BasketService;
import ru.saubulprojects.shop.service.ProductService;

@Service
public class BasketServiceImpl implements BasketService{
		
	private final ProductService productService;
	private final BasketRepository basketRepo;
	
	public BasketServiceImpl(ProductService productService,
							 BasketRepository basketRepo) {
		this.productService = productService;
		this.basketRepo = basketRepo;
	}

	@Override
	public Basket save(Basket basket) {
		return basketRepo.save(basket);
	}

	@Override
	public Basket findBasketByUserId(Long id) {
		return basketRepo.findBasketByUserId(id);
	}
	
}
