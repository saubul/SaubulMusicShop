package ru.saubulprojects.shop.service;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;

import ru.saubulprojects.shop.dto.UserDTO;
import ru.saubulprojects.shop.model.User;

public interface UserService extends UserDetailsService{
	
	User findByUsername(String username); 
	
	User save(UserDTO userDTO);
	
	User update(User user, User user2);

	void addProduct(User user, Long id);
	
}
