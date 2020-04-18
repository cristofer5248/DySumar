package com.grupoq.app.models.dao;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Facturacion;

public interface IFacturaDao extends PagingAndSortingRepository<Facturacion, Long> {
	@Query("select f from Facturacion f join fetch f.cliente cd join fetch cd.cliente c join fetch f.tipoFactura tp join fetch f.condicionesDPago cp join fetch f.formadepago fp join fetch f.cotizacion co join fetch co.carrito ca join fetch ca.productos pro where f.id=?1")
//	@Query("select f from Facturacion f where f.id=?1")
	public Facturacion fetchByIdWithClienteWithCarritoItemsWithProducto(Long id);
	
	@Query("select f from Facturacion f")
	public Page<Facturacion> findAllCustom(Pageable page);

}
