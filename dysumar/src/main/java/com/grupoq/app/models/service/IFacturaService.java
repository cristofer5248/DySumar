package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.ClienteDirecciones;
import com.grupoq.app.models.entity.Facturacion;

public interface IFacturaService {

	public Facturacion fetchByIdWithClienteWithCarritoItemsWithProducto(Long id);
	
	public Page<Facturacion> findAll(Pageable page);
	public Facturacion findBy(Long id);
	public void save(Facturacion facturacion);
	public void delete(Long id);
	public List<ClienteDirecciones> findByCliente(Long id);
	
}
