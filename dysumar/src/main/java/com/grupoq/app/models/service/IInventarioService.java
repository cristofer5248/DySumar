package com.grupoq.app.models.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Inventario;

public interface IInventarioService {

	public Page<Inventario> findAll(Pageable pageable);

	
}
