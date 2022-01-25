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
	public ResponseEntity<List<Usuarios>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuarios> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{usuario}") 
	public ResponseEntity<List<Usuarios>> GetByUsuarios (@PathVariable String usuario)  {
		return ResponseEntity.ok(repository.findAllByUsuariosContainingIgnoreCase(usuario));
	}     

	@PostMapping("/new")
	public ResponseEntity <Usuarios> newUsuarios (@RequestBody Usuarios newUsuarios) {
		return ResponseEntity.status(201).body(repository.save(newUsuarios));
	}

	@PutMapping("/edit")
	public ResponseEntity <Usuarios> editUsuarios (@RequestBody Usuarios editUsuarios) {
		return ResponseEntity.status (200).body(repository.save(editUsuarios));
	}

	@DeleteMapping ("/delete/{id}")
	public void deleteUsuarios (@PathVariable long id) {
		repository.deleteById(id);
	}
}
