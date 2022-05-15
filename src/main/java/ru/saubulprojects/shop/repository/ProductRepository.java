package ru.saubulprojects.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.saubulprojects.shop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	
	@Query("SELECT p FROM Product p JOIN p.categories c " +
		   "WHERE UPPER(p.name) LIKE UPPER(CONCAT('%', :name, '%')) AND " +
				 "UPPER(c.name) LIKE UPPER(CONCAT('%', :category, '%'))")
	Page<Product> findProductsByNameAndCategory(String name, String category, Pageable pageable);

	
}
