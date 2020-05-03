package com.grupoq.app.controllers;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.grupoq.app.webservice.ProductosWB;
import com.grupoq.app.webservice.DescuentosWB;
import com.grupoq.app.models.entity.Descuento;
import com.grupoq.app.models.entity.Producto;
import com.grupoq.app.models.service.IDescuentoService;
import com.grupoq.app.models.service.IProductoService;

@Controller
@SessionAttributes("descuento")
@RequestMapping("/descuento")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DescuentoController {

	@Autowired
	IDescuentoService descuentoService;

	@Autowired
	IProductoService productoService;

	@GetMapping(value = "/cargar_producto/{term}/{term2}", produces = { "application/json" })
	public @ResponseBody List<ProductosWB> listarByNombreJson(@PathVariable String term, @PathVariable Long term2) {
		List<ProductosWB> list2 = new ArrayList<ProductosWB>();

//		List<Producto> list1 = productoService.findByNombrep(term);
		List<Producto> list1 = productoService.findByNombrepAndProveedorId(term, term2);
		for (Producto veamos : list1) {
			ProductosWB productos = new ProductosWB();
			productos.setId(veamos.getId());
			productos.setCategoriaid(veamos.getCategoria().getId());
			productos.setProveedornombre(veamos.getProveedor().getNombre());
			productos.setPrecio(veamos.getPrecio());
			productos.setNombrep(veamos.getNombrep());
//			productos.setMargen(veamos.getMargen().getPorcentaje());
			productos.setCodigo(veamos.getCodigo());
			list2.add(productos);

		}
		return list2;
	}

	@GetMapping(value = "/cargar_descuento/{id}/{cantidad}", produces = { "application/json" })
	public @ResponseBody DescuentosWB buscarDescuento(@PathVariable Long id, @PathVariable int cantidad) {	
		Descuento descuento = descuentoService.findFirstByProductoIdAndCantidadOrderByCantidadAsc(id, cantidad);
		DescuentosWB descuentoWB = new DescuentosWB();
		if(descuento!=null) {
			descuentoWB.setProductoid(descuento.getProducto().getId());
			descuentoWB.setDescuentoid(descuento.getId());
			descuentoWB.setCantidad(descuento.getCantidad());
			descuentoWB.setDescuento(descuento.getPorcentaje()/100);
			return descuentoWB;
		}
		return null;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Map<String, Object> model) {
		Descuento descuentos = new Descuento();
		model.put("descuento", descuentos);
		model.put("titulo", "Registrar nuevo descuento");
		model.put("nullchecker", 1);
		return "/descuentos/form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@Valid Descuento descuento, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Descuentos");
			return "/descuentos/form";
		}
		String mensajeFlash = (descuento.getId() != null) ? "descuento editada con éxito!"
				: "Descuento creada con éxito!";

		descuentoService.save(descuento);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/descuento/listar/" + descuento.getProducto().getId();
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Descuento descuentoTemp = null;
		if (id > 0) {
			try {
				descuentoTemp = descuentoService.findBy(id);
				descuentoService.delete(id);
				flash.addFlashAttribute("success", "Descuento eliminado con éxito!");
			} catch (Exception e) {
				flash.addFlashAttribute("success",
						"La descuento posiblemente tiene registros en productos, no se puede eliminar!");
				return "redirect:/descuento/listar/" + descuentoTemp.getProducto().getId();
			}

		}
		return "redirect:/descuento/listar/" + descuentoTemp.getProducto().getId();
	}

	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET)
	public String listar(@PathVariable(value = "id") Long id, Model model) {
		List<Descuento> descuentos = descuentoService.findByProductoId(id);
		model.addAttribute("titulo", "Listado de descuentos");
		model.addAttribute("descuentos", descuentos);
		return "/descuentos/listar";
	}
}
