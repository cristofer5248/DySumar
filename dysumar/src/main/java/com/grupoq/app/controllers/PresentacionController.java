package com.grupoq.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupoq.app.models.entity.Presentacion;
import com.grupoq.app.models.service.IPresentacionService;

@Controller
@RequestMapping("/presentacion")
public class PresentacionController {

	@Autowired
	private IPresentacionService presentacionService;
	

	@GetMapping(value = "/cargar_presentacion", produces = { "application/json" })
	public @ResponseBody List<Presentacion> celulasTodosJson() {
		List<Presentacion> list = presentacionService.findByOrderByUnidadDesc();
		return list;
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_INV"})
	@GetMapping(value = "/form")
	public String nuevo(Map<String, Object> model) {
		Presentacion presentacion = new Presentacion();
		model.put("titulo", "Ingresar unidad de medida");
		model.put("presentacion", presentacion);
		return "/presentacion/form";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_INV"})
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@Valid Presentacion presentacion, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Ingresar unidad de medida");
			return "/direcciones/form";
		}
		String mensajeFlash = (presentacion.getId() != null) ? "Unidad editada con éxito! Ahora puedes encontrar el dato en seccion de presentacion"
				: "Unidad creada con éxito! Ahora puedes encontrar el dato en seccion de presentacion";

		// aqui
		presentacionService.save(presentacion);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/producto/nuevo";
	}

}
