package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
