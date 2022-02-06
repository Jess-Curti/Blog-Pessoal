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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Essa anotação indica que a Classe UsuarioRepositoryTest é uma Classe Spring Boot Testing.
//A Opção environment indica que caso a porta principal (8080 para uso local) esteja ocupada, o Spring irá atribuir uma outra porta automaticamente.

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Essa anotação indica que o Ciclo de vida da Classe de Teste será por Classe
public class UsuarioRepositoryTest {

	@Autowired //Injeção de dependência para aparecer os objetos no Banco de Dados de testes
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll //Método start com essa anotação inicializa os objetos do tipo Usuario e insere no banco de dados de testes por meio do método save uma unica vez
	void start() {

		usuarioRepository.save(new Usuario(0L, "Jessica M Curti", "jessica@email.com.br", "123456", ""));

		usuarioRepository.save(new Usuario(0L, "Rodolfo Santos Silva", "rodolfo@email.com.br", "123456", ""));

		usuarioRepository.save(new Usuario(0L, "Miguel S C Santos", "miguel@email.com.br", "123456", ""));

		usuarioRepository.save(new Usuario(0L, "Bernardo S C Santos", "bernardo@email.com.br", "123456", ""));
	}

	@Test //este método executará um teste
	@DisplayName("Retorna 1 usuario") //configura uma mensagem que será exibida ao invés do nome do método
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("jessica@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("jessica@email.com.br")); //verifica se o usuário (email) foi encontrado.
	}

	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Santos");
		assertEquals(3, listaDeUsuarios.size()); //assertEquals verifica se o tamanho da List é igual a 3 e o size retorna o tamanho da List.
		
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Rodolfo Santos Silva")); //1ª posição da List
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Miguel S C Santos")); //2ª posição da List
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Bernardo S C Santos")); //3ª posição da List
		//AssertTrue verifica em cada posição da Collection List listaDeUsuarios se os usuários, que foram inseridos no Banco de dados através no método start(), foram gravados na mesma sequência.

	}

}
