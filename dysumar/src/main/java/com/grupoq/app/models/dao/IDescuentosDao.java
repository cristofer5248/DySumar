package com.grupoq.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Descuento;

public interface IDescuentosDao extends CrudRepository<Descuento, Long> {

}
