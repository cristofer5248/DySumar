package com.grupoq.app.models.service;

import com.grupoq.app.models.entity.Role;

public interface IRolesService {

	public void update(Role role);
	
	public void save(Role role);
	
	public Role findByOne(Long id);
	public Role findByUser_id(Long id);
	public void delete(Role id);
	public Role findByUser_idByAuthority(Long id, String rol);
	
}
