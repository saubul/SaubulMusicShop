package ru.saubulprojects.shop.dto;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class UserRegistrationDTO {
	
	@NotBlank(message = "First name is required.")
	@Size(min = 2, message = "First name is too small.")
	@Pattern(regexp = "[a-zA-Z]{1,}", message = "Use latin letters.")
	private String firstName;
	
	@NotBlank(message = "Last name is required.")
	@Size(min = 2, message = "Last name is too small.")
	@Pattern(regexp = "[a-zA-Z]{1,}", message = "Use latin letters.")
	private String lastName;
	
	@Email(message = "Please provide a valid email address.")
	@Pattern(regexp = ".+@.+\\..+")
	private String email;
	
	@NotBlank(message = "Password is required.")
	private String password;
	
}
