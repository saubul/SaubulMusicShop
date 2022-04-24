package ru.saubulprojects.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.saubulprojects.shop.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	public Role findByName(String name);
	
}
