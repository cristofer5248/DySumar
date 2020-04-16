package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.ICarritoItemsDao;
import com.grupoq.app.models.entity.CarritoItems;

@Service
public class ICarritoItemsServiceImpl implements ICarritoItemsService {

	@Autowired
	ICarritoItemsDao carritoitemdao;
	
	@Override
	public void save(CarritoItems carritoitems) {
		carritoitemdao.save(carritoitems);		
	}

	@Override
	public void delete(Long id) {
		carritoitemdao.deleteById(id);
		
	}

	@Override
	public List<CarritoItems> findAll() {
		// TODO Auto-generated method stub
		return (List<CarritoItems>) carritoitemdao.findAll();
	}

}
