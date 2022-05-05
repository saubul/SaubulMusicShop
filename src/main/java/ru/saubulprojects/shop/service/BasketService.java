package ru.saubulprojects.shop.service;

import ru.saubulprojects.shop.model.Basket;

public interface BasketService {
	
	Basket save(Basket basket);

	Basket findBasketByUserId(Long id);
	
}
