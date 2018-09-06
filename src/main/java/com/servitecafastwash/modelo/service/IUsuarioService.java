package com.servitecafastwash.modelo.service;

import java.util.List;

import com.servitecafastwash.modelo.Usuario;

public interface IUsuarioService  {

	public void save(Usuario usuario);
	
	public Usuario findById(Long id);
	
	public Usuario findByEmail(String email);
		
	public List<Usuario> findAll();
	
	public void delete (Long id); 
	
}
