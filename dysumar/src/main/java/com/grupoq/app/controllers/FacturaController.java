package com.grupoq.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.grupoq.app.models.entity.CarritoItems;
import com.grupoq.app.models.entity.Cotizacion;
import com.grupoq.app.models.entity.Descuento;
import com.grupoq.app.models.entity.Facturacion;
import com.grupoq.app.models.entity.Notificaciones;
import com.grupoq.app.models.entity.Producto;
import com.grupoq.app.models.entity.Usuario;
import com.grupoq.app.models.service.ICarritoItemsService;
import com.grupoq.app.models.service.IClienteService;
import com.grupoq.app.models.service.ICotizacionService;
import com.grupoq.app.models.service.IDescuentoService;
import com.grupoq.app.models.service.IFacturaService;
import com.grupoq.app.models.service.INotificacionesService;
import com.grupoq.app.models.service.IProductoService;
import com.grupoq.app.models.service.IUsuarioService;
import com.grupoq.app.util.paginator.PageRender;
import com.grupoq.app.webservice.ProductosWB;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

@Controller
@SessionAttributes("facturacion")
@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	IFacturaService facturaservice;

	@Autowired
	IProductoService productoservice;

	@Autowired
	ICotizacionService cotizacionService;

	@Autowired
	ICarritoItemsService carritoitemsservice;

	@Autowired
	IUsuarioService usuarioService;

	@Autowired
	IClienteService clienteService;

	@Autowired
	IDescuentoService descuentoService;

	@Autowired
	INotificacionesService notificacionesService;

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_FACT", "ROLE_SELLER" })
	@RequestMapping(value = { "/listar", "/listar/{por}/{param}/{param2}/{opc}",
			"/listar/{por}/{param}" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			@PathVariable(value = "por", required = false) String por,
			@PathVariable(value = "param", required = false) String param,
			@PathVariable(value = "param2", required = false) String param2,
			@PathVariable(value = "opc", required = false) String opc, HttpServletRequest request,
			Authentication authentication) {
		Pageable pageRequest = PageRequest.of(page, 30);
		Page<Facturacion> facturacion = null;
//		String sPath= "listar";
		if (por != null) {
			if (por.equals("cliente")) {
				facturacion = facturaservice.findByClienteClienteNombreStartsWith(param, pageRequest);
				model.addAttribute("activePivot", false);
			}
			if (por.equals("usuario")) {
				facturacion = facturaservice.findByaACuentadeNombre(param, pageRequest);
				model.addAttribute("activePivot", false);
			}
			if (por.equals("fechas")) {
				try {
					System.out.print("Entro a 'fecha'\n");
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(param);
					Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(param2);
					if (opc.equals("vendedor")) {
						System.out.print("Entro a 'vendedor'\n");
						facturacion = (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_JEFEADM"))
								? facturaservice.findAllByFechaGroupBy(pageRequest, date1, date2)
								: null;
						model.addAttribute("activePivot", true);
					} else {

						Usuario user_temp = (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_JEFEADM"))
								? null
								: usuarioService.findByUsername(authentication.getName());

						facturacion = (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_JEFEADM"))
								? facturaservice.findAllByFecha(pageRequest, date1, date2)
								: facturaservice.findAllByFechaRestricted(pageRequest, date1, date2, user_temp.getId());

								
						System.out.print("Entro a 'admin' tamaño:" + facturacion.getSize() + "\n");
//						System.out.print("ID:" + facturacion.getNumberOfElements() + "\n");	
						model.addAttribute("activePivot", false);
					}
//				sPath = facturacion!=null ? "listar/fechas/"+param+"/"+param2 : "listar";  
				} catch (Exception e) {
					model.addAttribute("error", "Error en las fechas");
					return "/facturas/listar";
				}

			}

		} else {
			if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_FACT")
					|| request.isUserInRole("ROLE_JEFEADM")) {
				facturacion = facturaservice.findAll(pageRequest);
			} else {
				facturacion = facturaservice.findByaACuentadeNombre(authentication.getName(), pageRequest);
			}
			model.addAttribute("activePivot", false);
		}
		// si llega a estar vacio
		if (facturacion == null) {
			return "redirect:/factura/listar";
		}
		PageRender<Facturacion> pageRender = new PageRender<>("", facturacion);
		model.addAttribute("titulo", "Listado de Facturacion");
		model.addAttribute("facturas", facturacion);
		model.addAttribute("page", pageRender);
		return "/facturas/listar";
	}

//PARA CARRITO
	@Secured({ "ROLE_ADMIN", "ROLE_SELLER" })
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo2(Map<String, Object> model, RedirectAttributes flash) {
		CarritoItems carrito = new CarritoItems();
		model.put("carritoitems", carrito);
		model.put("titulo", "Ingreso de articulos");
		return "/facturas/form2";
	}

//ESTOS SI SON PARA FACTURA
	@Secured({ "ROLE_ADMIN", "ROLE_SELLER" })
	@RequestMapping(value = "/nuevof/{term}", method = RequestMethod.GET)
	public String nuevo(Map<String, Object> model, RedirectAttributes flash, @PathVariable Long term,
			HttpServletRequest request, Authentication authentication) {
		Facturacion facturacion = new Facturacion();
		Cotizacion cotizacion = new Cotizacion();
		cotizacion = cotizacionService.findby(term);
		model.put("titulo", "Remision");
		if (cotizacion == null) {
			model.put("facturacion", facturacion);
			flash.addFlashAttribute("error", "No existe ese id de cotizacion. Mostrando formulario vacio...");
			return "facturas/form";
		}
		if (!cotizacion.aprobado) {
			flash.addFlashAttribute("error", "Esa cotizacion a sido sometida a evaluacion...");
			flash.addFlashAttribute("success",
					"Recuerde: El codigo de su Cotizacion tiene le ID: " + cotizacion.getId());
			return "redirect:/factura/listar";
		}
		facturacion.setCotizacion(cotizacion);
		if (facturacion.getCotizacion() != null) {
			System.out.print(
					"La cotizacion no esta vacia, es mas este tiene el id \n" + facturacion.getCotizacion().getId());
		}

		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request,
				"");
		if (securityContext.isUserInRole("ROLE_SELLER")) {
			facturacion.setStatus(2);
		} else {
			facturacion.setStatus(1);
		}

		// llenando select a lo dundo
		model.put("fdePago", facturaservice.listFdp());
		model.put("cdePago", facturaservice.listCdp());
		model.put("tfactura", facturaservice.listTf());
		model.put("carteraclientes", clienteService.findAllByUsuario(authentication.getName()));
		// llenando selects a lo dundo
		model.put("facturacion", facturacion);
		return "/facturas/form";
	}

//ESTOS SI SON PARA FACTURA
	@Secured({ "ROLE_ADMIN", "ROLE_SELLER" })
	@RequestMapping(value = "/nuevof", method = RequestMethod.GET)
	public String nuevoSin(Map<String, Object> model, RedirectAttributes flash, Authentication authentication,
			HttpServletRequest request) {
		Facturacion facturacion = new Facturacion();
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request,
				"");
		if (securityContext.isUserInRole("ROLE_SELLER")) {
			facturacion.setStatus(2);
		} else {
			facturacion.setStatus(1);
		}
		model.put("facturacion", facturacion);

		// llenando select a lo dundo
		model.put("fdePago", facturaservice.listFdp());
		model.put("cdePago", facturaservice.listCdp());
		model.put("tfactura", facturaservice.listTf());
		model.put("carteraclientes", clienteService.findAllByUsuario(authentication.getName()));
		// llenando selects a lo dundo

		model.put("titulo", "Facturacion");
		return "/facturas/form";
	}

//guardar final de factura
	@Secured({ "ROLE_ADMIN", "ROLE_SELLER" })
	@RequestMapping(value = "/savefactura", method = RequestMethod.POST)
	public String guardarfactura(@Valid Facturacion facturacion, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status, Authentication authentication, HttpServletRequest request) {
		facturacion.setaCuentade(usuarioService.findByUsername(authentication.getName()));

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Facturacions");
			if (facturacion.getCotizacion() == null) {
				flash.addFlashAttribute("error",
						"El codigo de cotizacion no puede estar vacio, mostrando formulario vacio...");
//				return "redirect:/factura/nuevof";
				return "/facturas/form";
			}
//			return "redirect:/factura/nuevof/"+facturacion.getCotizacion().getId();

			return "/facturas/form";
		}
		Cotizacion cotizaciontemporal = cotizacionService.findby(facturacion.getCotizacion().getId());
		if (!cotizaciontemporal.aprobado) {
			return "redirect:/factura/listar";
		}
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request,
				"");

		if (securityContext.isUserInRole("ROLE_SELLER")) {
			facturacion.setStatus(2);
		} else {
			facturacion.setStatus(1);
		}
		String mensajeFlash = (facturacion.getId() != null) ? "facturacion editado con éxito!"
				: "Facturacion creada con éxito!";
		double totalParaFactura = 0;
		for (CarritoItems pro : cotizaciontemporal.getCarrito()) {
			Producto productogetStock = productoservice.findOne(pro.getProductos().getId());
			System.out.print("COMPARANDO LA CANTIDA PEDIDA: " + pro.getCantidad() + "\n LA CANTIDAD QUE HAY: "
					+ productogetStock.getStock());
			if (productogetStock.getStock() < pro.getCantidad()) {
				if (productogetStock.getStock() < 0) {
					productogetStock.setStock(productogetStock.getStock() - pro.getCantidad());
					model.addAttribute("error", "Stock insuficiente, se encuentra en numeros negativos");
					// aqui debo cambiar el estado del carrito segun el que tiene insuficientes en
					// stock
					System.out.print("ENTRANDO A LA CONDICION DE NUMEROS NEGATIVOS EN STOCK Y PONER SEST STATUS FALSE");
					pro.setStatus(false);

					// aqui
					carritoitemsservice.save(pro);
					// mando notificacion
					nuevaNotificacion("far fa-clock",
							"Producto " + pro.getProductos().getNombrep() + " en remision cambió de estado",
							"/cotizacion/ver/" + pro.getCotizacionid().getId(), "red");
					// fin
					facturacion.setStatus(3);
				} else {
					productogetStock.setStock(productogetStock.getStock() - pro.getCantidad());
					System.out.print("ENTRANDO A LA CONDICION DE NUMEROS NEGATIVOS EN STOCK Y PONER SEST STATUS TRUE");
					productoservice.save(productogetStock);
					facturacion.setStatus(3);
					pro.setStatus(false);
					carritoitemsservice.save(pro);
				}
			}

			else {
				productogetStock.setStock(productogetStock.getStock() - pro.getCantidad());
				productoservice.save(productogetStock);
			}
			// notificar cuando ya quedo en minimo
			if (productogetStock.getStock() - pro.getCantidad() <= productogetStock.getMinimo()) {
				nuevaNotificacion("fas fa-exclamation-triangle", productogetStock.getNombrep() + " en minimo!",
						"/producto/ver/" + productogetStock.getId(), "yellow");

			}
			// fin
			// poniendo total en factura entity

			totalParaFactura += (pro.getPrecio() * pro.getCantidad() * pro.getDescuento());
			System.out.print("\n" + totalParaFactura + "\n");
		}
		facturacion.setTotaRegistrado(totalParaFactura);
		// duplicaremos la cotizacin para despues no haber errores
		boolean cotiRepeated = facturaservice.cotizacionRepetida(facturacion.getCotizacion().getId()) > 0 ? true
				: false;
		System.out.print("El estado es " + cotiRepeated);
		if (cotiRepeated) {
			Cotizacion nCotizacion = new Cotizacion();
			nCotizacion.setFecha(new Date());
			cotizacionService.save(nCotizacion);
			for (CarritoItems c : cotizaciontemporal.getCarrito()) {
				CarritoItems carroNuevo = new CarritoItems();
				carroNuevo.setCantidad(c.getCantidad());
				carroNuevo.setCodigo(c.getCodigo());
				carroNuevo.setDescuento(c.getDescuento());
				carroNuevo.setMargen(c.getMargen());
				carroNuevo.setPrecio(c.getPrecio());
				carroNuevo.setStatus(c.isStatus());
				carroNuevo.setCotizacionid(nCotizacion);
				carroNuevo.setProductos(c.getProductos());
				carritoitemsservice.save(carroNuevo);
			}

			facturacion.setCotizacion(nCotizacion);

			// fin guardamos
		}

		facturaservice.save(facturacion);
		String url = "/factura/ver/" + facturacion.getId();
		nuevaNotificacion("fas fa-money-check-alt",
				"Nueva remision a nombre de " + facturacion.getaCuentade().getNombre(), url, "green");
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/factura/ver/" + facturacion.getId();
	}

	public void nuevaNotificacion(String icono, String nombre, String url, String color) {
		Notificaciones noti = new Notificaciones();
		noti.setFecha(new Date());
		noti.setIcono(icono);
		noti.setNombre(nombre);
		noti.setUrl(url);
		noti.setColor(color);
		notificacionesService.save(noti);
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
			productos.setMargen(veamos.getMargen());
			productos.setMarcanombre(veamos.getMarca().getNombrem());
			productos.setPresentacionnombre(veamos.getPresentacion().getUnidad());
			productos.setCodigo(veamos.getCodigo());
			list2.add(productos);

		}
		return list2;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_SELLER", "ROLE_JEFEADM" })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad,
			@RequestParam(name = "precio_id[]", required = false) double[] precio,
			@RequestParam(name = "tipoC", required = true) int tipoC,
			@RequestParam(name = "ivaC", required = true) int iva, Model model, RedirectAttributes flash,
			SessionStatus status) throws ParseException {

//		if (result.hasErrors()) {
//			model.addAttribute("titulo", "Inventariado");
//			return "/producto/listar";
//		}
		String mensajeFlash;
		/*
		 * para probar que el switch funciona if(tipoC==1) {
		 * System.out.print("\n"+tipoC+"\n"); return "redirect:/factura/listar"; }
		 */

		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Nuevo ingreso");
			model.addAttribute("error", "Error: No puede haber linea productos vacias!");
			return "/facturacion/nuevo";
		}
		Cotizacion cotizacion = new Cotizacion();

		cotizacion.setFecha(new Date());
		cotizacionService.save(cotizacion);
		// contador de cotizacion solo para cambiar estado
		boolean icotizacion = true;
		Random random = new Random();
		for (int i = 0; i < itemId.length; i++) {
			System.out.print("La cantidad es: " + cantidad[i] + " \n");
			int x = random.nextInt(900) + 100;
			CarritoItems carrito = new CarritoItems();
			Producto producto = productoservice.findOne(itemId[i]);
			// Buscamos si tiene descuento
			Descuento des = descuentoService.findFirstByProductoIdAndCantidadOrderByCantidadAsc(producto.getId(),
					cantidad[i]);
			if (des != null) {
				carrito.setDescuento((des.getPorcentaje()) / 100);
			} else {
				carrito.setDescuento(1);
			}
			carrito.setProductos(producto);
			// codigo generado
			String codigoGenerated = x + "xxx";

			carrito.setCodigo(codigoGenerated);
			// fin

			carrito.setCotizacionid(cotizacion);

			// margen default
			// evaluar si era un cotizacin evaluada
			// aqui pondremos si es menor a 15 entoncs sera avaluada
			double cSobrep = (producto.getPrecio() / precio[i]) * -1;
			double margenGenerado = (cSobrep * 100) + 100;

			if (margenGenerado <= 15) {

				if (margenGenerado != producto.getMargen()) {
					if (icotizacion) {
						Cotizacion cotizacintemporal = cotizacionService.findby(cotizacion.getId());
						cotizacintemporal.setAprobado(false);
						cotizacionService.save(cotizacintemporal);
						icotizacion = false;
					}
					carrito.setMargen(margenGenerado);
				} else {
					carrito.setMargen(producto.getMargen());
				}
			} else {
				// evaluar si era un cotizacin evaluada
				carrito.setMargen(margenGenerado);
				// margen default
			}
			carrito.setCantidad(cantidad[i]);
			carrito.setPrecio(precio[i]);
			String msjCo_Remi = (tipoC == 1) ? "Nueva cotizacion realizada" : "Nueva remesion realizada";
			nuevaNotificacion("fas fa-cart-plus", msjCo_Remi, "/cotizacion/ver/" + carrito.getCotizacionid().getId(),
					"blue");
			carritoitemsservice.save(carrito);

		}
		mensajeFlash = (tipoC == 1) ? " \nEl codigo de cotizacion es: " + cotizacion.getId()
				: "El codigo de la remision es: " + cotizacion.getId();
		String mensajeflash2 = (tipoC == 1)
				? "No olvidar el numero de la cotizacion para poder asi consultar y posteriormente usarla para una remision"
				: "Recuerde no borrar los datos con el campo en rojo de su cotizacion";
		flash.addFlashAttribute("error", mensajeflash2);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return (tipoC == 1) ? "redirect:/factura/listar/" : "redirect:/factura/nuevof/" + cotizacion.getId();

	}

//para ver el detalle de la FACTURA
	@Secured({ "ROLE_ADMIN", "ROLE_SELLER", "ROLE_JEFEADM", "ROLE_FACT" })
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Facturacion facturacion = facturaservice.fetchByIdWithClienteWithCarritoItemsWithProducto(id);
		if (facturacion == null) {
			flash.addFlashAttribute("error", "El ingreso con ese codigo no existe en la base de datos");
			return "redirect:/facturacion/listar";
		}
		model.put("activePivot", true);
		model.put("facturaciones", facturacion);
//		model.put("proveedor", facturacion.get(0).getProducto().getProveedor().getNombre());
//		model.put("fecha", facturacion.get(0).getFecha().toString());
		model.put("codigofa", id);
		model.put("titulo", "Detalle de factura # : " + id);
		return "/facturas/ver";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			try {
				facturaservice.delete(id);
				flash.addFlashAttribute("success", "Facturacion eliminada con éxito!");
			} catch (Exception e) {
				flash.addFlashAttribute("error",
						"El Facturacion posiblemente tiene registros enlazados, no se puede eliminar!");
				return "redirect:/factura/listar";
			}

		}
		return "redirect:/factura/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_FACT" })
	@RequestMapping(value = "/statusChange", method = RequestMethod.POST)
	public String finalizandoFactura(@RequestParam(name = "id") Long id, @RequestParam(name = "codigo") int codigo,
			RedirectAttributes flash) {
		if (id > 0 && codigo > 0) {
			try {
				Facturacion factura = facturaservice.findBy(id);
				factura.setStatus(1);
				factura.setCodigofactura(codigo);
				facturaservice.save(factura);
				flash.addFlashAttribute("success", "Operacion exitosa!");
				nuevaNotificacion("fas fa-file-alt", "Nueva remision finalizada!", "/factura/ver/" + factura.getId(),
						"green");
			} catch (Exception e) {
				flash.addFlashAttribute("error", "No se pudo cambiar el estado ni guardar la operacion!");
				return "redirect:/factura/listar";
			}

		}
		return "redirect:/factura/listar";
	}
}
