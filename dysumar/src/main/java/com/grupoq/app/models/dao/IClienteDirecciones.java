package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.grupoq.app.models.entity.ClienteDirecciones;

public interface IClienteDirecciones extends CrudRepository<ClienteDirecciones, Long> {

	@Query("select cd from ClienteDirecciones cd join fetch cd.cliente c join fetch cd.direcciones d where c.id=?1")
	public List<ClienteDirecciones> findByCliente(Long id);
	
	@Query(value="select cd from ClienteDirecciones cd join fetch cd.cliente c join fetch cd.facturacion f join fetch f.tipoFactura tf join fetch f.formadepago fp join fetch f.condicionesDPago cp where cd.id=?1 order by f.fecha desc")
	public ClienteDirecciones findByIdByFacturacion(Long id);
	
	@Query("select cd from ClienteDirecciones cd join fetch cd.cliente c join fetch cd.direcciones d where cd.id=?1")
	public ClienteDirecciones findByIdDireccionOnly(Long id);
}	
