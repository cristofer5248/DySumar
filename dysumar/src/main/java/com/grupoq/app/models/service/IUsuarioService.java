package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Role;
import com.grupoq.app.models.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Usuario findByUsername(String user);
	public Page<Usuario> findByIdNot(Long id,Pageable pageable);
	public Page<Usuario> findByRoles_Authority(String param,Pageable pageable);
	
	public void delete(Long id);
	
	public Usuario findOne(Long id);
	public void save(Usuario user);
	
	public List<Role> findAllRole();
	
	public void saveRol(Role param);
	
	public void saveRolNative(Long id, String rol);
}

