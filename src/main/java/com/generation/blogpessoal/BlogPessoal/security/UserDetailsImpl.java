package com.generation.blogpessoal.BlogPessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.blogpessoal.BlogPessoal.model.Usuario;

/**
 * Implementação do UserDetails do Spring Security
 * 
 * @author Jessica Curti
 * @date 08/02/20221.00.1.1-SNAPSHOT
 * @see UserDetails
 * @see UserDetailsImplements
 * @see UserDetailsServiceImplement
 * 
 */

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String usuario;
	private String senha;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(Usuario usuario) {
		this.usuario = usuario.getUsuario();
		this.senha = usuario.getSenha();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
