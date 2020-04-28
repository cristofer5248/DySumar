package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IGiroDao;
import com.grupoq.app.models.entity.Giro;

@Service
public class IGiroServiceImpl implements IGiroService {
	
	@Autowired
	private IGiroDao giroDao;

	@Override
	public List<Giro> listAll() {
		return (List<Giro>) giroDao.findAll();
	}



	@Override
	public void delete(Long id) {
		giroDao.deleteById(id);
	}

	@Override
	public Giro findBy(Long id) {
		return giroDao.findById(id).orElse(null);
	}



	@Override
	public List<Giro> findByNombre(String term) {
		return giroDao.findByNombre(term);
	}



	@Override
	public void save(Giro giro) {
		giroDao.save(giro);
		
	}





}
