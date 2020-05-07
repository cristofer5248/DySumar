package com.grupoq.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IMovimientosDao;
import com.grupoq.app.models.entity.Movimientos;
import org.springframework.data.domain.Pageable;

@Service
public class IMovimientosServiceImpl implements IMovimientosService {

	@Autowired
	IMovimientosDao movimientosDao;
	
	@Override
	public Page<Movimientos> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return movimientosDao.findAll(page);
	}

	@Override
	public Movimientos findById(Long id) {
		// TODO Auto-generated method stub
		return movimientosDao.findById1(id);
	}

	@Override
	public void save(Movimientos movimientos) {
		movimientosDao.save(movimientos);
		
	}

}
