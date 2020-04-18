package com.grupoq.app.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.grupoq.app.models.dao.ICarritoItemsDao;
import com.grupoq.app.models.entity.CarritoItems;
import com.grupoq.app.models.entity.Cotizacion;
import com.grupoq.app.models.entity.Facturacion;
import com.grupoq.app.models.entity.Producto;
import com.grupoq.app.models.service.ICotizacionService;
import com.grupoq.app.models.service.IFacturaService;
import com.grupoq.app.models.service.IProductoService;
import com.grupoq.app.util.paginator.PageRender;
import com.grupoq.app.webservice.ProductosWB;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;

@Controller
@SessionAttributes("Facturacion")
@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	IFacturaService facturaservice;

	@Autowired
	IProductoService productoservice;

	@Autowired
	ICotizacionService cotizacionService;

	@Autowired
	ICarritoItemsDao carritoitemsdao;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 20);
		Page<Facturacion> Facturacion = facturaservice.findAll(pageRequest);
		PageRender<Facturacion> pageRender = new PageRender<>("listar", Facturacion);
		model.addAttribute("titulo", "Listado de Facturacion");
		model.addAttribute("facturas", Facturacion);
		model.addAttribute("page", pageRender);
		return "/facturas/listar";
	}
//PARA CARRITO
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo2(Map<String, Object> model, RedirectAttributes flash) {
		CarritoItems carrito = new CarritoItems();
		model.put("carritoitems", carrito);
		model.put("titulo", "Ingreso de articulos");
		return "/facturas/form2";
	}
//ESTOS SI SON PARA FACTURA
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/nuevof/{term}", method = RequestMethod.GET)
	public String nuevo(Map<String, Object> model, RedirectAttributes flash,@PathVariable Long term) {
		Facturacion facturacion = new Facturacion();
		Cotizacion cotizacion = new Cotizacion();
		cotizacion = cotizacionService.findby(term);		
		model.put("titulo", "Facturacion");
		if(cotizacion==null) {
			model.put("facturacion", facturacion);
			flash.addFlashAttribute("error","No existe ese id de cotizacion. Mostrando formulario vacio...");
			return "facturas/form";
		}
		
		facturacion.setCotizacion(cotizacion);
		if(facturacion.getCotizacion()!=null) {
		System.out.print("La cotizacion no esta vacia, es mas este tiene el id \n"+facturacion.getCotizacion().getId());
		}
		//llenando select a lo dundo
		model.put("fdePago", facturaservice.listFdp());
		model.put("cdePago", facturaservice.listCdp());
		model.put("tfactura", facturaservice.listTf());		
		//llenando selects a lo dundo
		model.put("facturacion", facturacion);
		return "/facturas/form";
	}
//ESTOS SI SON PARA FACTURA
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/nuevof", method = RequestMethod.GET)
	public String nuevoSin(Map<String, Object> model, RedirectAttributes flash) {
		Facturacion facturacion = new Facturacion();
		model.put("facturacion", facturacion);
		
		//llenando select a lo dundo
		model.put("fdePago", facturaservice.listFdp());
		model.put("cdePago", facturaservice.listCdp());
		model.put("tfactura", facturaservice.listTf());		
		//llenando selects a lo dundo
		
		model.put("titulo", "Facturacion");
		return "/facturas/form";
	}

//guardar final de factura
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/savefactura", method = RequestMethod.POST)
	public String guardarfactura(@Valid Facturacion facturacion, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Facturacions");
			if(facturacion.getCotizacion()==null) {
				flash.addFlashAttribute("error","El codigo de cotizacion no puede estar vacio, mostrando formulario vacio...");
//				return "redirect:/factura/nuevof";
				return "/facturas/form";
			}
//			return "redirect:/factura/nuevof/"+facturacion.getCotizacion().getId();
			
			return "/facturas/form";
		}
		String mensajeFlash = (facturacion.getId() != null) ? "facturacion editado con éxito!" : "Facturacion creado con éxito!";
		facturaservice.save(facturacion);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/factura/listar";
	}
	
	
	@GetMapping(value = "/cargar_producto/{term}", produces = { "application/json" })
	public @ResponseBody List<ProductosWB> listarByNombreJson(@PathVariable String term) {
		List<ProductosWB> list2 = new ArrayList<ProductosWB>();
//		List<Producto> list1 = productoService.findByNombrep(term);
		List<Producto> list1 = productoservice.findByNombrep(term);
		for (Producto veamos : list1) {
			ProductosWB productos = new ProductosWB();
			productos.setId(veamos.getId());
			productos.setCategoriaid(veamos.getCategoria().getId());
			productos.setProveedornombre(veamos.getProveedor().getNombre());
			productos.setPrecio(veamos.getPrecio());
			productos.setNombrep(veamos.getNombrep());
			productos.setMargen(veamos.getMargen().getPorcentaje());
			productos.setCodigo(veamos.getCodigo());
			list2.add(productos);

		}
		return list2;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, Model model,
			RedirectAttributes flash, SessionStatus status) throws ParseException {
//		if (result.hasErrors()) {
//			model.addAttribute("titulo", "Inventariado");
//			return "/producto/listar";
//		}
		String mensajeFlash = "Cotizacion registrada con éxito!";
		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Nuevo ingreso");
			model.addAttribute("error", "Error: No puede haber linea productos vacias!");
			return "/facturacion/nuevo";
		}
		Cotizacion cotizacion = new Cotizacion();
		cotizacion.setFecha(new Date());
		cotizacionService.save(cotizacion);

		Random random = new Random();
		for (int i = 0; i < itemId.length; i++) {
			System.out.print("La cantidad es: " + cantidad[i] + " \n");
			int x = random.nextInt(900) + 100;
			CarritoItems carrito = new CarritoItems();
			Producto producto = productoservice.findOne(itemId[i]);
			carrito.setProductos(producto);
			// codigo generado
			String codigoGenerated = x + "xxx";

			carrito.setCodigo(codigoGenerated);
			// fin

			carrito.setCotizacionid(cotizacion);
			carrito.setCantidad(cantidad[i]);
			carritoitemsdao.save(carrito);

		}
		mensajeFlash = mensajeFlash + " \nEl codigo de cotizacion es: " + cotizacion.getId();
		flash.addFlashAttribute("error", "Recuerde no borrar los datos con el campo en rojo");
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/factura/nuevof/"+cotizacion.getId();
	}
//para ver el detalle de la FACTURA
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {


		Facturacion facturacion = facturaservice.fetchByIdWithClienteWithCarritoItemsWithProducto(id);
		if (facturacion == null) {
			flash.addFlashAttribute("error", "El ingreso con ese codigo no existe en la base de datos");
			return "redirect:/facturacion/listar";
		}
		model.put("facturaciones", facturacion);		
//		model.put("proveedor", facturacion.get(0).getProducto().getProveedor().getNombre());
//		model.put("fecha", facturacion.get(0).getFecha().toString());
		model.put("codigofa", id);
		model.put("titulo", "Detalle de factura # : " + id);		
		return "/facturas/ver";
	}
}
