package com.generation.blogpessoal.BlogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.BlogPessoal.model.Postagem;
import com.generation.blogpessoal.BlogPessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class PostagemController {
	
	private @Autowired PostagemRepository repository;

	@GetMapping("/all")
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping("/new")
	public ResponseEntity<Postagem> newPostagens(@Valid @RequestBody Postagem newPostagens) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(newPostagens));
	}

	@PutMapping("/edit")
	public ResponseEntity<Postagem> editPostagens(@Valid @RequestBody Postagem editPostagens) {
		return repository.findById(editPostagens.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(repository.save(editPostagens)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/delete/{id}")
	public void deletePostagens(@PathVariable long id) {
		Optional<Postagem> post = repository.findById(id);

		if(post.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		repository.deleteById(id);
	}

}
