package ru.saubulprojects.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.saubulprojects.shop.model.Basket;
import ru.saubulprojects.shop.model.BasketProduct;
import ru.saubulprojects.shop.model.Product;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, Long>{

	
	@Query("SELECT b FROM BasketProduct b WHERE b.basket=:id")
	List<BasketProduct> findAllByBasketId(@Param("id") Basket basket);

	BasketProduct findByProductAndBasket(Product product, Basket basket);

}
