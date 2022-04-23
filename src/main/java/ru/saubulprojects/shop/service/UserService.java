package ru.saubulprojects.shop.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ru.saubulprojects.shop.model.User;

public interface UserService extends UserDetailsService{
	
	public User findByUsername(String username); 
	
}
