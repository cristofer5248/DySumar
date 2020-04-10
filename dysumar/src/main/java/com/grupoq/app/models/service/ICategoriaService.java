package com.grupoq.app.models.service;

import java.util.List;

import com.grupoq.app.models.entity.Categoria;

public interface ICategoriaService {
	
	public List<Categoria> findAll();
	public void delete (Long id);
	public Categoria findBy(Long id);
	public void save(Categoria categoria);

}
