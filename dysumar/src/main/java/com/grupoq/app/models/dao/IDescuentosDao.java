package com.grupoq.app.models.dao;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Descuento;

public interface IDescuentosDao extends CrudRepository<Descuento, Long> {

	
	public List<Descuento> findByProductoId(Long id);
	
//	public Descuento findFirstByProductoIdAndCantidadOrderByCantidadAsc(Long id, int cantidad);
//	@Query(value="select d from Descuento d join fetch d.producto p where p.id=?1 and d.cantidad>=?2 ORDER BY d.cantidad ASC limit 1")
	public Descuento findFirstByProductoIdAndCantidadLessThanEqualOrderByCantidadDesc(Long id, int cantidad);
}
