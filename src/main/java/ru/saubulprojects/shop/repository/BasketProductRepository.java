package ru.saubulprojects.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.saubulprojects.shop.model.BasketProduct;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, Long>{
	
	
	
}
