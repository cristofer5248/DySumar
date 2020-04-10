package com.grupoq.app.models.service;



import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;

import com.grupoq.app.models.entity.Producto;

public interface IProductoService {

	public Page<Producto> findAll(Pageable page);
	public void save (Producto producto);
	public void delete(Long id);
	
	public Producto findOne(Long id);
	
	public List<Producto> findAllList();
	public List<Producto> findByNombrep(String term);	
	public List<Producto> findByNombrepAndProveedorId(String term, Long term2);
}
