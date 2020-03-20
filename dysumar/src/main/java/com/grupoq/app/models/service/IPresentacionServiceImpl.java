package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IPresentacionDao;
import com.grupoq.app.models.entity.Presentacion;

@Service
public class IPresentacionServiceImpl implements IPresentacionService {
	
	@Autowired
	private IPresentacionDao presentacionDao;

	@Override
	public List<Presentacion> findAll() {
		// TODO Auto-generated method stub
		return (List<Presentacion>) presentacionDao.findAll();
	}
	

}
