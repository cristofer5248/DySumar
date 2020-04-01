package com.grupoq.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
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

import com.grupoq.app.models.entity.Inventario;
import com.grupoq.app.models.entity.Producto;
import com.grupoq.app.models.service.IInventarioService;
import com.grupoq.app.models.service.IProductoService;
import com.grupoq.app.util.paginator.PageRender;

@Controller
@SessionAttributes("inventario")
@RequestMapping("/inventario")
public class InventarioController {

	@Autowired
	private IInventarioService inventarioService;
	@Autowired
	private IProductoService productoService;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 20);
		Page<Inventario> inventario = inventarioService.findAll(pageRequest);
		PageRender<Inventario> pageRender = new PageRender<>("listar", inventario);
		model.addAttribute("titulo", "Listado de inventario");
		model.addAttribute("inventarios", inventario);
		model.addAttribute("page", pageRender);
		return "/inventario/listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/nuevo/{id}", method = RequestMethod.GET)
	public String nuevo(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Inventario inventario = new Inventario();
		Producto productoTemp = new Producto();
		if (id > 0) {			
			productoTemp = productoService.findOne(id);
			if (productoTemp == null) {
				flash.addFlashAttribute("error", "El ID del producto ha inventariar no existe en la BBDD!");
				return "redirect:/producto/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/producto/listar";
		}
		inventario.setProducto(productoTemp);
		model.put("inventario", inventario);
		model.put("infProducto",productoTemp.getNombrep() + " con el codigo " + productoTemp.getCodigo());
		model.put("titulo", "Inventariar");
		model.put("nullchecker", 1);
		model.put("nullchecker", 0);
		return "/inventario/form";		
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@Valid Inventario inventario, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Inventariado");
			return "/producto/listar";
		}
		String mensajeFlash = (inventario.getId() != null) ? "inventario editado con éxito!"
				: "Inventario creado con éxito!";		
		inventarioService.save(inventario);
		// llenado de nuevo stock/ suma con inventario
		List<String> total = inventarioService.sumarStock(inventario.getProducto().getId());
		Producto productoTemp = inventario.getProducto();
		productoTemp.setStock(Integer.parseInt(total.get(0).toString()));
		productoService.save(productoTemp);

		System.out.print("/n Veamos si sale :/n" + total.get(0).toString());

		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/inventario/listar";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Inventario inventarioTemp = inventarioService.findById(id);
			if(inventarioTemp!=null) {
			int stockDeInventario = inventarioTemp.getStock();
			System.out.print("Codigo para eliminar de inventario /n "+stockDeInventario);
			Producto productoTemp = inventarioTemp.getProducto();
			int stockTemp = productoTemp.getStock()-stockDeInventario;
			productoTemp.setStock(stockTemp);
			productoService.save(productoTemp);
			inventarioService.delete(id);
			flash.addFlashAttribute("success", "Inventariado eliminado con éxito!");
			}
		}
		return "redirect:/inventario/listar";
	}
}