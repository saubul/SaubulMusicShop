package ru.saubulprojects.shop.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.saubulprojects.shop.dto.UserDTO;
import ru.saubulprojects.shop.model.Role;
import ru.saubulprojects.shop.model.User;
import ru.saubulprojects.shop.repository.RoleRepository;
import ru.saubulprojects.shop.repository.UserRepository;
import ru.saubulprojects.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepo;
	private final RoleRepository roleRepo;
	private final PasswordEncoder passEncoder;
	
	public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passEncoder) {
		this.userRepo = userRepo;
		this.passEncoder = passEncoder;
		this.roleRepo = roleRepo;
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepo.findByEmail(username);
	}
	
	@Override
	public User save(UserDTO userDTO) {
		return userRepo.save(new User(userDTO.getFirstName(), 
				  					  userDTO.getLastName(), 
				  					  userDTO.getEmail(), 
				  					  passEncoder.encode(userDTO.getPassword()),
				  					  userDTO.getPhone(),
				  					  Arrays.asList(roleRepo.findByName("ROLE_USER")),
				  					  userDTO.getImg()
				  					  ));
	}
	
	@Override
	public User update(User user, User user2) {
		user.setFirstName(user2.getFirstName());
		user.setLastName(user2.getLastName());
		user.setPhone(user2.getPhone());
		return userRepo.save(user);
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid email or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
