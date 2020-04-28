package com.grupoq.app.models.dao;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Proveedor;

public interface IProveedorDao extends PagingAndSortingRepository<Proveedor, Long> {
public Page<Proveedor> findByNombreStartsWith(String nombre, Pageable page);
}
