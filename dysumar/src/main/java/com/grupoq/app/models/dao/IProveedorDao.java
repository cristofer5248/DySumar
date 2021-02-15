package com.grupoq.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Proveedor;

public interface IProveedorDao extends PagingAndSortingRepository<Proveedor, Long> {

	public List<Proveedor> findAllByOrderByNombreAsc();

	public Page<Proveedor> findByNombreContainingIgnoreCase(String nombre, Pageable page);

	public Proveedor findByNombre(String nombre);
}
