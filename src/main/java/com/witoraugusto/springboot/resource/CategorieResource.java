package com.witoraugusto.springboot.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.witoraugusto.springboot.domain.Categorie;

@RestController
@RequestMapping(value = "/categories")
public class CategorieResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categorie> find() {
		
		Categorie c1 = new Categorie(1,"Informatica");
		Categorie c2 = new Categorie(2, "Escritorio");
		
		List<Categorie> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		
		return list;
	}

}
