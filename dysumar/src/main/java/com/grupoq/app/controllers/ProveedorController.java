package com.grupoq.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.grupoq.app.models.entity.Proveedor;
import com.grupoq.app.models.service.IProveedorService;
import com.grupoq.app.util.paginator.PageRender;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/proveedor")
@SessionAttributes("proveedor")
@Controller
public class ProveedorController {

	@Autowired
	private IProveedorService proveedoresService;

	@GetMapping(value = "/cargar_proveedor", produces = { "application/json" })
	public @ResponseBody List<Proveedor> celulasTodosJson() {
		List<Proveedor> list = proveedoresService.findAllList();
		return list;
	}

	@RequestMapping(value = { "/listar", "/listar/{term}" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			@PathVariable(value = "term", required = false) String term) {
		Pageable pageRequest = PageRequest.of(page, 20);
		Page<Proveedor> proveedors = null;
		if (term != null) {
			proveedors = proveedoresService.findByNombreStartsWith(term, pageRequest);
		} else {
			proveedors = proveedoresService.findAll(pageRequest);
		}
		PageRender<Proveedor> pageRender = new PageRender<>("listar", proveedors);
		model.addAttribute("titulo", "Listado de proveedores");
		model.addAttribute("proveedores", proveedors);
		model.addAttribute("page", pageRender);
		return "/proveedores/listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Map<String, Object> model) {
		Proveedor proveedor = new Proveedor();
		model.put("proveedor", proveedor);
		model.put("titulo", "Crear nuevo proveedor");
		model.put("nullchecker", 1);
		return "/proveedores/form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@Valid Proveedor proveedor, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Proveedors");
			return "/proveedores/form";
		}
		String mensajeFlash = (proveedor.getId() != null) ? "proveedor editado con éxito!"
				: "Proveedor creado con éxito!";
		proveedoresService.save(proveedor);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/proveedor/listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			try {
				proveedoresService.deleteById(id);
				flash.addFlashAttribute("success", "Proveedor eliminado con éxito!");
			} catch (Exception e) {
				flash.addFlashAttribute("error",
						"El proveedor posiblemente tiene registros de inventariado o productos, no se puede eliminar!");
				return "redirect:/proveedor/listar";
			}

		}
		return "redirect:/proveedor/listar";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Proveedor proveedor = null;

		if (id > 0) {
			proveedor = proveedoresService.findOne(id);
			if (proveedor == null) {
				flash.addFlashAttribute("error", "El ID del proveedor no existe en la BBDD!");
				return "redirect:/proveedor/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del proveedor no puede ser cero!");
			return "redirect:/proveedor/listar";
		}
		model.put("proveedor", proveedor);
		model.put("nullchecker", 0);
		model.put("titulo", "Editar Proveedor");
		return "/proveedores/form";
	}
}
