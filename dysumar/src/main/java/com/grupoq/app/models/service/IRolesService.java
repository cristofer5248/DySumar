package com.grupoq.app.models.service;

import java.util.List;

import com.grupoq.app.models.entity.Role;

public interface IRolesService {

	public void update(Role role);
	
	public void save(Role role);
	
	public Role findByOne(Long id);
	public Role findByUser_id(Long id);
	public List<Role> findByUser_idList(Long id);
	public void delete(Role id);
	public Role findByUser_idByAuthority(Long id, String rol);
	
}
