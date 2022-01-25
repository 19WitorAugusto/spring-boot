package com.witoraugusto.springboot.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.witoraugusto.springboot.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {

		SimpleMailMessage smm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(smm);
	}

	// pode ser acessado por sub
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(obj.getCliente().getEmail());// destino
		smm.setFrom(sender);// remetente
		smm.setSubject("Pedido confirmado: Código: " + obj.getId());// assunto do email
		smm.setSentDate(new Date(System.currentTimeMillis()));// data do pedido, pegando do servidor
		smm.setText(obj.toString());// corpo do pedido
		return smm;
	}
}
