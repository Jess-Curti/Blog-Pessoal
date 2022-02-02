package com.generation.blogpessoal.BlogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.BlogPessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.save(new Usuario(0L, "Jessica M Curti", "jessica@email.com.br", "123456", ""));
		
		usuarioRepository.save(new Usuario(0L, "Rodolfo Santos Silva", "rodolfo@email.com.br", "123456", ""));
		
		usuarioRepository.save(new Usuario(0L, "Miguel S C Santos", "miguel@email.com.br", "123456", ""));
		
		usuarioRepository.save(new Usuario(0L, "Bernardo S C Santos", "bernardo@email.com.br", "123456", ""));
	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("jessica@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("jessica@email.com.br"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Santos");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Rodolfo Santos Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Miguel S C Santos"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Bernardo S C Santos"));
	}

}
