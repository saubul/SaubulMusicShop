package ru.saubulprojects.shop.service.impl;

import org.springframework.stereotype.Service;

import ru.saubulprojects.shop.model.OrderProduct;
import ru.saubulprojects.shop.repository.OrderProductRepository;
import ru.saubulprojects.shop.service.OrderProductService;

@Service
public class OrderProductServiceImpl implements OrderProductService{
	
	private final OrderProductRepository orderProductRepo;
	
	public OrderProductServiceImpl(OrderProductRepository orderProductRepo) {
		this.orderProductRepo = orderProductRepo;
	}

	@Override
	public OrderProduct save(OrderProduct orderProduct) {
		return orderProductRepo.save(orderProduct);
	}

}
