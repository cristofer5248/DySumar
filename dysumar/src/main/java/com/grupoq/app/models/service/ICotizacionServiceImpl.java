package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.ICotizacionDao;
import com.grupoq.app.models.entity.Cotizacion;

@Service
public class ICotizacionServiceImpl implements ICotizacionService {

	@Autowired
	ICotizacionDao cotizaciondao;
	
	@Override
	public void save(Cotizacion cotizacion) {
		cotizaciondao.save(cotizacion);
		
	}

	@Override
	public void delete(Long id) {
	   cotizaciondao.deleteById(id);
		
	}

	@Override
	public List<Cotizacion> findAll() {
		// TODO Auto-generated method stub
		return (List<Cotizacion>) cotizaciondao.findAll();
	}

	@Override
	public Cotizacion findby(Long id) {
		// TODO Auto-generated method stub
		return cotizaciondao.findById(id).orElse(null);
	}

	@Override
	public Cotizacion listAllById(Long id) {
		// TODO Auto-generated method stub
		return cotizaciondao.listAllById(id);
	}

}
