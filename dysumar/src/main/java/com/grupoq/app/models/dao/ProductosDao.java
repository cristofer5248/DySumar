package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Producto;

public interface ProductosDao extends PagingAndSortingRepository<Producto, Long> {
	@Query("select p from Producto p join fetch p.proveedor prv join fetch p.marca m join fetch p.presentacion pre where p.nombrep like %?1%")
	public List<Producto> findByNombrep(String term);
	
	@Query("select p from Producto p join fetch p.proveedor prv join fetch p.marca m join fetch p.presentacion pre where p.nombrep like %?1% and m.nombrem like %?2%")
	public List<Producto> findByNombrepYMarca(String term, String term2);
	
	@Query(value = "select p from Producto p join fetch p.proveedor prv join fetch p.marca m join fetch p.presentacion pre where p.nombrep like %?1% and m.nombrem like %?2%", countQuery = "select count(p) from Producto p join p.proveedor prv join p.marca m join p.presentacion pre where p.nombrep like %?1% and m.nombrem like %?2%")
	public Page<Producto> findByNombrepYMarcaPage(String term, String term2, Pageable page);
	
	@Query(value = "select p from Producto p join fetch p.proveedor prv join fetch p.marca m join fetch p.presentacion pre where p.bodega like %?1%", countQuery = "select count(p) from Producto p join p.proveedor prv join p.marca m join p.presentacion pre where p.bodega like %?1%")
	public Page<Producto> findByBodega(String term, Pageable page);
	
	
	//este es el eque lista todo en el get listar
	@Query(value = "select p from Producto p join fetch p.marca m join fetch p.categoria c join fetch p.proveedor pro join fetch p.presentacion pre where p.status=true", countQuery = "select count(p) from Producto p join p.marca m join p.categoria c join p.proveedor pro join p.presentacion pre where p.status=true")
	public Page<Producto> findAllJoin(Pageable page);

	@Query(value = "select p from Producto p join fetch p.marca m join fetch p.categoria c join fetch p.proveedor pro join fetch p.presentacion pre where p.status=false", countQuery = "select count(p) from Producto p join p.marca m join p.categoria c join p.proveedor pro join p.presentacion pre where p.status=false")
	public Page<Producto> findAllJoinFalse(Pageable page);
	
	
	@Query(value = "select p from Producto p join fetch p.marca m join fetch p.categoria c join fetch p.proveedor pro join fetch p.presentacion pre where p.nombrep like %?1%", countQuery = "select count(p) from Producto p join p.marca m join p.categoria c join p.proveedor pro join p.presentacion pre where p.nombrep like %?1%")
	public Page<Producto> findAllLike(String termn,Pageable page);
	
	@Query("select p from Producto p where p.nombrep like %?1% and p.proveedor.id=?2")
	public List<Producto> findByNombrepAndProveedorId(String term, Long term2);
//	@Query("select p from Producto p join fetch p.proveedor pro join fetch p.marca m join fetch p.categoria c join fetch p.margen mar join fetch p.inventarios i where p.id=?1")
	@Query("select p from Producto p join fetch p.proveedor pro join fetch p.marca m  left join p.productosmodify pm join fetch p.categoria c left join p.inventarios i where p.id=?1")
	public Producto fetchByIdWithInventario(Long id);
//	@Query("select p from Producto p join fetch p.proveedor pro join fetch p.marca m join fetch p.categoria c join fetch p.margen mar join fetch p.inventarios i where i.codigoProveedor=?1")
	@Query("select p from Producto p join fetch p.proveedor pro join fetch p.marca m join fetch p.categoria c join fetch p.inventarios i where i.codigoProveedor=?1")
	public Producto fetchByIdWithInventarioByCodigoP(String id);
	
	
	//filtro para searchbar
	
	@Query(value = "select p from Producto p join fetch p.marca m join fetch p.categoria c join fetch p.proveedor pro join fetch p.presentacion pre where p.codigo like %?1%", countQuery = "select count(p) from Producto p join p.marca m join p.categoria c join p.proveedor pro join p.presentacion pre where p.codigo like %?1%")
	public Page<Producto> findByCodigo(String codigo, Pageable page);
	
	@Query(value = "select p from Producto p join fetch p.marca m join fetch p.categoria c join fetch p.proveedor pro join fetch p.presentacion pre where pro.nombre like %?1%", countQuery = "select count(p) from Producto p join p.marca m join p.categoria c join p.proveedor pro join p.presentacion pre where pro.nombre like %?1%")
	public Page<Producto> findByProveedor(String codigo, Pageable page);
	
	@Query(value = "select p from Producto p join fetch p.marca m join fetch p.categoria c join fetch p.proveedor pro join fetch p.presentacion pre where m.nombrem like %?1%", countQuery = "select count(p) from Producto p join p.marca m join p.categoria c join p.proveedor pro join p.presentacion pre where m.nombrem like %?1%")
	public Page<Producto> findByMarca(String codigo, Pageable page);
	
	@Query(value = "select p from Producto p join fetch p.marca m join fetch p.categoria c join fetch p.proveedor pro join fetch p.presentacion pre where c.nombre like %?1%", countQuery = "select count(p) from Producto p join p.marca m join p.categoria c join p.proveedor pro join p.presentacion pre where c.nombre like %?1%")
	public Page<Producto> findByCategoria(String codigo, Pageable page);
	
	public Producto findByCodigo(String codigo);
	
	@Query(value = "select p from Producto p where p.id!=?1 and p.codigo!=?2")
	public Producto findOneByCodigoNot(Long id,String id2);
}
