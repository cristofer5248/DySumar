package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoq.app.models.entity.Producto;
import com.grupoq.app.models.dao.ProductosDao;

@Service
public class IProductoServiceImpl implements IProductoService {

	@Autowired
	private ProductosDao productosDao;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return productosDao.findAll(page);
	}

	@Override
	@Transactional
	public void save(Producto producto) {
		// TODO Auto-generated method stub
		productosDao.save(producto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productosDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findOne(Long id) {
		// TODO Auto-generated method stub
		return productosDao.findById(id).orElse(null);
	}

	@Override
	public List<Producto> findAllList() {
		// TODO Auto-generated method stub
		return (List<Producto>) productosDao.findAll();
	}

}
