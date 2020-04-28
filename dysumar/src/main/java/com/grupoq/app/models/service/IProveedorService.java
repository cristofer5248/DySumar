package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Proveedor;

public interface IProveedorService {

	public Page<Proveedor> findAll(Pageable page);
	public List<Proveedor> findAllList();
	public void save (Proveedor proveedor);
	public void deleteById (Long id);
	public Proveedor findOne(Long id);
	
	public Page<Proveedor> findByNombreStartsWith(String nombre, Pageable page);
}
