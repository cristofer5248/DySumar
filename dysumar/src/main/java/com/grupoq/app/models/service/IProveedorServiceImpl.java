package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IProveedorDao;
import com.grupoq.app.models.entity.Proveedor;

@Service
public class IProveedorServiceImpl implements IProveedorService {

	@Autowired
	private IProveedorDao proveedorDao;

	@Override
	public List<Proveedor> findAll() {
		// TODO Auto-generated method stub
		return (List<Proveedor>) proveedorDao.findAll();
	}
	
	
}
