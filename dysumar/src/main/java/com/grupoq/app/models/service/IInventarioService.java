package com.grupoq.app.models.service;



import java.util.Date;
import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Inventario;

public interface IInventarioService {

	public Page<Inventario> findAll(Pageable pageable);
	public Page<Inventario> findAllFechas(Pageable pageable,Date date1, Date date2);
	
	public void save (Inventario inventario);
	
	public List<Inventario> findByProductoById(Long id);
	
	
	public List<String> sumarStock(Long id);

	public void delete(Long id);
	
	public Inventario findById(Long id);
	public List<Inventario> findByIdCodigoProveedor(String id);
	public Inventario findByIdCodigoProveedorOb(Long id,String codcompra);
	public Inventario findByCodigoProveedor(String id);
	public Inventario findByIdCustom(Long id);
}
