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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@Secured({ "ROLE_ADMIN", "ROLE_INV", "ROLE_JEFEADM", "ROLE_SELLER" })
	@RequestMapping(value = { "/listar", "/listar/{op}/{nombrep}" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			@PathVariable(value = "nombrep", required = false) String nombrep,
			@PathVariable(value = "op", required = false) String op) {
		Pageable pageRequest = PageRequest.of(page, 20);
		Page<Producto> productos = null;
		if (nombrep != null) {
			if (op.equals("nombre")) {
				productos = productoService.findAllLike(nombrep, pageRequest);
			}
			if (op.equals("codigo")) {
				productos = productoService.findByCodigo(nombrep, pageRequest);
			}
			if (op.equals("proveedor")) {
				productos = productoService.findByProveedor(nombrep, pageRequest);
			}
			if (op.equals("marca")) {
				productos = productoService.findByMarca(nombrep, pageRequest);
			}
			if (op.equals("categoria")) {
				productos = productoService.findByCategoria(nombrep, pageRequest);
			}
			if (productos == null) {
				model.addAttribute("error", "Error, Query mal formado");
			}
		} else {
			productos = productoService.findAllJoin(pageRequest);
		}
		PageRender<Producto> pageRender = new PageRender<>("listar", productos);
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);
		return "/productos/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_INV", "ROLE_SELLER" })
	@RequestMapping(value = { "/listarS" }, method = RequestMethod.GET)
	public String listarS(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			@PathVariable(value = "nombrep", required = false) String nombrep) {
		Pageable pageRequest = PageRequest.of(page, 20);
		Page<Producto> productos;
		productos = productoService.findAllJoinFalse(pageRequest);

		PageRender<Producto> pageRender = new PageRender<>("listarS", productos);
		model.addAttribute("titulo", "Listado de solicitud de productos");
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);
		return "/productos/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_INV" })
	@RequestMapping(value = "/status/{id}")
	public String cambiarStatus(@PathVariable(value = "id") Long id, Map<String, Object> model,
			RedirectAttributes flash) {
		Producto producto = productoService.findOne(id);

		String mensajeFlash = (producto != null) ? "Solicitud aceptada con éxito!"
				: "Error en la solicitud, ese ID de solicitud podria no existir!!";

		producto.setStatus(true);
		productoService.save(producto);
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/producto/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_INV" })
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Map<String, Object> model) {
		Producto productos = new Producto();
		productos.setStatus(true);
		model.put("producto", productos);
		model.put("titulo", "Crear nuevo producto");
		model.put("nullchecker", 1);
		return "/productos/productoform";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_SELLER" })
	@RequestMapping(value = "/nuevoS", method = RequestMethod.GET)
	public String nuevoS(Map<String, Object> model) {
		Producto productos = new Producto();
		productos.setStatus(false);
		model.put("producto", productos);
		model.put("titulo", "Crear nuevo producto");
		model.put("nullchecker", 1);
		return "/productos/productoform";
	}

//	@Secured({"ROLE_ADMIN","ROLE_INV"})
//	@RequestMapping(value = "/nuevo10", method = RequestMethod.GET)
//	public String nuevosdjiosjfdoi(Map<String, Object> model) {
//		Producto productos = new Producto();
//		model.put("producto", productos);
//		model.put("titulo", "Crear nuevo producto");
//		model.put("nullchecker", 1);
//		return "/templates/productos/productoform";
//	}

	@Secured({ "ROLE_ADMIN", "ROLE_INV", "ROLE_SELLER" })
	@RequestMapping(value = "/productosave", method = RequestMethod.POST)
	public String guardar(@Valid Producto producto, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Productos");
			return "/productos/productoform";
		}
		String mensajeFlash = (producto.getId() != null) ? "Producto editado con éxito!"
				: "Producto creado o solicitud enviada con éxito!";
		if (producto.getId() == null) {
			producto.setStock(0);
		}
		productoService.save(producto);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/producto/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_INV" })
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			try {
				productoService.delete(id);
				flash.addFlashAttribute("success", "Producto eliminado con éxito!");
			} catch (Exception e) {
				flash.addFlashAttribute("error",
						"El producto posiblemente tiene registros de inventariado, no se puede eliminar!");
				return "redirect:/producto/listar";
			}

		}
		return "redirect:/producto/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_INV" })
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
//		model.put("margenid", producto.getMargen().getId());
		model.put("presentacionid", producto.getPresentacion().getId());
		model.put("proveedorid", producto.getProveedor().getId());
		model.put("nullchecker", 0);
		model.put("titulo", "Editar Producto");
		return "/productos/productoform";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_INV" })
	@GetMapping(value = "/cargar_productos", produces = { "application/json" })
	public @ResponseBody List<Producto> marcaTodosJson() {
		List<Producto> list = productoService.findAllList();
		return list;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_INV", "ROLE_JEFEADM" })
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

//		Taller taller = clienteService.findByIdTallerWithClienteWithFactura(id);
//		List<?> taller = facturaService.probando(id);
//		Generica sin fetch no join
//		Producto producto = productoService.findOne(id);
		Producto producto = productoService.fetchProductoWithInventario(id);
		if (producto == null) {
			flash.addFlashAttribute("error", "El producto no existe en la base de datos");
			return "redirect:/producto/listar";
		}
		model.put("producto", producto);
		model.put("titulo", "Detalle producto: " + producto.getNombrep());
		return "/productos/ver";
	}

}