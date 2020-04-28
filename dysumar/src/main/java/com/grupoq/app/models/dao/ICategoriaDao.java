package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria,Long> {

	public List<Categoria> findByNombreStartsWith(String nombre);
}
