package ru.saubulprojects.shop.service;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;

import ru.saubulprojects.shop.dto.UserDTO;
import ru.saubulprojects.shop.model.User;

public interface UserService extends UserDetailsService{
	
	public User findByUsername(String username); 
	
	public User save(UserDTO userDTO);
	
	public User update(User user, User user2);
	
}
