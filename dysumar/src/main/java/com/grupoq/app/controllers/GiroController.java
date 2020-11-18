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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.grupoq.app.models.entity.Giro;
import com.grupoq.app.models.service.IGiroService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SessionAttributes("giro")
@RequestMapping("/giro")
@Controller

public class GiroController {

	@Autowired
	private IGiroService giroService;

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_SELLER", "ROLE_INV" })
	@GetMapping(value = "/cargar_giro/{term}", produces = { "application/json" })
	public @ResponseBody List<Giro> giroTodosJson(@PathVariable String term) {
		List<Giro> list = giroService.findByNombre(term);
		return list;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_SELLER", "ROLE_INV" })
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		List<Giro> giro = giroService.listAll();
		model.addAttribute("titulo", "Listado de Giros");
		model.addAttribute("giros", giro);
		return "/giros/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_SELLER" })
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Map<String, Object> model) {
		Giro giro = new Giro();
		model.put("giro", giro);
		model.put("titulo", "Registrar nuevo Giro");
		model.put("nullchecker", 1);
		return "/giros/form";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_SELLER" })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@Valid Giro giro, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Giros");
			return "/giros/form";
		}
		String mensajeFlash = (giro.getId() != null) ? "Giro editado con éxito!" : "Giro creado con éxito!";

		giroService.save(giro);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/giro/listar";
	}

//	@Secured({"ROLE_ADMIN","ROLE_JEFEADM", "ROLE_SELLER"})
//	@RequestMapping(value= "/saveExpress", method= RequestMethod.POST)
//	public @ResponseBody void saveExpress(@RequestParam String nombreGiro, SessionStatus status,RedirectAttributes flash) {
//		Giro giro = new Giro();
//		giro.setDetalles(nombreGiro);		
//		giroService.save(giro);
//		status.setComplete();
//		String mensajeFlash = (giro.getId() != null) ? "Giro editado con éxito!" : "Giro creado con éxito!";
//		flash.addFlashAttribute("success", mensajeFlash);		
//	}
	/* @Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_SELLER" }) */
	
	@RequestMapping(value = "/saveExpress/{nombre}", method = {RequestMethod.GET}, produces = { "application/json" })
	public @ResponseBody Giro saveExpress(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "nombre", required = true) String nombre) {
		Giro giro = new Giro();
		giro.setDetalles(nombre);
		giroService.save(giro);		
		return giro; 
		
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			try {
				giroService.delete(id);
				flash.addFlashAttribute("success", "Giro eliminado con éxito!");
			} catch (Exception e) {
				flash.addFlashAttribute("success",
						"El giro posiblemente tiene registros en algun proveedor o cliente, no se puede eliminar!");
				return "redirect:/giro/listar";
			}

		}
		return "redirect:/giro/listar";
	}

}
