package ru.saubulprojects.shop.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "basket", schema = "shop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_id_fk"))
	private User user;
	
	private BigDecimal price;

	@OneToMany(targetEntity = BasketProduct.class, mappedBy = "basket")
	private List<BasketProduct> basketProducts;
	
	public Basket(User user) {
		this.user = user;
		this.price = BigDecimal.ZERO;
	}
	
}
