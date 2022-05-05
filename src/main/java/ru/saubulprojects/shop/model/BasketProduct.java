package ru.saubulprojects.shop.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "basket_products", schema = "shop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "basket_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "basket_id_fk"))
	private Basket basket;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_id_fk"))
	private Product product;
	
	private int count;
	
	public BasketProduct(Basket basket, Product product, int count) {
		this.basket = basket;
		this.product = product;
		this.count = count;
	}
}
