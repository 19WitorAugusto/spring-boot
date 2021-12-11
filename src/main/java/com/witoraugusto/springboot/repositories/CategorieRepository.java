package com.witoraugusto.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.witoraugusto.springboot.domain.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
	
	
}
