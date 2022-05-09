package ru.saubulprojects.shop.service;


import java.util.List;

import ru.saubulprojects.shop.model.Basket;
import ru.saubulprojects.shop.model.BasketProduct;
import ru.saubulprojects.shop.model.Product;

public interface BasketProductService {
	
	BasketProduct save(BasketProduct basketProduct);

	List<BasketProduct> findAllByBasketId(Basket basket);

	void deleteBasketProduct(Long id);

	void deleteBasketProducts(List<BasketProduct> basketProducts);
	
}
