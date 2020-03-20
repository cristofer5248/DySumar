package com.grupoq.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Marca;

public interface IMarcaDao extends CrudRepository<Marca, Long> {

}
