package ru.saubulprojects.shop.dto;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class UserRegistrationDTO {
	
	@NotBlank(message = "Необходимо ввести имя.")
	@Size(min = 2, message = "Имя слишком корткое.")
	//@Pattern(regexp = "[а-ЯА-Я]{1,}", message = "Можно использовать только кириллицу.")
	private String firstName;
	
	@NotBlank(message = "Необходимо ввести фамилию.")
	@Size(min = 2, message = "Фамилия слишком короткая.")
	//@Pattern(regexp = "[а-ЯА-Я]{1,}", message = "Можно использовать только кириллицу.")
	private String lastName;
	
	@Email(message = "Введите электронную почту в правильном формате.")
	@Pattern(regexp = ".+@.+\\..+", message = "Введите электронную почту в правильном формате.")
	private String email;
	
	@NotBlank(message = "Необходимо ввести пароль.")
	private String password;
	
	@NotBlank(message = "Необходимо ввести номер телефона.")
	@Pattern(regexp = "^8[0-9]{10}$", message = "Введите корректный номер телефона.")
	private String phone;
}
