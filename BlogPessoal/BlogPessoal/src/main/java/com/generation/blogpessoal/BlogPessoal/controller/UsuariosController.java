package com.generation.blogpessoal.BlogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.BlogPessoal.model.Usuarios;
import com.generation.blogpessoal.BlogPessoal.repository.UsuariosRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuariosController {

	@Autowired
	private UsuariosRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuarios>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuarios> getById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{pathBatata}/{pathBanana}") 
	public ResponseEntity<List<Usuarios>> getByUsuarios (
			@PathVariable(value = "pathBatata") String batata,
			@PathVariable(value = "pathBanana") String banana)  {
		return ResponseEntity.ok(repository.findAllByNomeAndEmailContainingIgnoreCase(batata, banana));
	}     

	@PostMapping("/new")
	public ResponseEntity<Usuarios> newUsuario(@RequestBody Usuarios newUsuario) {
		return ResponseEntity.status(201).body(repository.save(newUsuario));
	}

	@PutMapping("/edit")
	public ResponseEntity<Usuarios> editUsuario(@RequestBody Usuarios editUsuario) {
		return ResponseEntity.status(200).body(repository.save(editUsuario));
	}

	@DeleteMapping ("/delete/{id}")
	public void deleteUsuarios (@PathVariable long id) {
		repository.deleteById(id);
	}
}
