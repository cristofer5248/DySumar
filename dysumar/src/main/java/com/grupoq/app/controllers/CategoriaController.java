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

import com.grupoq.app.models.entity.Categoria;
import com.grupoq.app.models.service.ICategoriaService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/categoria")
@SessionAttributes("categoria")
@Controller
public class CategoriaController {

	@Autowired
	private ICategoriaService categoriaService;

	@GetMapping(value = "/cargar_categoria", produces = { "application/json" })
	public @ResponseBody List<Categoria> celulasTodosJson() {
		List<Categoria> list = categoriaService.findAll();
		return list;
	}

	@RequestMapping(value = { "/listar", "/listar/{param}" }, method = RequestMethod.GET)
	public String listar(Model model, @PathVariable(value = "param", required = false) String param) {
		List<Categoria> categorias = null;
		if (param != null) {
			categorias = categoriaService.findByNombreStartsWith(param);
		} else {
			categorias = categoriaService.findAll();
		}
		model.addAttribute("titulo", "Listado de categorias");
		model.addAttribute("categorias", categorias);
		return "/categorias/listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Map<String, Object> model) {
		Categoria categoria = new Categoria();
		model.put("categoria", categoria);
		model.put("titulo", "Crear nuevo categoria");
		model.put("nullchecker", 1);
		return "/categorias/form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@Valid Categoria categoria, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Categorias");
			return "/categorias/form";
		}
		String mensajeFlash = (categoria.getId() != null) ? "categoria editado con éxito!"
				: "Categoria creado con éxito!";
		categoriaService.save(categoria);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/categoria/listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			try {
				categoriaService.delete(id);
				flash.addFlashAttribute("success", "Categoria eliminado con éxito!");
			} catch (Exception e) {
				flash.addFlashAttribute("error",
						"El categoria posiblemente tiene registros de inventariado o productos, no se puede eliminar!");
				return "redirect:/categoria/listar";
			}

		}
		return "redirect:/categoria/listar";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Categoria categoria = null;

		if (id > 0) {
			categoria = categoriaService.findBy(id);
			if (categoria == null) {
				flash.addFlashAttribute("error", "El ID del categoria no existe en la BBDD!");
				return "redirect:/categoria/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del categoria no puede ser cero!");
			return "redirect:/categoria/listar";
		}
		model.put("categoria", categoria);
		model.put("nullchecker", 0);
		model.put("titulo", "Editar Categoria");
		return "/categorias/form";
	}
}
