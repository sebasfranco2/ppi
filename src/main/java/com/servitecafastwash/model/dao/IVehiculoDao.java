package com.servitecafastwash.model.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.servitecafastwash.modelo.Vehiculo;

public interface IVehiculoDao extends PagingAndSortingRepository<Vehiculo, Long>{
			
	@Query("select s from Vehiculo s where s.placa like %?1%")
	public List<Vehiculo> findAllVehiLike(String nombre);
	
	public Vehiculo findByPlacaIgnoreCase(String placa);

	@Query("select s from Vehiculo s order by id desc")
	public Page<Vehiculo> findAllOrder(Pageable pageable);

	
}
