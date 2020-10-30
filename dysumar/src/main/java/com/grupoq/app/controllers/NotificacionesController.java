package com.grupoq.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.grupoq.app.models.entity.Notificaciones;
import com.grupoq.app.models.service.INotificacionesService;

@Controller
@RequestMapping("/notificaciones")
public class NotificacionesController {

	@Autowired
	INotificacionesService notificacionesService;

	/* @Secured({ "ROLE_ADMIN", "ROLE_INV" }) */
	@GetMapping(value = "/ver", produces = { "application/json" })
	public @ResponseBody List<Notificaciones> verSolo10() {
		List<Notificaciones> list = notificacionesService.findTop15ByOrderByIdDesc();
		return list;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Notificaciones> notificaciones = notificacionesService.findTop15ByOrderByIdDesc();
		model.addAttribute("titulo", "Listado de movimientos actuales");
		model.addAttribute("notificaciones", notificaciones);
		return "/notificaciones/listar";
	}
}
