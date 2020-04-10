package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Descuento;

public interface IDescuentosDao extends CrudRepository<Descuento, Long> {

	
	public List<Descuento> findByProductoId(Long id);
}
