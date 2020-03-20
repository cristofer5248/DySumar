package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IDescuentosDao;
import com.grupoq.app.models.entity.Descuento;

@Service
public class IDescuentoServiceImpl implements IDescuentoService {

	@Autowired
	private IDescuentosDao descuentoDao;
	
	@Override
	public List<Descuento> findAll() {
		// TODO Auto-generated method stub
		return (List<Descuento>) descuentoDao.findAll();
	}

}
