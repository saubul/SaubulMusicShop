package ru.saubulprojects.shop.model;

import java.util.Collection;

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
	

	@OneToMany(targetEntity = BasketProduct.class, mappedBy = "basketId")
	private Collection<BasketProduct> basketProducts;
	
}
