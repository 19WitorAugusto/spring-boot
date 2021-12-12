package com.witoraugusto.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.witoraugusto.springboot.domain.Estado;

@Repository
public interface EstadoRepositoy extends JpaRepository<Estado, Integer>{

}
