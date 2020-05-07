package com.grupoq.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupoq.app.models.entity.Inventario;
import com.grupoq.app.models.entity.Movimientos;
import com.grupoq.app.models.entity.Producto;
import com.grupoq.app.models.service.IInventarioService;
import com.grupoq.app.models.service.IMovimientosService;
import com.grupoq.app.models.service.IProductoService;
import com.grupoq.app.util.paginator.PageRender;

@Controller
@SessionAttributes("inventario")
@RequestMapping("/inventario")
@Secured({"ROLE_ADMIN","ROLE_INV","ROLE_JEFEADM"})
public class InventarioController {

	@Autowired
	private IInventarioService inventarioService;
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IMovimientosService movimientosService;

	
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
		model.put("infProducto", productoTemp.getNombrep() + " con el codigo " + productoTemp.getCodigo());
		model.put("titulo", "Ingreso");
		model.put("nullchecker", 1);
		model.put("nullchecker", 0);
		return "/inventario/form";
	}

	
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo2(Map<String, Object> model, RedirectAttributes flash) {
		Inventario inventario = new Inventario();			
		model.put("inventario", inventario);		
		model.put("titulo", "Inventariar");		
		return "/inventario/form2";
	}
	

	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(
			@RequestParam(name="fecha", required=true) String fecha,
			@RequestParam(name="codigo", required=true) String codigo,
			@RequestParam(name="item_id[]", required=false) Long[] itemId,
			@RequestParam(name="cantidad[]", required=false) Integer[] cantidad , Model model, RedirectAttributes flash,
			SessionStatus status) throws ParseException {
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date1 = simpleDateFormat.parse(fecha);

//		if (result.hasErrors()) {
//			model.addAttribute("titulo", "Inventariado");
//			return "/producto/listar";
//		}
		String mensajeFlash = (itemId != null) ? "inventario editado con éxito!"
				: "Inventario creado con éxito!";
//		Inventario justCodeRepetead = null;
//		justCodeRepetead = inventarioService.findByCodigoProveedor(codigo);
//		if(justCodeRepetead!=null) {
//			model.addAttribute("error","Error: Ese id de compra ya existe!");
//			model.addAttribute("titulo","Nuevo");
//			return "/inventario/form2";
//		}
		
		if(itemId == null || itemId.length==0) {
			model.addAttribute("titulo","Nuevo ingreso");
			model.addAttribute("error","Error: El nuevo no puede tener lineas de productos vacias!");
			return "/inventario/form2";
		}
		Movimientos movimiento = new Movimientos();
		movimientosService.save(movimiento);
		for(int i = 0; i<itemId.length; i++) {
			Inventario inventario = new Inventario();
			Producto producto = productoService.findOne(itemId[i]);
			inventario.setProducto(producto);
			inventario.setStock(cantidad[i]);
			inventario.setFecha(date1);
			inventario.setCodigoProveedor(codigo);
			inventario.setMovimientos(movimiento);
			inventarioService.save(inventario);			
			// llenado de nuevo stock/ suma con inventario DEPRECATED PORQUE ES MEJOR SOLO SUMAR, LA FACTURA RESTARÁ
//			List<String> total = inventarioService.sumarStock(itemId[i]);
			producto.setStock(producto.getStock()+cantidad[i]);
			productoService.save(producto);
		}
		

		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/inventario/listar";
	}

	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Inventario inventarioTemp = inventarioService.findById(id);
			if (inventarioTemp != null) {
				int stockDeInventario = inventarioTemp.getStock();
				System.out.print("Codigo para eliminar de inventario /n " + stockDeInventario);
//				Producto productoTemp = inventarioTemp.getProducto();
//				int stockTemp = productoTemp.getStock() - stockDeInventario;
//				productoTemp.setStock(stockTemp);
//				productoService.save(productoTemp);
				inventarioService.delete(id);
				flash.addFlashAttribute("success", "Inventariado eliminado con éxito!");
			}
		}
		return "redirect:/inventario/listar";
	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
//para una solo entity
		//		Inventario inventario;
		Movimientos movimientos;
		try {
			 
			 movimientos = movimientosService.findById(id);
		} catch (Exception e) {
			flash.addFlashAttribute("error", "El ingreso con ese codigo no existe en la base de datos");
			return "redirect:/inventario/listar";
		}
		
//		Inventario inventario = inventarioService.findByIdCodigoProveedorOb(id);
		
//		List<Inventario> inventario = inventarioService.findByIdCodigoProveedor(id);
		
		
		model.put("inventarios",movimientos);
		model.put("proveedor", movimientos.getInventario().get(0).getCodigoProveedor());
		model.put("fecha", movimientos.getInventario().get(0).getFecha());
		model.put("codigopro", id);
		model.put("titulo", "Detalle del ingreso : " + id);
		
//		model.put("inventarios", inventario);
//		model.put("proveedor", inventario.get(0).getProducto().getProveedor().getNombre());
//		model.put("fecha", inventario.get(0).getFecha().toString());
//		model.put("codigopro", id);
//		model.put("titulo", "Detalle del ingreso : " + id);		
		return "/inventario/ver";
	}
}