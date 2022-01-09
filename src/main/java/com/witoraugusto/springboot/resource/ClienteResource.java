package com.witoraugusto.springboot.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.witoraugusto.springboot.domain.Cliente;
import com.witoraugusto.springboot.dto.ClienteDTO;
import com.witoraugusto.springboot.dto.ClienteNewDto;
import com.witoraugusto.springboot.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDto objDto) {
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// findAll
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	// findById
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {

		Cliente obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}

	// page
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "OrderBy", defaultValue = "nome") String OrderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String Direction) {
		Page<Cliente> listPage = service.findPage(page, linesPerPage, OrderBy, Direction);
		Page<ClienteDTO> listPageDto = listPage.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listPageDto);

	}

	// Update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	// Delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
