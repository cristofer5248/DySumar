package com.grupoq.app.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.CDepago;
import com.grupoq.app.models.entity.ClienteDirecciones;
import com.grupoq.app.models.entity.Facturacion;
import com.grupoq.app.models.entity.Formadepago;
import com.grupoq.app.models.entity.TFactura;

public interface IFacturaService {

	public Facturacion fetchByIdWithClienteWithCarritoItemsWithProducto(Long id);
	
	public Page<Facturacion> findAll(Pageable page);
	public Facturacion findBy(Long id);
	public void save(Facturacion facturacion);
	public void delete(Long id);
	public List<ClienteDirecciones> findByCliente(Long id);
	public List<Formadepago> listFdp();
	public List<CDepago> listCdp();
	public List<TFactura> listTf();
	public Page<Facturacion> findAllCustom(Pageable page); 
	public Page<Facturacion> findByClienteClienteNombreStartsWith(String nombre, Pageable page);
	public Page<Facturacion> findByaACuentadeNombre(String nombre, Pageable page);
	public Page<Facturacion> findAllByFecha(Pageable page, Date date1, Date date2);
	public Page<Facturacion> findAllByFechaGroupBy(Pageable page, Date date1, Date date2);
	
	//para encontrar si una factura esta en estado 3 que es insuficiente y al meter en inventario cambie de estado
	public List<Facturacion> findByCotizacionByCarritoItemsByIdByStatus(Long id);
	
	//para encontrar si una factura esta en estado 3 que es insuficiente y al meter en inventario cambie de estado
	public List<Facturacion> findByCotizacionByCarritoItemsByIdByStatusWithoutProducto(Long id);
	
	public List<Facturacion> findByCotizacionByCarritoItemsByIdByStatusByCarritoStatus(Long id);
	
	//buscar si hay cotizacion repetida en factura
	
	public Long cotizacionRepetida(Long id);
}
