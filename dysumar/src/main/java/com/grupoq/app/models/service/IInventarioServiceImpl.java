package com.grupoq.app.models.service;

import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoq.app.models.dao.IInventarioDao;
import com.grupoq.app.models.entity.Inventario;

@Service
public class IInventarioServiceImpl implements IInventarioService {
	
	@Autowired
	private IInventarioDao inventarioDao;

	@Transactional(readOnly = true)
	public Page<Inventario> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return inventarioDao.findAll(pageable);
	}


	
	

}
