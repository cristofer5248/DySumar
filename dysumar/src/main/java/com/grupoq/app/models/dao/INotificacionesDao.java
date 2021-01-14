package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Notificaciones;

public interface INotificacionesDao extends PagingAndSortingRepository<Notificaciones, Long> {
	
	public List<Notificaciones> findTop15ByOrderByIdDesc();
	public Page<Notificaciones> findByOrderByIdDesc(Pageable page); 
}
