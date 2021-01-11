package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.CarritoItems;

public interface ICarritoItemsDao extends CrudRepository<CarritoItems, Long>{
	@Query(value = "select c from CarritoItems c left join c.cotizacionid co left join co.factura fa where fa.status=3 and c.status!=true")
	public List<CarritoItems> sdfsfsdf ();
	
	
	public List<CarritoItems> findByProductosId(Long id);

}
