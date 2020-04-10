package com.grupoq.app.models.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Proveedor;

public interface IProveedorDao extends PagingAndSortingRepository<Proveedor, Long> {

}
