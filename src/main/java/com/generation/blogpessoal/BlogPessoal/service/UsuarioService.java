package com.generation.blogpessoal.BlogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.BlogPessoal.model.Usuario;
import com.generation.blogpessoal.BlogPessoal.model.UsuarioLogin;
import com.generation.blogpessoal.BlogPessoal.repository.UsuarioRepository;

/**
 * Implementação do UsuarioService com os métodos relacionados com a model Usuario
 * 
 * @author Jessica Curti
 * @date 08/02/2022
 * @version 1.0
 * 
 */

@Service
public class UsuarioService {

	private @Autowired UsuarioRepository repository;

	//Cadastrar Usuário
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (repository.findByUsuario(usuario.getUsuario()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(repository.save(usuario));
	}
	
	//Atualizar Usuário
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		if (repository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscaUsuario = repository.findByUsuario(usuario.getUsuario());
			if (buscaUsuario.isPresent()) {
				if (buscaUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			}
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			return Optional.of(repository.save(usuario));
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
	}
	
	//Logar Usuário
	public Optional<UsuarioLogin> logarUsuario(Optional<UsuarioLogin> usuarioLogin) {
		Optional<Usuario> usuario = repository.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setToken(generatorBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());

				return usuarioLogin;
			}
		}
		throw new ResponseStatusException(
			HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
	}
	
	//Criptografar Senha
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);
		return senhaEncoder;
	}
	
	//Comparar Senhas
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);
	}

	//Gerador de token
	private String generatorBasicToken(String usuario, String password) {
		String structure = usuario + ":" + password;
		byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(structureBase64);
	}
}
