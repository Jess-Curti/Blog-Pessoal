package com.generation.blogpessoal.BlogPessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.BlogPessoal.model.Usuario;
import com.generation.blogpessoal.BlogPessoal.repository.UsuarioRepository;

/**
 * Implementação do UserDetailsService do Spring Security
 * 
 * @author Jessica Curti
 * @date 08/02/2022
 * @version 1.0
 * @see UserDetailsService
 * @see UserRepository
 * @see UserDetailsImplement
 * 
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private @Autowired UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {

		Optional<Usuario> user = usuarioRepository.findByUsuario(usuario);
		user.orElseThrow(() -> new UsernameNotFoundException(usuario + " not found."));

		return user.map(UserDetailsImpl::new).get();
	}

}
