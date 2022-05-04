package ru.saubulprojects.shop.model;

import java.math.BigDecimal;
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
	
	private BigDecimal price;
	
	@OneToMany(targetEntity = OrderProduct.class, mappedBy = "productId")
    private Collection<OrderProduct> orderProducts;
	
	@OneToMany(targetEntity = BasketProduct.class, mappedBy = "productId")
	private Collection<BasketProduct> basketProducts;
	
	@ManyToMany
	@JoinTable(name = "categories_products", schema = "shop",
			   joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_id_fk"))},
			   inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "category_id_fk"))}
	)
	private Collection<Category> categories;
}
