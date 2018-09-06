package com.servitecafastwash.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.servitecafastwash.modelo.Factura;

public interface IFacturaDao  extends CrudRepository<Factura, Long>{

	@Query(value="select f from Factura f where vehiculo_id =?1 order by id desc")
	public List<Factura> findByLast(Long id);
}
