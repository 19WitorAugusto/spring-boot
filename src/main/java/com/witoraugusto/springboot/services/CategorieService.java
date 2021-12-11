package com.witoraugusto.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witoraugusto.springboot.domain.Categorie;
import com.witoraugusto.springboot.repositories.CategorieRepository;

@Service
public class CategorieService {

	// instanciar o repositorio - injeção de dependencia
	@Autowired
	private CategorieRepository repo;

	public Categorie findById(Integer id) {
		Optional<Categorie> obj = repo.findById(id);
		return obj.orElse(null);
	}

}
