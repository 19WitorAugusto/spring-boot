package com.witoraugusto.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.witoraugusto.springboot.domain.Cidade;
import com.witoraugusto.springboot.domain.Cliente;
import com.witoraugusto.springboot.domain.Endereco;
import com.witoraugusto.springboot.domain.enums.TipoCliente;
import com.witoraugusto.springboot.dto.ClienteDTO;
import com.witoraugusto.springboot.dto.ClienteNewDto;
import com.witoraugusto.springboot.repositories.ClienteRepository;
import com.witoraugusto.springboot.repositories.EnderecoRepository;
import com.witoraugusto.springboot.services.exceptions.DataIntegratyException;
import com.witoraugusto.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepository;

	// insert
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

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
			throw new DataIntegratyException("Não é possivel excluir cliente que contem pedidos!");
		}
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}

	public Cliente fromDTO(ClienteNewDto objDto) {
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cliente, cidade);

		cliente.getEnderecos().add(endereco);

		cliente.getTelefones().add(objDto.getTelefone1());

		if (objDto.getTelefone2() != null) {
			cliente.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cliente.getTelefones().add(objDto.getTelefone3());
		}
		return cliente;
	}

	private void upadateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}