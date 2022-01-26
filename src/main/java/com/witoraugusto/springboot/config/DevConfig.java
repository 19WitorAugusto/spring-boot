package com.witoraugusto.springboot.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.witoraugusto.springboot.services.DBService;
import com.witoraugusto.springboot.services.EmailService;
import com.witoraugusto.springboot.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instatiateDatabase() throws ParseException {

		if (!"create".equals(strategy)) {
			return false;
		} else {
			dbService.instatiateTestDatabase();
			return true;
		}

	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
