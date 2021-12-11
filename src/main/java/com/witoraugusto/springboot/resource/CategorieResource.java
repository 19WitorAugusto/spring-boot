package com.witoraugusto.springboot.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.witoraugusto.springboot.domain.Categorie;
import com.witoraugusto.springboot.services.CategorieService;

@RestController
@RequestMapping(value = "/categoria")
public class CategorieResource {

	@Autowired
	private CategorieService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		Categorie obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}

}
