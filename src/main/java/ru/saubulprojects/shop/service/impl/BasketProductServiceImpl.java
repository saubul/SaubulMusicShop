package ru.saubulprojects.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.saubulprojects.shop.model.Basket;
import ru.saubulprojects.shop.model.BasketProduct;
import ru.saubulprojects.shop.model.Product;
import ru.saubulprojects.shop.repository.BasketProductRepository;
import ru.saubulprojects.shop.repository.ProductRepository;
import ru.saubulprojects.shop.service.BasketProductService;

@Service
public class BasketProductServiceImpl implements BasketProductService{

	private final BasketProductRepository basketProductRepo;
	private final ProductRepository productRepo;
	
	public BasketProductServiceImpl(BasketProductRepository basketProductRepo,
									ProductRepository productRepo) {
		this.basketProductRepo = basketProductRepo;
		this.productRepo = productRepo;
	}
	
	@Override
	public BasketProduct save(BasketProduct basketProduct) {
		
		return basketProductRepo.save(basketProduct);
	}

	@Override
	public List<BasketProduct> findAllByBasketId(Basket basket) {
		return basketProductRepo.findAllByBasketId(basket);
	}

	@Override
	public void delete(BasketProduct basketProduct) {
		basketProductRepo.delete(basketProduct);
	}

	@Override
	public void deleteBasketProducts(List<BasketProduct> basketProducts) {
		
		basketProducts.stream().forEach(it -> basketProductRepo.delete(it));
	}

	@Override
	public BasketProduct findByProductAndBasket(Product product, Basket basket) {
		return basketProductRepo.findByProductAndBasket(product, basket);
	}

	@Override
	public BasketProduct findById(Long id) {
		return basketProductRepo.findById(id).get();
	}



}
