package com.witoraugusto.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.witoraugusto.springboot.domain.Cliente;
import com.witoraugusto.springboot.dto.ClienteDTO;
import com.witoraugusto.springboot.repositories.ClienteRepository;
import com.witoraugusto.springboot.services.exceptions.DataIntegratyException;
import com.witoraugusto.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	// FindById
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " Tipo: " + Cliente.class.getName()));
	}

	// GET
	public List<Cliente> findAll() {
		return repo.findAll();
	}

	// PAGE
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// POST
	// Ainda implementar

	// PUT
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		upadateData(newObj, obj);
		return repo.save(newObj);
	}

	// DELETE
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyException("Não é possivel excluir cliente que contem outras associações!");
		}
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	private void upadateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}