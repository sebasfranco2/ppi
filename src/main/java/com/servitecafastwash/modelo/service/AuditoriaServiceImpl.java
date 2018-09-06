package com.servitecafastwash.modelo.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servitecafastwash.model.dao.IAuditoriaDao;
import com.servitecafastwash.modelo.Auditoria;

@Service
public class AuditoriaServiceImpl implements IAuditoriaService {

	@Autowired
	private IAuditoriaDao auditoriaDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void save(Auditoria auditoria) {
		auditoriaDao.save(auditoria);
	}

}
