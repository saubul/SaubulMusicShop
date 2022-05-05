package ru.saubulprojects.shop.service.impl;

import org.springframework.stereotype.Service;

import ru.saubulprojects.shop.model.BasketProduct;
import ru.saubulprojects.shop.repository.BasketProductRepository;
import ru.saubulprojects.shop.service.BasketProductService;

@Service
public class BasketProductServiceImpl implements BasketProductService{

	private final BasketProductRepository basketProductRepo;
	
	public BasketProductServiceImpl(BasketProductRepository basketProductRepo) {
		this.basketProductRepo = basketProductRepo;
	}
	
	@Override
	public BasketProduct save(BasketProduct basketProduct) {
		
		return basketProductRepo.save(basketProduct);
	}

}
