package com.servitecafastwash.model.dao;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.servitecafastwash.modelo.Servicio;

public interface IServicioDao extends PagingAndSortingRepository<Servicio, Long>{

		
	public List<Servicio> findByNombreLikeIgnoreCase(String nombre);
	
}
