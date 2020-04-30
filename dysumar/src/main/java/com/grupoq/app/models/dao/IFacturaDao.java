package com.grupoq.app.models.dao;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Facturacion;

public interface IFacturaDao extends PagingAndSortingRepository<Facturacion, Long> {
	@Query("select f from Facturacion f join fetch f.cliente cd join fetch cd.cliente c join fetch f.tipoFactura tp join fetch f.condicionesDPago cp join fetch f.formadepago fp join fetch f.cotizacion co join fetch co.carrito ca join fetch ca.productos pro join fetch pro.proveedor prove join fetch pro.marca ma join fetch c.giro g join fetch cd.direcciones di where f.id=?1")
//	@Query("select f from Facturacion f where f.id=?1")
	public Facturacion fetchByIdWithClienteWithCarritoItemsWithProducto(Long id);
	
	@Query(value="select f from Facturacion f join fetch f.cliente cd join fetch cd.cliente c join fetch f.tipoFactura tp join fetch f.condicionesDPago cp join fetch f.formadepago fp join fetch f.aCuentade usu",countQuery = "select count(f) from Facturacion f join f.cliente cd join cd.cliente c join f.tipoFactura tp join f.condicionesDPago cp join f.formadepago fp join f.aCuentade usu")
	public Page<Facturacion> findAllCustom(Pageable page);
	
	//buscar factura por cliente
	@Query(value="select f from Facturacion f join fetch f.cliente cd join fetch cd.cliente c join fetch f.tipoFactura tp join fetch f.condicionesDPago cp join fetch f.formadepago fp join fetch  c.giro g join fetch cd.direcciones di where c.nombre like %?1",countQuery="select count(f) from Facturacion f join f.cliente cd join cd.cliente c join f.tipoFactura tp join f.condicionesDPago cp join f.formadepago fp join c.giro g join cd.direcciones di join f.aCuentade us where c.nombre like %?1")
	public Page<Facturacion> findByClienteClienteNombreStartsWith(String nombre, Pageable page);
	
	//buscar por comision de usuario
	@Query(value ="select f from Facturacion f join fetch f.cliente cd join fetch cd.cliente c join fetch f.tipoFactura tp join fetch f.condicionesDPago cp join fetch f.formadepago fp join fetch c.giro g join fetch cd.direcciones di join fetch f.aCuentade us where us.nombre like %?1", countQuery="select count(f) from Facturacion f join f.cliente cd join cd.cliente c join f.tipoFactura tp join f.condicionesDPago cp join f.formadepago fp join c.giro g join cd.direcciones di join f.aCuentade us where us.nombre like %?1")
	public Page<Facturacion> findByaACuentadeNombre(String nombre, Pageable page);

}
