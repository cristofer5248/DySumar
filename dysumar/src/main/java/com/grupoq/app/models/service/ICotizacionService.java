package com.grupoq.app.models.service;

import java.util.List;

import com.grupoq.app.models.entity.Cotizacion;

public interface ICotizacionService {

	
	public void save(Cotizacion cotizacion);
	public void delete(Long id);
	public List<Cotizacion> findAll();
	public Cotizacion findby(Long id);
	public Cotizacion listAllById(Long id);
}
