package com.servitecafastwash.modelo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.servitecafastwash.modelo.Servicio;

public interface IServicioService {
	
	public void save(Servicio servicio);
	
	public Page<Servicio> findAll(Pageable pageable);
	
	public List<Servicio> findNombre(String nombre);
	
	public void delete(Long id);
	
	public Servicio findById(Long id);
}
