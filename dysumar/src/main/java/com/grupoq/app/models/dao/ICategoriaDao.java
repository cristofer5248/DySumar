package com.grupoq.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria,Long> {

}
