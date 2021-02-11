package com.grupoq.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.entity.ProductosModify;

@Service
public class IProductoModifyServiceImpl implements IProductoModifyService {

	@Autowired
	IProductoModifyService productomodifyservice;

	@Override
	public void save(ProductosModify productosmodify) {
		productomodifyservice.save(productosmodify);

	}

}
