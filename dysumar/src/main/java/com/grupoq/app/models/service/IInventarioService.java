package com.grupoq.app.models.service;



import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Inventario;

public interface IInventarioService {

	public Page<Inventario> findAll(Pageable pageable);
	
	public void save (Inventario inventario);
	
	
	public List<String> sumarStock(Long id);

	public void delete(Long id);
	
	public Inventario findById(Long id);
	public List<Inventario> findByIdCodigoProveedor(String id);
	public Inventario findByIdCodigoProveedorOb(String id);
}
