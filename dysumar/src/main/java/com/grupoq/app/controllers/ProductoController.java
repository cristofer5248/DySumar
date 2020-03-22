package com.grupoq.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.grupoq.app.models.entity.Producto;
import com.grupoq.app.models.service.IProductoService;
import com.grupoq.app.util.paginator.PageRender;

@Controller
@RequestMapping("/producto")
@SessionAttributes("producto")
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 20);
		Page<Producto> productos = productoService.findAll(pageRequest);
		PageRender<Producto> pageRender = new PageRender<>("listar", productos);
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);
		return "/productos/listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Map<String, Object> model) {
		Producto productos = new Producto();
		model.put("producto", productos);
		model.put("titulo", "Crear nuevo producto");
		model.put("nullchecker", 1);
		return "/productos/productoform";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/productosave", method = RequestMethod.POST)
	public String guardar(@Valid Producto producto, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Productos");
			return "/productos/productoform";
		}
		String mensajeFlash = (producto.getId() != null) ? "producto editado con éxito!" : "Producto creado con éxito!";
		productoService.save(producto);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/producto/listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			productoService.delete(id);
			flash.addFlashAttribute("success", "Producto eliminado con éxito!");

		}
		return "redirect:/producto/listar";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Producto producto = null;

		if (id > 0) {
			producto = productoService.findOne(id);
			if (producto == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/producto/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/producto/listar";
		}
		model.put("producto", producto);
		
		model.put("categoriaid", producto.getCategoria().getId());
		model.put("marcaid", producto.getMarca().getIdm());
		model.put("margenid", producto.getMargen().getId());
		model.put("presentacionid", producto.getPresentacion().getId());
		model.put("proveedorid", producto.getProveedor().getId());
		model.put("nullchecker", 0);
		model.put("titulo", "Editar Producto");
		return "/productos/productoform";
	}

}