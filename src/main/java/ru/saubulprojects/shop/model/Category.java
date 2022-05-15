package ru.saubulprojects.shop.model;

import java.util.Collection;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category", schema = "shop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany(targetEntity = Product.class, mappedBy = "categories")
	private Collection<Product> products;
}
