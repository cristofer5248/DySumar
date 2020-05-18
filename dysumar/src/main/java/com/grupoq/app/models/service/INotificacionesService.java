package com.grupoq.app.models.service;

import java.util.List;

import com.grupoq.app.models.entity.Notificaciones;

public interface INotificacionesService {

	public List<Notificaciones> findTop15ByOrderByIdDesc(); 
	public void save (Notificaciones notificaciones);
	
}
