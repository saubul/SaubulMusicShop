package ru.saubulprojects.shop.service.impl;

import org.springframework.stereotype.Service;

import ru.saubulprojects.shop.repository.OrderRepository;
import ru.saubulprojects.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepo;
	
	public OrderServiceImpl(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
}