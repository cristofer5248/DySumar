package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupoq.app.models.dao.IMargenDao;
import com.grupoq.app.models.entity.Margen;

@Service
public class IMargenServiceImpl implements IMargenService {

	@Autowired
	private IMargenDao margenDao;

	@Override
	public List<Margen> findAll() {
		// TODO Auto-generated method stub
		return (List<Margen>) margenDao.findAll();
	}
	
	

}
