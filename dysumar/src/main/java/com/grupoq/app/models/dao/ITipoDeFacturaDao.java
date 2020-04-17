package com.grupoq.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.TFactura;

public interface ITipoDeFacturaDao extends CrudRepository<TFactura, Long> {

}
