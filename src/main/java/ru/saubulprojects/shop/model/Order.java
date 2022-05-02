package ru.saubulprojects.shop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "shop")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@CreationTimestamp
	@Column(name = "date_created")
	private LocalDateTime dateCreated;
	
	@OneToMany(targetEntity = OrderProduct.class, mappedBy = "orderId")
	public Collection<OrderProduct> orderProducts;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_id_fk"))
	private User user;
	
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
}
