package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Cliente;


public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

	@Query("select c from Cliente c where c.nombre like %?1")
	public List<Cliente> findByNombre(String term);
	
	@Query("select c from Cliente c join fetch c.usuario u where u.username=?1")
	public List<Cliente> findByUsuario(String term);
	
	@Query(value="select c from Cliente c join fetch c.usuario u where u.username=?1", countQuery = "select count(c) from Cliente c join c.usuario u where u.username=?1")
	public Page <Cliente> findAllByUsuarioPage(String usuario, Pageable pageable);
	
	
}