package ru.saubulprojects.shop.model;

import java.util.Collection;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", 
	   uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "email_constraint")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_Name")
	private String firstName;
	
	@Column(name = "last_Name")
	private String lastName;
	
	private String email;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
			   joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_id_fk"))},
			   inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "role_id_fk"))})
	private Collection<Role> roles;
}
