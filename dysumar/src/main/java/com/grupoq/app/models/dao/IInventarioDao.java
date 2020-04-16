package com.grupoq.app.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.grupoq.app.models.entity.Inventario;

public interface IInventarioDao extends PagingAndSortingRepository<Inventario, Long>{
	
	@Query(value= "select sum(stock) from inventario where producto_idp=:id", nativeQuery = true)
	public List<String> sumarStock(@Param("id") Long id);
	
	@Query("select i from Inventario i join fetch i.producto p join fetch p.proveedor pro join fetch p.marca ma join fetch p.categoria c where i.codigoProveedor=?1")
	public List<Inventario> findByIdCodigoProveedor(String id);
}
