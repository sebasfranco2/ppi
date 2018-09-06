package com.servitecafastwash.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servitecafastwash.model.dao.ITipoVehiculoDao;
import com.servitecafastwash.modelo.TipoVehiculo;

@Service
public class TipoVehiculoServiceImpl implements ITipoVehiculoService {

	@Autowired
	private ITipoVehiculoDao tipoVehiculoDao;

	@Override
	@Transactional(readOnly=true)
	public List<TipoVehiculo> findAll() {
		return (List<TipoVehiculo>) tipoVehiculoDao.findAll();
	}

}
