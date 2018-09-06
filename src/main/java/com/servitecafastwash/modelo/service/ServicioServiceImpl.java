package com.servitecafastwash.modelo.service;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servitecafastwash.model.dao.IServicioDao;
import com.servitecafastwash.modelo.Servicio;

@Service
public class ServicioServiceImpl  implements IServicioService{

	@Autowired
	private IServicioDao servicioDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void save(Servicio servicio) {
		servicioDao.save(servicio);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Servicio> findAll(Pageable pageable) {
		return  servicioDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Servicio> findNombre(String nombre) {
		return servicioDao.findByNombreLikeIgnoreCase("%" + nombre + "%");
	}

	@Override
	@Transactional
	public void delete(Long id) {
		servicioDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Servicio findById(Long id) {
		return servicioDao.findById(id).orElse(null);
	}

}
