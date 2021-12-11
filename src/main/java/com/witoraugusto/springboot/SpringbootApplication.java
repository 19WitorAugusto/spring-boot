package com.witoraugusto.springboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.witoraugusto.springboot.domain.Categorie;
import com.witoraugusto.springboot.repositories.CategorieRepository;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {

	@Autowired
	private CategorieRepository categorieRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categorie cat1 = new Categorie(null, "Informática");
		Categorie cat2 = new Categorie(null, "Escritório");

		categorieRepository.saveAll(Arrays.asList(cat1, cat2));

	}

}
