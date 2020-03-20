package com.grupoq.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Producto;

public interface ProductosDao extends PagingAndSortingRepository<Producto, Long> {

	
}
