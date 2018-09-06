package com.servitecafastwash.modelo.service;

import java.util.List;

import com.servitecafastwash.modelo.Factura;

public interface IFacturaService {
	
	public Factura findById(Long id);
	
	public void save(Factura factura);
	
	public void delete(Long id);
	
	public List<Factura> findByLast(Long id);
	
}
