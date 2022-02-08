package com.generation.blogpessoal.BlogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.BlogPessoal.model.Tema;

/**
 * Repositório de métodos CRUD da model Tema
 * 
 * @author Jessica Curti
 * @date 08/02/2022
 * @version 1.0
 *
 */

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {

	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);

}
