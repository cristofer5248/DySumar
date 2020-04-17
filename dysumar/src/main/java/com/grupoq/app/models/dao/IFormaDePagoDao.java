package com.grupoq.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Formadepago;

public interface IFormaDePagoDao extends CrudRepository<Formadepago, Long> {

}