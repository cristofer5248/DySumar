package com.grupoq.app.models.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Inventario;

public interface IInventarioDao extends PagingAndSortingRepository<Inventario, Long>{
	

}
