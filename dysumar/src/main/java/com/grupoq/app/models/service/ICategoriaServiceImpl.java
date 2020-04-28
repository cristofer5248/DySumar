package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.ICategoriaDao;
import com.grupoq.app.models.entity.Categoria;

@Service
public class ICategoriaServiceImpl implements ICategoriaService {

	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return (List<Categoria>) categoriaDao.findAll();
	}

	@Override
	public void delete(Long id) {
		categoriaDao.deleteById(id);
		
	}

	@Override
	public Categoria findBy(Long id) {
		// TODO Auto-generated method stub
		return categoriaDao.findById(id).orElse(null);
	}

	@Override
	public void save(Categoria categoria) {
		categoriaDao.save(categoria);
		
	}

	@Override
	public List<Categoria> findByNombreStartsWith(String nombre) {
		// TODO Auto-generated method stub
		return categoriaDao.findByNombreStartsWith(nombre);
	}

}
