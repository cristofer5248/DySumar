package com.grupoq.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Facturacion;

public interface IFacturaDao extends PagingAndSortingRepository<Facturacion, Long> {
	@Query("select f from Facturacion f join fetch f.cliente cd join fetch cd.cliente c join fetch f.tipoFactura tp join fetch f.condicionesDPago cp join fetch f.formadepago fp join fetch f.cotizacion co join fetch co.carrito ca join fetch ca.productos pro join fetch pro.proveedor prove join fetch pro.marca ma join fetch c.giro g join fetch cd.direcciones di where f.id=?1")
//	@Query("select f from Facturacion f where f.id=?1")
	public Facturacion fetchByIdWithClienteWithCarritoItemsWithProducto(Long id);

	// este es el listar del controlador este sera para solo los true
	@Query(value = "select f from Facturacion f join fetch f.cliente cd join fetch cd.cliente c join fetch f.tipoFactura tp join fetch f.condicionesDPago cp join fetch f.formadepago fp join fetch f.aCuentade usu order by f.id desc", countQuery = "select count(f) from Facturacion f join f.cliente cd join cd.cliente c join f.tipoFactura tp join f.condicionesDPago cp join f.formadepago fp join f.aCuentade usu order by f.id desc")
	public Page<Facturacion> findAllCustom(Pageable page);

	// buscar factura por cliente
	@Query(value = "select f from Facturacion f join fetch f.cliente cd join fetch cd.cliente c join fetch f.tipoFactura tp join fetch f.condicionesDPago cp join fetch f.formadepago fp join fetch  c.giro g join fetch cd.direcciones di where c.nombre like %?1", countQuery = "select count(f) from Facturacion f join f.cliente cd join cd.cliente c join f.tipoFactura tp join f.condicionesDPago cp join f.formadepago fp join c.giro g join cd.direcciones di join f.aCuentade us where c.nombre like %?1")
	public Page<Facturacion> findByClienteClienteNombreStartsWith(String nombre, Pageable page);

	// buscar por comision de usuario
	@Query(value = "select f from Facturacion f join fetch f.cliente cd join fetch cd.cliente c join fetch f.tipoFactura tp join fetch f.condicionesDPago cp join fetch f.formadepago fp join fetch c.giro g join fetch cd.direcciones di join fetch f.aCuentade us where us.nombre like %?1", countQuery = "select count(f) from Facturacion f join f.cliente cd join cd.cliente c join f.tipoFactura tp join f.condicionesDPago cp join f.formadepago fp join c.giro g join cd.direcciones di join f.aCuentade us where us.nombre like %?1")
	public Page<Facturacion> findByaACuentadeNombre(String nombre, Pageable page);

//	para encontrar si una factura esta en estado 3 que es insuficiente y al meter en inventario cambie de estado
	@Query(value = "select f from Facturacion f join fetch f.cotizacion c join fetch c.carrito ca join fetch ca.productos pro where f.status=3 and pro.id=?1")
	public List<Facturacion> findByCotizacionByCarritoItemsByIdByStatus(Long id);
	
	//para encontrar si una factura esta en estado 3 que es insuficiente y al meter en inventario cambie de estado pero sin producto
//	@Query(value = "select f from Facturacion f join fetch f.cotizacion c join fetch c.carrito ca where f.status=3 and ca.status=true")
	@Query(value = "select f from Facturacion f join fetch f.cotizacion c join fetch c.carrito ca join fetch ca.productos p where f.status=3 and ca.status!=true and p.id=?1")
	public List<Facturacion> findByCotizacionByCarritoItemsByIdByStatusWithoutProducto(Long id);

	@Query(value = "select f from Facturacion f where f.status=3")
	public List<Facturacion> findByCotizacionByCarritoItemsByIdByStatusByCarritoStatus(Long id);

	// rindiendo cuentas por vendedor en lista segun recientes 
	@Query(value = "select f from Facturacion f join fetch f.cliente cd join fetch cd.cliente c join fetch f.tipoFactura tp join fetch f.condicionesDPago cp join fetch f.formadepago fp join fetch f.aCuentade usu where f.status=1 and f.fecha BETWEEN ?1 and ?2 order by f.id desc", countQuery = "select count(f) from Facturacion f join f.cliente cd join cd.cliente c join f.tipoFactura tp join f.condicionesDPago cp join f.formadepago fp join f.aCuentade usu where f.status=1 and f.fecha BETWEEN ?1 and ?2 order by f.id desc")
	public Page<Facturacion> findAllByFecha(Pageable page, Date date1, Date date2);

	// rindiendo cuentas por vendedor agrupado
	@Query(value = "select f from Facturacion f join fetch f.cliente cd join fetch cd.cliente c join fetch f.tipoFactura tp join fetch f.condicionesDPago cp join fetch f.formadepago fp join fetch f.aCuentade usu where f.status=1 and f.fecha BETWEEN ?1 and ?2 order by c.nombre asc", countQuery = "select count(f) from Facturacion f join f.cliente cd join cd.cliente c join f.tipoFactura tp join f.condicionesDPago cp join f.formadepago fp join f.aCuentade usu where f.status=1 and f.fecha BETWEEN ?1 and ?2 order by c.nombre asc")
	public Page<Facturacion> findAllByFechaGroupBy(Pageable page, Date date1, Date date2);
	//buscar si esta la cotizacion repetida en factura
	@Query("SELECT COUNT(f) FROM Facturacion f join f.cotizacion c where c.id=?1")
	public Long cotizacionRepetida(Long id);
}
