package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Giro;

public interface IGiroDao extends CrudRepository<Giro, Long> {
	
	@Query("select g from Giro g where g.detalles like %?1%")
	public List<Giro> findByNombre(String term);
}
