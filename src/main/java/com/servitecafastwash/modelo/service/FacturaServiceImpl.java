package com.servitecafastwash.modelo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servitecafastwash.model.dao.IFacturaDao;
import com.servitecafastwash.modelo.Factura;

@Service
public class FacturaServiceImpl implements IFacturaService {

	@Autowired
	private IFacturaDao facturaDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public Factura findById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Factura> findByLast(Long id) {
		// TODO Auto-generated method stub
		return facturaDao.findByLast(id);
	}

}
