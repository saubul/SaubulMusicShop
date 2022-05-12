package ru.saubulprojects.shop.service;

import ru.saubulprojects.shop.model.Basket;
import ru.saubulprojects.shop.model.User;

public interface BasketService {
	
	Basket save(Basket basket);

	Basket findByUser(User user);
	
}
