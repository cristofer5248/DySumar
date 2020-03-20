package com.grupoq.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Role;

public interface IRolesDao extends CrudRepository<Role, Long> {
	
	@Query ("select r from Role r where r.user_id =?1 and r.authority=?2")
	public Role findByUser_idByAuthority(Long id, String rol);

	@Query ("select r from Role r where r.user_id =?1")
	public Role findByUser_id(Long id);
	
}
