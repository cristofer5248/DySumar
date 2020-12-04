package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IMarcaDao;
import com.grupoq.app.models.entity.Marca;

@Service
public class IMarcaServiceImpl implements IMarcaService {

	@Autowired
	private IMarcaDao marcaDao;
	
	@Override
	public List<Marca> findAll() {
		// TODO Auto-generated method stub
		return (List<Marca>) marcaDao.findAll();
	}

	@Override
	public Page<Marca> findAllPage(Pageable page) {
	 return marcaDao.findAll(page);
	}

	@Override
	public void save(Marca marca) {
		marcaDao.save(marca);
		
	}

	@Override
	public void delete(Long id) {
		marcaDao.deleteById(id);
		
	}

	@Override
	public Page<Marca> findByNombre(String nombre, Pageable page) {
		// TODO Auto-generated method stub
		return marcaDao.findByNombremStartsWith(nombre, page);
	}

	@Override
	public Marca findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return marcaDao.findByNombrem(nombre);
	}

}
