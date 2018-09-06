package com.servitecafastwash.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servitecafastwash.model.dao.IVehiculoDao;
import com.servitecafastwash.modelo.Vehiculo;

@Service
public class VehiculoServiceImp implements IVehiculoService {

	@Autowired
	private IVehiculoDao vehiculoDao;

	@Override
	@Transactional
	public void guardar(Vehiculo vehiculo) {
		vehiculoDao.save(vehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Vehiculo> findAll(Pageable pageable) {
		return vehiculoDao.findAllOrder(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> findAllVehiLike(String nombre) {
		return vehiculoDao.findAllVehiLike(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Vehiculo findById(Long id) {
		return vehiculoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		vehiculoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Vehiculo findByPlaca(String placa) {
		return vehiculoDao.findByPlacaIgnoreCase(placa);
	}


}
