package com.generation.blogpessoal.BlogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.BlogPessoal.model.Usuario;
import com.generation.blogpessoal.BlogPessoal.model.UsuarioLogin;
import com.generation.blogpessoal.BlogPessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

@Autowired
private UsuarioRepository repository;

public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
	
	Optional<Usuario> optional = repository.findByUsuario(usuario.getUsuario());
	if(optional.isPresent()) {
		Optional.empty();
	}
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	String senhaEncoder = encoder.encode(usuario.getSenha());
	usuario.setSenha(senhaEncoder);
	
	return Optional.ofNullable(repository.save(usuario));
}

public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> user) {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
	
	if(usuario.isPresent()) {
		if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
		
			String auth = user.get().getUsuario() + ":" + user.get().getSenha();
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic" + new String(encodedAuth);
			
			user.get().setToken(authHeader);
			user.get().setNome(usuario.get().getNome());
			
			return user;
		}
	}
	return null;
}
}
