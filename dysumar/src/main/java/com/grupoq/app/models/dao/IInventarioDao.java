package com.grupoq.app.models.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.grupoq.app.models.entity.Inventario;

public interface IInventarioDao extends PagingAndSortingRepository<Inventario, Long>{
	
	@Query(value= "select sum(stock) from inventario where producto_idp=:id", nativeQuery = true)
	public List<String> sumarStock(@Param("id") Long id);
	
	@Query(value="select i from Inventario i join fetch i.producto p join fetch p.proveedor prov order by i.id desc", countQuery = "select count(i) from Inventario i join i.producto p join p.proveedor prov order by i.id desc")
	public Page<Inventario> findAllCustom(Pageable page);
	
	@Query("select i from Inventario i join fetch i.producto p join fetch p.proveedor pro join fetch p.marca ma join fetch p.categoria c join fetch p.presentacion pre where i.codigoProveedor=?1")
	public List<Inventario> findByIdCodigoProveedor(String id);
	
	
	//este es para encontrar por inventario por codigo de proveedor
	@Query(value ="select i from Inventario i join fetch i.producto p join fetch p.proveedor pro join fetch p.marca ma join fetch p.categoria c where i.id=?1 and i.codigoProveedor=?2 ")
	public Inventario findByIdCodigoProveedorOb(Long id, String idcompra );
	
	//este es para encontrar por inventario por id de INVENTARIO
	@Query(value ="select i from Inventario i join fetch i.producto p join fetch p.proveedor pro join fetch p.marca ma join fetch p.categoria c where i.id=?1")
	public Inventario findByIdCustom(Long id);
	
	public Inventario findFirstByCodigoProveedor(String id);
}
