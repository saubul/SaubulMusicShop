package ru.saubulprojects.shop.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.saubulprojects.shop.model.Order;
import ru.saubulprojects.shop.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	Page<Order> findAllByUser(User user, Pageable pageable);

	Page<Order> findAllByUserAndDateCreated(User user, LocalDate date, Pageable pageable);
	
}
