package com.witoraugusto.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.witoraugusto.springboot.domain.Categoria;
import com.witoraugusto.springboot.dto.CategoriaDTO;
import com.witoraugusto.springboot.repositories.CategoriaRepository;
import com.witoraugusto.springboot.services.exceptions.DataIntegratyException;
import com.witoraugusto.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	// instanciar o repositorio - injeção de dependencia
	@Autowired
	private CategoriaRepository repo;

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " Tipo: " + Categoria.class.getName()));
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// nulo - save - insere
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	// não nulo - save - atualiza
	public Categoria update(Categoria obj) {
		Categoria newObj = findById(obj.getId());// busca, caso não exista chama exceção
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyException("Não é possivel excluir uma categoria que possui produtos");
		}
	}

	public Categoria fromDto(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

}
