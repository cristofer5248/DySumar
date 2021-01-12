package com.grupoq.app.models.service;

import java.util.List;

import com.grupoq.app.models.entity.Presentacion;

public interface IPresentacionService {

	public List<Presentacion> findAll();
	public List<Presentacion> findByOrderByUnidadDesc();
	public void save(Presentacion presentacion);
	public Presentacion findByDetalle(String detalle);
	public List<Presentacion> findByDetalleList(String nombre);
}
