package com.grupoq.app.models.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Movimientos;

public interface IMovimientosService {

	public Page<Movimientos> findAll(Pageable page);
	public Movimientos findById(Long id);
	public void save(Movimientos movimientos);
}
