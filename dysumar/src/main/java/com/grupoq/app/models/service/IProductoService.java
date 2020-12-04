package com.grupoq.app.models.service;



import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;

import com.grupoq.app.models.entity.Producto;

public interface IProductoService {

	public Page<Producto> findAll(Pageable page);
	public Page<Producto> findAllJoin(Pageable page);
	public Page<Producto> findAllJoinFalse(Pageable page);
	
	
	public void save (Producto producto);
	public void delete(Long id);
	public Page<Producto> findAllLike(String termn,Pageable page);
	
	public Producto findOne(Long id);
	
	public List<Producto> findAllList();
	public List<Producto> findByNombrep(String term);	
	public List<Producto> findByNombrepAndProveedorId(String term, Long term2);
	
	public Producto fetchProductoWithInventario(Long id);
	public Producto fetchByIdWithInventarioByCodigoP(String id);
	
	
	//buscar para parametros en searchbar
	
	public Page<Producto> findByCodigo(String codigo, Pageable page);
	public Page<Producto> findByProveedor(String codigo, Pageable page);
	public Page<Producto> findByMarca(String codigo, Pageable page);
	public Page<Producto> findByCategoria(String codigo, Pageable page);
	public Producto findByCodigo(String codigo);
	
}
