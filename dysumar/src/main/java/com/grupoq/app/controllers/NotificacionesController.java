package com.grupoq.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grupoq.app.models.entity.Notificaciones;
import com.grupoq.app.models.service.INotificacionesService;

@Controller
@RequestMapping("/notificaciones")
public class NotificacionesController {

	@Autowired INotificacionesService notificacionesService;
	
	/* @Secured({ "ROLE_ADMIN", "ROLE_INV" }) */
	@GetMapping(value = "/ver", produces = { "application/json" })
	public @ResponseBody List<Notificaciones> verSolo10() {
		List<Notificaciones> list = notificacionesService.findTop15ByOrderByIdDesc();
		return list;
	}
}
