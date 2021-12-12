package com.witoraugusto.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.witoraugusto.springboot.domain.Cidade;

@Repository
public interface CidadeRepositoy extends JpaRepository<Cidade, Integer> {

}
