package com.witoraugusto.springboot.services;

import org.springframework.mail.SimpleMailMessage;

import com.witoraugusto.springboot.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
