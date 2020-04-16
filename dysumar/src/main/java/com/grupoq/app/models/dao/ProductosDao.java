package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Producto;

public interface ProductosDao extends PagingAndSortingRepository<Producto, Long> {
	@Query("select p from Producto p where p.nombrep like %?1%")
	public List<Producto> findByNombrep(String term);
	
	@Query("select p from Producto p where p.nombrep like %?1% and p.proveedor.id=?2")
	public List<Producto> findByNombrepAndProveedorId(String term, Long term2);
	
	@Query("select p from Producto p join fetch p.proveedor pro join fetch p.marca m join fetch p.categoria c join fetch p.margen mar join fetch p.inventarios i where p.id=?1")
	public Producto fetchByIdWithInventario(Long id);
	
	@Query("select p from Producto p join fetch p.proveedor pro join fetch p.marca m join fetch p.categoria c join fetch p.margen mar join fetch p.inventarios i where i.codigoProveedor=?1")
	public Producto fetchByIdWithInventarioByCodigoP(String id);
}
