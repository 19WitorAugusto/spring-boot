package com.witoraugusto.springboot.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.witoraugusto.springboot.domain.Cliente;
import com.witoraugusto.springboot.dto.ClienteDTO;
import com.witoraugusto.springboot.repositories.ClienteRepository;
import com.witoraugusto.springboot.services.exceptions.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClienteDTO> {

	// obter o parametro da uri
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

		// obter dados uri
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		// obtive o id da uri
		Integer uriId = Integer.parseInt(map.get("id"));
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		//tentar atualizar cliente com email que ja tinha em outro cliente - não se repete
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("Email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFildName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}