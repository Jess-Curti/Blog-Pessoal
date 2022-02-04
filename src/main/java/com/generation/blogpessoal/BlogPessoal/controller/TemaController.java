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

import com.generation.blogpessoal.BlogPessoal.model.Tema;
import com.generation.blogpessoal.BlogPessoal.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin("*")
public class TemaController {

	private @Autowired TemaRepository repository;

	@GetMapping("/all")
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{descricao}")
	public ResponseEntity<List<Tema>> getByTemas(@PathVariable String descricao) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@PostMapping("/new")
	public ResponseEntity<Tema> newTemas(@RequestBody Tema newTemas) {
		return ResponseEntity.status(201)
				.body(repository.save(newTemas));
	}

	@PutMapping("/edit")
	public ResponseEntity<Tema> editTemas(@RequestBody Tema editTemas) {
		return ResponseEntity.status(200)
				.body(repository.save(editTemas));
	}

	@DeleteMapping("/delete/{id}")
	public void deleteTemas(@PathVariable long id) {
		repository.deleteById(id);
	}
}
