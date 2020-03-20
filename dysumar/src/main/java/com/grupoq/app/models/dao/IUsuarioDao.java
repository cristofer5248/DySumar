package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.grupoq.app.models.entity.Usuario;

public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

	public Usuario findByUsername(String user);
	
	@Query("select u from Usuario u where u.username like %?1%")
	public List<Usuario> findByUsernameLike(String term);
	
	@Query(value="select * from roles r inner join users u on u.id=r.id;", nativeQuery=true)
	public void updaterol(Long param);
	
	public Page<Usuario> findByIdNot(Long id,Pageable pageable);
	
	public List<Usuario> findByRoles_AuthorityOrderByUsernameAsc(String param);
	
	public Page<Usuario> findByRoles_Authority(String param,Pageable pageable);
	
	@Modifying
	@Query(value="insert into roles values(null,:param2,:param);", nativeQuery=true)
	public void saveRolNative(@Param("param")Long id, @Param("param2")String rol);
	
	
}
