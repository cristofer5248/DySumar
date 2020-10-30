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

	@Query("select c from Cliente c join fetch c.usuario u where u.username=?1 and c.nombre like %?2")
	public List<Cliente> findByUsuarioLike(String term, String nombre);

	@Query(value = "select c from Cliente c join fetch c.usuario u where u.username=?1", countQuery = "select count(c) from Cliente c join c.usuario u where u.username=?1")
	public Page<Cliente> findAllByUsuarioPage(String usuario, Pageable pageable);

	@Query(value = "select c from Cliente c join fetch c.usuario u inner join c.giro g where g.id=?1 and u.username=?2", countQuery = "select count(c) from Cliente c join c.usuario u join c.giro g where g.id=?1 and u.username=?2")
	public Page<Cliente> findAllByGiro(Long id, String user, Pageable pageable);

	@Query(value = "select c from Cliente c join fetch c.usuario u inner join c.giro g where g.id=?1", countQuery = "select count(c) from Cliente c join c.usuario u join c.giro g where g.id=?1")
	public Page<Cliente> findAllByGiroAdmin(Long id, Pageable pageable);
	
	@Query(value = "select c from Cliente c join fetch c.usuario u inner join c.giro g where c.nombre like %?1%", countQuery = "select count(c) from Cliente c join c.usuario u join c.giro g where c.nombre like %?1%")
	public Page<Cliente> findByNombrePage(String term, Pageable pageable);
	
	@Query(value = "select c from Cliente c join fetch c.usuario u inner join c.giro g where c.nombre like %?1% and u.username=?2", countQuery = "select count(c) from Cliente c join c.usuario u join c.giro g where c.nombre like %?1% and u.username=?2")
	public Page<Cliente> findByNombrePageOwner(String term, String usuario,Pageable pageable);
	
	@Query(value = "select c from Cliente c join fetch c.usuario u inner join c.giro g where c.id>=?1", countQuery = "select count(c) from Cliente c join c.usuario u join c.giro g where c.id>=?1")
	public Page<Cliente> findByIdPage(Long term, Pageable pageable);
	
	@Query(value = "select c from Cliente c join fetch c.usuario u inner join c.giro g where c.id>=?1 and u.username=?2", countQuery = "select count(c) from Cliente c join c.usuario u join c.giro g where c.id>=?1 and u.username=?2")
	public Page<Cliente> findByIdPageOwner(Long term, String usuario, Pageable pageable);

}