package com.generation.blogpessoal.BlogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.BlogPessoal.model.Usuario;

/**
 * Repositório de métodos CRUD da model Usuario
 * 
 * @author Jessica Curti
 * @date 08/02/2022
 * @version 1.0
 *
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public List<Usuario> findAllByNomeAndUsuarioContainingIgnoreCase(String nome, String usuario);

	public Optional<Usuario> findByUsuario(String usuario);

	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);

}
