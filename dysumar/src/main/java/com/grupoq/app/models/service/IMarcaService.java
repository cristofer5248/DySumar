package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Marca;


public interface IMarcaService {

	public List<Marca> findAll();
	public Page<Marca> findAllPage(Pageable page);
	public void save(Marca marca);
	public void delete(Long id);
	public Page<Marca> findByNombre(String nombre ,Pageable page);
	public Marca findByNombre(String nombre);
}
