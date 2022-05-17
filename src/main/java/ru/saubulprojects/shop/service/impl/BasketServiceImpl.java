package ru.saubulprojects.shop.service.impl;

import org.springframework.stereotype.Service;

import ru.saubulprojects.shop.model.Basket;
import ru.saubulprojects.shop.model.User;
import ru.saubulprojects.shop.repository.BasketRepository;
import ru.saubulprojects.shop.service.BasketService;

@Service
public class BasketServiceImpl implements BasketService{
		
	private final BasketRepository basketRepo;
	
	public BasketServiceImpl(BasketRepository basketRepo) {
		this.basketRepo = basketRepo;
	}

	@Override
	public Basket save(Basket basket) {
		return basketRepo.save(basket);
	}

	@Override
	public Basket findByUser(User user) {
		return basketRepo.findByUser(user);
	}
	
}
