package com.grupoq.app.models.service;

import java.util.List;

import com.grupoq.app.models.entity.Descuento;

public interface IDescuentoService {

	public List<Descuento> findAll();
	public void save(Descuento descuento);
	public void delete (Long id);
	public Descuento findBy(Long id);
	public List<Descuento> findByProductoId(Long id);
}
