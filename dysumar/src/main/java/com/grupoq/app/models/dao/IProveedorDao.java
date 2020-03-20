package com.grupoq.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Proveedor;

public interface IProveedorDao extends CrudRepository<Proveedor, Long> {

}
