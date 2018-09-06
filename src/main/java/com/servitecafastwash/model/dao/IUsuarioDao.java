package com.servitecafastwash.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.servitecafastwash.modelo.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	public Usuario findByEmailIgnoreCase(String email);
	
}
