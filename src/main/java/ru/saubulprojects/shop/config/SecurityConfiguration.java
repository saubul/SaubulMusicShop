package ru.saubulprojects.shop.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder passEncoder;
	
	public SecurityConfiguration(PasswordEncoder passEncoder) {
		this.passEncoder = passEncoder;
	}
	
	
}
