package com.grupoq.app.models.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IClienteDao;
import com.grupoq.app.models.dao.IClienteDirecciones;
import com.grupoq.app.models.dao.IFacturaDao;
import com.grupoq.app.models.entity.ClienteDirecciones;
import com.grupoq.app.models.entity.Facturacion;

@Service
public class IFacturaServiceImpl implements IFacturaService {

	@Autowired
	IFacturaDao facturadao;
	
	@Autowired
	IClienteDirecciones clientedireccionesdao;

	@Override
	public Facturacion fetchByIdWithClienteWithCarritoItemsWithProducto(Long id) {
		// TODO Auto-generated method stub
		return facturadao.fetchByIdWithClienteWithCarritoItemsWithProducto(id);
	}

	@Override
	public Page<Facturacion> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return facturadao.findAll(page);
	}

	@Override
	public Facturacion findBy(Long id) {
		// TODO Auto-generated method stub
		return facturadao.findById(id).orElse(null);
	}

	@Override
	public void save(Facturacion facturacion) {
		facturadao.save(facturacion);
		
	}

	@Override
	public void delete(Long id) {
		facturadao.deleteById(id);
		
	}

	@Override
	public List<ClienteDirecciones> findByCliente(Long id) {
		// TODO Auto-generated method stub
		return clientedireccionesdao.findByCliente(id);
	}
}
