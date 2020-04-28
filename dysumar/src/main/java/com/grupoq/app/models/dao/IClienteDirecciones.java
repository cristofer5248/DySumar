package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.ClienteDirecciones;

public interface IClienteDirecciones extends CrudRepository<ClienteDirecciones, Long> {

	@Query("select cd from ClienteDirecciones cd join fetch cd.cliente c join fetch cd.direcciones d where c.id=?1")
	public List<ClienteDirecciones> findByCliente(Long id);
	
	
}
