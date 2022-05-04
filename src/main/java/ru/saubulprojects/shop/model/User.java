package ru.saubulprojects.shop.model;

import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", schema = "shop",
	   uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "email_constraint")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Необходимо ввести имя.")
	@Size(min = 2, message = "Имя слишком корткое.")
	@Column(name = "first_Name")
	private String firstName;
	
	@NotBlank(message = "Необходимо ввести фамилию.")
	@Size(min = 2, message = "Фамилия слишком короткая.")
	@Column(name = "last_Name")
	private String lastName;
	
	private String email;
	
	private String password;
	
	@NotBlank(message = "Необходимо ввести номер телефона.")
	@Pattern(regexp = "^8[0-9]{10}$", message = "Введите корректный номер телефона.")
	private String phone;
	
	@ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", schema = "shop",
			   joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_id_fk"))},
			   inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "role_id_fk"))})
	private Collection<Role> roles;
	
	@OneToMany(targetEntity = Order.class, mappedBy = "user")
	private Collection<Order> orders;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
	private Basket basket;
	
	private String img;
	
	public User(String firstName,
				String lastName,
				String email,
				String password,
				String phone,
				Collection<Role> roles,
				String img) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.roles = roles;
		this.img = img;
	}
}
