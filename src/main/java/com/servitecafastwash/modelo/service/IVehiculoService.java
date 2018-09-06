package com.servitecafastwash.modelo.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.servitecafastwash.modelo.Vehiculo;

public interface IVehiculoService {
	
	public void guardar(Vehiculo vehiculo);
	
	public Page<Vehiculo> findAll(Pageable pageable);

	public List<Vehiculo> findAllVehiLike(String nombre);
		
	public Vehiculo findById(Long id);
	
	public void eliminar(Long id);
	
	public Vehiculo findByPlaca(String placa);
}
