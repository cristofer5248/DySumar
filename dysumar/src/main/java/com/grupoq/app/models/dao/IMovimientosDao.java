package com.grupoq.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.grupoq.app.models.entity.Movimientos;

public interface IMovimientosDao extends PagingAndSortingRepository<Movimientos, Long> {

	//este es para encontrar por inventario por id de INVENTARIO
	@Query(value ="select m from Movimientos m join fetch m.inventario i join fetch i.producto p join fetch p.proveedor pro join fetch p.marca ma join fetch p.categoria c where m.id=?1")
	public Movimientos findById1(Long id);
	
	@Query(value ="select m from Movimientos m join fetch m.inventario i join fetch i.producto p join fetch p.proveedor pro join fetch p.marca ma join fetch p.categoria", countQuery = "select count(m) from Movimientos m join m.inventario i join i.producto p join p.proveedor pro join p.marca ma join p.categoria c")
	public Page<Movimientos> findAll(Pageable page);
}
