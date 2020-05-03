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

	@Override
	public void save(Descuento descuento) {
		descuentoDao.save(descuento);
		
	}

	@Override
	public void delete(Long id) {
		descuentoDao.deleteById(id);
		
	}

	@Override
	public Descuento findBy(Long id) {
		// TODO Auto-generated method stub
		return descuentoDao.findById(id).orElse(null);
	}

	@Override
	public List<Descuento> findByProductoId(Long id) {
		// TODO Auto-generated method stub
		return descuentoDao.findByProductoId(id);
	}

	@Override
	public Descuento findFirstByProductoIdAndCantidadOrderByCantidadAsc(Long id, int cantidad) {
		// TODO Auto-generated method stub
		return descuentoDao.findFirstByProductoIdAndCantidadLessThanEqualOrderByCantidadDesc(id, cantidad);
	}

}
