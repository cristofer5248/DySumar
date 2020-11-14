package com.grupoq.app.controllers;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupoq.app.models.entity.Cotizacion;
import com.grupoq.app.models.entity.Notificaciones;
import com.grupoq.app.models.service.ICotizacionService;
import com.grupoq.app.models.service.INotificacionesService;

@Controller
@RequestMapping("/cotizacion")
public class CotizacionController {

	@Autowired
	ICotizacionService cotizacionService;
	@Autowired
	INotificacionesService notificacionesService;

	@RequestMapping(value = "/listar/{param}", method = RequestMethod.GET)
	public String listar(@RequestParam(value = "param", required = true) String param, Model model) {

		return "cotizaciones/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_SELLER", "ROLE_FACT" })
	@RequestMapping(value = "/ver/{term}", method = RequestMethod.GET)
	public String ver(@PathVariable Long term, Map<String, Object> model, RedirectAttributes flash) {
		Cotizacion cotizacion = cotizacionService.listAllById(term);
		if (cotizacion == null) {
			flash.addFlashAttribute("error", "El codigo no existe en la base de datos");
			return "redirect:/factura/listar";
		}
		model.put("cotizacion", cotizacion);
		model.put("codigoco", cotizacion.getId());
		model.put("titulo", "Detalle de cotizacion");
		return "cotizaciones/ver";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_SELLER","ROLE_JEFEADM" })
	@RequestMapping(value = "/aprobar/{term}", method = RequestMethod.GET)
	public String aprobar(@PathVariable Long term, Map<String, Object> model, RedirectAttributes flash) {
		Cotizacion cotizacion = cotizacionService.findby(term);
		cotizacion.setAprobado(true);
		cotizacionService.save(cotizacion);
		model.put("success", "Cotizacion aprobada");
		nuevaNotificacion("fas fa-file-alt", "Cotizacion con id "+cotizacion.getId()+" aprobada", "/cotizacion/ver/"+cotizacion.getId(), "green");
		return "redirect:/cotizacion/ver/" + term;
	}

	public void nuevaNotificacion(String icono, String nombre, String url, String color) {
		Notificaciones noti = new Notificaciones();
		noti.setFecha(new Date());
		noti.setIcono(icono);
		noti.setNombre(nombre);
		noti.setUrl(url);
		noti.setColor(color);
		notificacionesService.save(noti);
	}
}
