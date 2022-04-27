package ru.saubulprojects.shop.model;

import java.util.Collection;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", schema = "shop")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	private String name;
	
	private String description;
	
	@OneToMany(targetEntity = OrderProduct.class, mappedBy = "productId")
    private Collection<OrderProduct> orderProducts;
}
