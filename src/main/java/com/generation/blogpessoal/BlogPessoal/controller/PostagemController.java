package com.generation.blogpessoal.BlogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.generation.blogpessoal.BlogPessoal.model.Postagem;
import com.generation.blogpessoal.BlogPessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin("*")
public class PostagemController {
	
	private @Autowired PostagemRepository repository;

	@GetMapping("/all")
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{postagem}")
	public ResponseEntity<List<Postagem>> GetByPostagens(@PathVariable String postagem) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(postagem));
	}

	@PostMapping("/new")
	public ResponseEntity<Postagem> newPostagens(@Valid @RequestBody Postagem newPostagens) {
		return ResponseEntity.status(201)
				.body(repository.save(newPostagens));
	}

	@PutMapping("/edit")
	public ResponseEntity<Postagem> editPostagens(@Valid @RequestBody Postagem editPostagens) {
		return ResponseEntity.status(200).body(repository.save(editPostagens));
	}

	@DeleteMapping("/delete/{id}")
	public void deletePostagens(@PathVariable long id) {
		repository.deleteById(id);
	}

}
