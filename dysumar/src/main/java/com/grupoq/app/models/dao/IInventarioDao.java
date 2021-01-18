package com.grupoq.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.grupoq.app.models.entity.Inventario;

public interface IInventarioDao extends PagingAndSortingRepository<Inventario, Long> {

	@Query(value = "select sum(stock) from inventario where producto_idp=:id", nativeQuery = true)
	public List<String> sumarStock(@Param("id") Long id);

	@Query(value = "select i from Inventario i join fetch i.producto p join fetch p.proveedor prov join fetch i.movimientos m group by m  order by i.id desc", countQuery = "select count(i) from Inventario i join i.producto p join p.proveedor prov join i.movimientos m  group by m order by i.id desc ")
	public Page<Inventario> findAllCustom(Pageable page);

	@Query(value = "select i from Inventario i join fetch i.producto p join fetch p.proveedor prov join fetch i.movimientos m where i.fecha between ?1 and ?2 group by m  order by i.id desc", countQuery = "select count(i) from Inventario i join i.producto p join p.proveedor prov join i.movimientos m where i.fecha between ?1 and ?2 group by m order by i.id desc ")
	public Page<Inventario> findAllByDates(Pageable page, Date date1, Date date2);

	@Query("select i from Inventario i join fetch i.producto p join fetch p.proveedor pro join fetch p.marca ma join fetch p.categoria c join fetch p.presentacion pre where i.codigoProveedor=?1")
	public List<Inventario> findByIdCodigoProveedor(String id);

	// este es para encontrar por inventario por codigo de proveedor
	@Query(value = "select i from Inventario i join fetch i.producto p join fetch p.proveedor pro join fetch p.marca ma join fetch p.categoria c where i.id=?1 and i.codigoProveedor=?2 ")
	public Inventario findByIdCodigoProveedorOb(Long id, String idcompra);

	// este es para encontrar por inventario por id de INVENTARIO
	@Query(value = "select i from Inventario i join fetch i.producto p join fetch p.proveedor pro join fetch p.marca ma join fetch p.categoria c where i.id=?1")
	public Inventario findByIdCustom(Long id);

	public Inventario findFirstByCodigoProveedor(String id);

	public Page<Inventario> findByCodigoProveedorLike(String id, Pageable page);

	public List<Inventario> findByProductoId(Long id);
}
