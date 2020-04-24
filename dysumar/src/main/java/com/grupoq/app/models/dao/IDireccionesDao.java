package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Direccion;

public interface IDireccionesDao extends CrudRepository<Direccion, Long> {

	@Query("select d from Direccion d where d.nombre like %?1%")
	public List<Direccion> findallBy(String term);
}
