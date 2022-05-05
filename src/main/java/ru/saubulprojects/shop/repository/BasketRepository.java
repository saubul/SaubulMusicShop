package ru.saubulprojects.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.saubulprojects.shop.model.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long>{
	
	Basket findBasketByUserId(Long id);
	
}
