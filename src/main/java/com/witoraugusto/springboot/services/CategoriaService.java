package com.witoraugusto.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witoraugusto.springboot.domain.Categoria;
import com.witoraugusto.springboot.repositories.CategoriaRepository;
import com.witoraugusto.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	// instanciar o repositorio - injeção de dependencia
	@Autowired
	private CategoriaRepository repo;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " Tipo: " + Categoria.class.getName()));
	}

}
