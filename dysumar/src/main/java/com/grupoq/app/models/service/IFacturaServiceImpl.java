package com.grupoq.app.models.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.ICDePagoDao;
import com.grupoq.app.models.dao.IClienteDirecciones;
import com.grupoq.app.models.dao.IFacturaDao;
import com.grupoq.app.models.dao.IFormaDePagoDao;
import com.grupoq.app.models.dao.ITipoDeFacturaDao;
import com.grupoq.app.models.entity.CDepago;
import com.grupoq.app.models.entity.ClienteDirecciones;
import com.grupoq.app.models.entity.Facturacion;
import com.grupoq.app.models.entity.Formadepago;
import com.grupoq.app.models.entity.TFactura;

@Service
public class IFacturaServiceImpl implements IFacturaService {

	@Autowired
	IFacturaDao facturadao;
	
	@Autowired
	IClienteDirecciones clientedireccionesdao;
	
	@Autowired
	IFormaDePagoDao formadepagodao;
	
	@Autowired
	ICDePagoDao cdepagodao;
	
	@Autowired
	ITipoDeFacturaDao tipodefacturadao;

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

	@Override
	public List<Formadepago> listFdp() {
		// TODO Auto-generated method stub
		return (List<Formadepago>) formadepagodao.findAll();
	}

	@Override
	public List<CDepago> listCdp() {
		// TODO Auto-generated method stub
		return (List<CDepago>) cdepagodao.findAll();
	}

	@Override
	public List<TFactura> listTf() {
		// TODO Auto-generated method stub
		return (List<TFactura>) tipodefacturadao.findAll();
	}

	@Override
	public Page<Facturacion> findAllCustom(Pageable page) {
		// TODO Auto-generated method stub
		return facturadao.findAllCustom(page);
	}

	@Override
	public Page<Facturacion> findByClienteClienteNombreStartsWith(String nombre, Pageable page) {
		// TODO Auto-generated method stub
		return facturadao.findByClienteClienteNombreStartsWith(nombre, page);
	}

	@Override
	public Page<Facturacion> findByaACuentadeNombre(String nombre, Pageable page) {
		// TODO Auto-generated method stub
		return facturadao.findByaACuentadeNombre(nombre, page);
	}
}
