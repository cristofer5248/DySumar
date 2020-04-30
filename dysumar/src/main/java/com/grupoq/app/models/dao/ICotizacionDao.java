package com.grupoq.app.models.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Cotizacion;

public interface ICotizacionDao extends CrudRepository<Cotizacion, Long> {

	@Query("select c from Cotizacion c join fetch c.carrito ca join fetch ca.productos pro join fetch pro.marca ma join fetch pro.proveedor prov where c.id=?1")
	public Cotizacion listAllById(Long id);
	
//	@Query("select c from Cotizacion c inner join c.carrito ca inner join ca.productos pro where c.id=?1")
//	public Cotizacion listAllByCodigo(Long id);
}
