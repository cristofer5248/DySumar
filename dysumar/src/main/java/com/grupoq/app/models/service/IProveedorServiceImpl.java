package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IProveedorDao;
import com.grupoq.app.models.entity.Proveedor;

@Service
public class IProveedorServiceImpl implements IProveedorService {

	@Autowired
	private IProveedorDao proveedorDao;

	@Override
	public List<Proveedor> findAllList() {
		// TODO Auto-generated method stub
		return (List<Proveedor>) proveedorDao.findAll();
	}

	@Override
	public void save(Proveedor proveedor) {
		proveedorDao.save(proveedor);
		
	}

	@Override
	public void deleteById(Long id) {
		proveedorDao.deleteById(id);
		
	}



	@Override
	public Page<Proveedor> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return proveedorDao.findAll(page);
	}

	@Override
	public Proveedor findOne(Long id) {
		// TODO Auto-generated method stub
		return proveedorDao.findById(id).orElse(null);
	}
	
	
}
