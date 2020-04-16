package com.grupoq.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Cotizacion;

public interface ICotizacionDao extends CrudRepository<Cotizacion, Long> {

}
