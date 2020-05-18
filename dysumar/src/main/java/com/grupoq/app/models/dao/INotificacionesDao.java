package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Notificaciones;

public interface INotificacionesDao extends CrudRepository<Notificaciones, Long> {
	
	public List<Notificaciones> findTop15ByOrderByIdDesc();
}
