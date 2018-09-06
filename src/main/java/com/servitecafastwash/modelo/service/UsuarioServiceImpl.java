package com.servitecafastwash.modelo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servitecafastwash.model.dao.IUsuarioDao;
import com.servitecafastwash.modelo.Perfil;
import com.servitecafastwash.modelo.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByEmail(email);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Perfil perfil: usuario.getPerfil()) {
			authorities.add(new SimpleGrantedAuthority(perfil.getPerfil()));
		}
		return new User(email, usuario.getContrasena(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByEmail(String email) {
		return usuarioDao.findByEmail(email);
	}

}
