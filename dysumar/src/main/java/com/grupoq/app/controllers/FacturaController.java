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
import com.grupoq.app.models.entity.Inventario;
import com.grupoq.app.models.entity.NotadeCredito;
import com.grupoq.app.models.entity.Notificaciones;
import com.grupoq.app.models.entity.Producto;
import com.grupoq.app.models.entity.Usuario;
import com.grupoq.app.models.service.ICarritoItemsService;
import com.grupoq.app.models.service.IClienteService;
import com.grupoq.app.models.service.ICotizacionService;
import com.grupoq.app.models.service.IDescuentoService;
import com.grupoq.app.models.service.IFacturaService;
import com.grupoq.app.models.service.IInventarioService;
import com.grupoq.app.models.service.INotadeCreditoService;
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

	@Autowired
	IInventarioService inventarioservice;

	@Autowired
	INotadeCreditoService notadecreditoservice;

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
		Pageable pageRequest = (por != null || param != null || opc != null) ? Pageable.unpaged()
				: PageRequest.of(page, 30);
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

						model.addAttribute("activePivot", true);
					}
//				sPath = facturacion!=null ? "listar/fechas/"+param+"/"+param2 : "listar";  
				} catch (Exception e) {
					e.printStackTrace();
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
		PageRender<Facturacion> pageRender = new PageRender<>("listar", facturacion);
		model.addAttribute("titulo", "Listado de facturas y remisiones");
		model.addAttribute("facturas", facturacion);
		model.addAttribute("page", pageRender);

		return "/facturas/listar";
	}

	// PARA CARRITO
	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_FACT" })
	@RequestMapping(value = "/checkstock/{term}", method = RequestMethod.GET)
	public String evaluarstock(Map<String, Object> model, RedirectAttributes flash, @PathVariable Long term) {
		String productosmalos = "";
		try {
			Facturacion facturacion = facturaservice.findBy(term);
			List<CarritoItems> carrito = facturacion.getCotizacion().getCarrito();

			System.out.print("\nRecorriendo: \n");
			boolean cambiarStatus = true;

			for (CarritoItems caObj : carrito) {
				if (caObj.getProductos().getStock() < 0) {
					cambiarStatus = false;
					productosmalos += "(" + caObj.getProductos().getNombrep() + " )- ";
				}
			}
			if (cambiarStatus) {
				facturacion.setStatus(2);
				facturaservice.save(facturacion);
				flash.addFlashAttribute("success", "Se ha actualizado el estado de la remision");
			} else {

				flash.addFlashAttribute("error", productosmalos + " Se encuentran en stock negativo");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "redirect:/factura/ver/" + term;
	}

	// PARA CARRITO
	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_FACT" })
	@RequestMapping(value = "/notaderemision/{term}", method = RequestMethod.GET)
	public String rm(Map<String, Object> model, RedirectAttributes flash, @PathVariable Long term) {
		String productosmalos = "";
		try {
			Facturacion facturacion = facturaservice.findBy(term);

			System.out.print("\nRecorriendo: \n");
			boolean cambiarStatus = true;
			facturacion.setStatus(4);
			if (cambiarStatus) {
				facturaservice.save(facturacion);
				flash.addFlashAttribute("success", "Se ha actualizado el estado de la remision");
			} else {

				flash.addFlashAttribute("error", productosmalos + " No se pudo cambiar el estado");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "redirect:/factura/ver/" + term;
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
	@RequestMapping(value = { "/nuevof" })
	public String nuevoSin(Map<String, Object> model, RedirectAttributes flash, Authentication authentication,
			HttpServletRequest request, @RequestParam(name = "id", required = false) String id) {
		Facturacion facturacion = new Facturacion();
//		facturacion = (id != null) ? facturaservice.findBy(id) : facturacion;
		System.out.print("\n EL ID DE EDITAR ES: " + id);
		facturacion = facturaservice.findBy(Long.parseLong(id));
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
	@Secured({ "ROLE_ADMIN", "ROLE_SELLER", "ROLE_FACT" })
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
			model.addAttribute("fdePago", facturaservice.listFdp());
			model.addAttribute("cdePago", facturaservice.listCdp());
			model.addAttribute("tfactura", facturaservice.listTf());
			model.addAttribute("carteraclientes", clienteService.findAllByUsuario(authentication.getName()));
			return "/facturas/form";
		}

		// solo para editar solo guardamos
		if (facturacion.getId() != null) {
			facturaservice.save(facturacion);
			flash.addFlashAttribute("success", "Edicion correcta");
			return "redirect:/factura/ver/" + facturacion.getId();
		} else {

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
		String mensajeFlash = (facturacion.getId() != null) ? "factura editada con éxito!"
				: "Facturacion creada con éxito!";
		double totalParaFactura = 0;
		double totalsiniva = 0;
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

			totalParaFactura += (pro.getPrecio() * pro.getCantidad() * pro.getDescuento() * 1.13);

			totalsiniva += pro.getPrecio() * pro.getCantidad();

			System.out.print("\n" + totalParaFactura + "\n");
		}

		facturacion.setTotaRegistrado(totalParaFactura > 113 && facturacion.getCliente().getCliente().getAgente()
				? (totalParaFactura - (totalsiniva * 0.01))
				: totalParaFactura);
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
		if (list1.isEmpty()) {
			int lastSpaceIndex = term.lastIndexOf(" ");
			String term2 = term.substring(lastSpaceIndex + 1, term.length());
			term = term.substring(0, lastSpaceIndex);
			System.out.print("\n PRODUCTO: " + term + " MARCA: " + term2 + " indexlast " + lastSpaceIndex);
			list1 = productoservice.findByNombrepYMarca(term, term2);
		}
		for (Producto veamos : list1) {
			ProductosWB productos = new ProductosWB();
			productos.setId(veamos.getId());
			productos.setCategoriaid(veamos.getCategoria().getId());
			productos.setProveedornombre(veamos.getProveedor().getNombre());
			productos.setPrecio(veamos.getPrecio());
			productos.setNombrep(veamos.getNombrep());
			productos.setMargen(veamos.getMargen());
			productos.setMarcanombre(veamos.getMarca().getNombrem());
			productos.setPresentacionnombre(veamos.getPresentacion().getDetalle());
			productos.setCodigo(veamos.getCodigo());
			productos.setStock(veamos.getStock());
			list2.add(productos);

		}
		return list2;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_FACT", "ROLE_JEFEADM", "ROLE_INV" })
	@RequestMapping(value = "/busquedacodigo/{codigodoc}", method = { RequestMethod.GET }, produces = {
			"application/json" })
	public @ResponseBody Boolean busquedacodigo(@PathVariable(value = "codigodoc", required = true) String codigodoc,
			Model model, RedirectAttributes flash, SessionStatus status) throws ParseException {

		Boolean resultado = false;
		try {

			Facturacion facturacion = facturaservice.findByCodigofacturaStatus(codigodoc);
			Inventario inventario = new Inventario();
			if (facturacion == null) {
				resultado = false;
				inventario = inventarioservice.findByCodigoProveedorAndStatus(codigodoc);
//				inventario = (inventario == null) ? false : true;
				resultado = (inventario == null) ? true : false;
				System.out.print("el resultado es: " + resultado);
			} else {
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return resultado;

	}

	@Secured({ "ROLE_ADMIN", "ROLE_FACT", "ROLE_JEFEADM", "ROLE_INV" })
	@RequestMapping(value = "/savendc", method = RequestMethod.POST)
	public String guardarnotadecredito(@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad,
			@RequestParam(name = "precio_id[]", required = false) double[] precio,
			@RequestParam(name = "tipoC", required = true) int tipoC,
			@RequestParam(name = "municipio", required = true) String municipio,
			@RequestParam(name = "departamento", required = true) String departamento,
			@RequestParam(name = "codigodoc", required = true) String codigodoc, Authentication authentication,
			Model model, RedirectAttributes flash, SessionStatus status) throws ParseException {

		String dondevoy = "/";
		Facturacion facturacion = new Facturacion();
		try {
			facturacion = facturaservice.findByCodigofactura(codigodoc);
		} catch (Exception e) {
			flash.addFlashAttribute("error",
					"Es posible que el codigo de documento ingresado se encuentre repetido, vuelve a revisar los datos ingresados y vuelve a intentarlo.");
		}

		NotadeCredito notita = new NotadeCredito();
		Cotizacion coti = new Cotizacion();
		try {
			if (facturacion == null) {
				Inventario inventario = inventarioservice.findByCodigoProveedor(codigodoc);
				if (inventario == null) {
					flash.addFlashAttribute("error",
							"Revisa la integridad de los datos si de verdad corresponden, si eso no funciona contacta a soporte tecnico");
				} else {
					flash.addFlashAttribute("success", anularinventario(inventario, tipoC, itemId, cantidad, precio,
							departamento, municipio, coti));
					dondevoy = "/inventario/listar";
					String clientestring = inventario.getProducto().getProveedor().getNombre();
					clientestring = (clientestring.length() <= 7) ? clientestring + "     " : clientestring;
					notita.setCliente(clientestring);
					notita.setCodigodoc(codigodoc);
					notita.setMunicipio(municipio);
					notita.setDepartamento(departamento);
					notita.setFecha(new Date());
					notita.setDuinit(inventario.getProducto().getProveedor().getNit());
					notita.setGiro(inventario.getProducto().getProveedor().getGiro());
					notita.setCdpago("contado");
					notita.setNtr("A cuenta de " + authentication.getName());
					notita.setCarrito(coti);
					notadecreditoservice.save(notita);

				}
			} else {
				dondevoy = "/factura/listar";
				flash.addFlashAttribute("success",
						anularfactura(facturacion, tipoC, itemId, cantidad, precio, departamento, municipio, coti));
				notita.setCliente(facturacion.getCliente().getCliente().getNombre());
				notita.setCodigodoc(codigodoc);
				notita.setMunicipio(municipio);
				notita.setDepartamento(departamento);
				notita.setFecha(new Date());
				notita.setDuinit(facturacion.getCliente().getCliente().getDui());
				notita.setGiro(facturacion.getCliente().getCliente().getGiro());
				notita.setCdpago(facturacion.getCondicionesDPago().getNombre());
				notita.setNtr("A cuenta de " + authentication.getName());
				notita.setCarrito(coti);
				notadecreditoservice.save(notita);

			}

		} catch (Exception e) {
			e.printStackTrace();
			flash.addFlashAttribute("error", "Error interno, reportar a soporte tecnico");
			return "redirect:" + dondevoy;
		}

		return "redirect:" + dondevoy;

	}

	public String anularfactura(Facturacion facturacion, int anular, Long[] itemId, Integer[] cantidad, double[] precio,
			String departamento, String municipio, Cotizacion coti) {
		String mensaje = "";
		coti.setAprobado(true);
		coti.setFecha(new Date());
		cotizacionService.save(coti);
		for (int i = 0; i < itemId.length; i++) {
			Producto producto_ = productoservice.findOne(itemId[i]);
			Random random = new Random();
			int x = random.nextInt(900) + 100;
			CarritoItems carrito = new CarritoItems();
			carrito.setCantidad(cantidad[i]);
			carrito.setPrecio(precio[i]);
			String codigoGenerated = x + "xxx";
			carrito.setCodigo(codigoGenerated);
			carrito.setProductos(productoservice.findOne(itemId[i]));
			carrito.setCotizacionid(coti);
			carritoitemsservice.save(carrito);
			producto_.setStock(producto_.getStock() + (cantidad[i]));
			productoservice.save(producto_);
			nuevaNotificacion(((cantidad[i] <= 0) ? "fas fa-step-backward" : "fas fa-fast-forward"),
					((cantidad[i] <= 0) ? "Devolucion de " : "Se añadio ") + producto_.getNombrep()
							+ " a una nota de credito",
					"/notadecredito/listar", "orange");

		}
		if (anular > 0) {
			facturacion.setStatus(5);
			facturacion.setDetalles(
					"NDC: ESTA FACTURA/CREDITO FISCAL tiene una nota de credito. " + facturacion.getDetalles());
			mensaje += "Factura con ID " + facturacion.getCodigofactura() + " anulada";

		} else {
			facturacion.setDetalles(
					"NDC: ESTA FACTURA/CREDITO FISCAL tiene una nota de credito. " + facturacion.getDetalles());
			mensaje += "Factura con ID " + facturacion.getCodigofactura() + " no anulada";
		}
		facturaservice.save(facturacion);
		return mensaje;
	}

	public String anularinventario(Inventario inventario, int anular, Long[] itemId, Integer[] cantidad,
			double[] precio, String departamento, String municipio, Cotizacion coti) {
		String mensaje = "";
		coti.setAprobado(true);
		coti.setFecha(new Date());
		cotizacionService.save(coti);
		for (int i = 0; i < itemId.length; i++) {
			Producto producto_ = productoservice.findOne(itemId[i]);
			Random random = new Random();
			int x = random.nextInt(900) + 100;
			CarritoItems carrito = new CarritoItems();
			carrito.setCantidad(cantidad[i]);
			carrito.setPrecio(precio[i]);
			String codigoGenerated = x + "xxx";
			carrito.setCodigo(codigoGenerated);
			carrito.setProductos(productoservice.findOne(itemId[i]));
			carrito.setCotizacionid(coti);
			carritoitemsservice.save(carrito);
			producto_.setStock(producto_.getStock() + (cantidad[i]));
			productoservice.save(producto_);
			nuevaNotificacion(((cantidad[i] <= 0) ? "fas fa-step-backward" : "fas fa-fast-forward"),
					((cantidad[i] <= 0) ? "Devolucion de " : "Se añadio ") + producto_.getNombrep()
							+ " a una nota de credito",
					"/notadecredito/listar", "orange");
		}
		if (anular > 0) {
			inventario.setEstado(false);
			inventario.setComentario("Este registro tiene una nota de credito " + inventario.getComentario());
			mensaje += "Inventario con ID " + inventario.getCodigoProveedor() + " anulada";
		} else {
			mensaje += "Inventario con ID " + inventario.getCodigoProveedor() + " no anulada";
		}
		inventarioservice.save(inventario);
		return mensaje;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_SELLER", "ROLE_JEFEADM" })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad,
			@RequestParam(name = "precio_id[]", required = false) double[] precio,
			@RequestParam(name = "tipoC", required = true) int tipoC,
			@RequestParam(name = "ivaC", required = true) int iva, Model model, RedirectAttributes flash,
			SessionStatus status) throws ParseException {

		if (itemId == null) {
			model.addAttribute("titulo", "Realizar una remision");
			flash.addAttribute("error", "La remision no puede estar vacia");
			return "redirect:/factura/nuevo";
		}
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
			double cSobrep = (producto.getPrecio() / ((precio[i]) * 1.13)) * -1;
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
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash,
			Authentication auth, HttpServletRequest request) {

		Facturacion facturacion = facturaservice.fetchByIdWithClienteWithCarritoItemsWithProducto(id);
		if (facturacion == null) {
			flash.addFlashAttribute("error", "El ingreso con ese codigo no existe en la base de datos");
			return "redirect:/facturacion/listar";
		}
		System.out.print("\n usuario1 " + auth.getName());
		System.out.print("\n usuario2 " + facturacion.getaCuentade().getUsername());

		if (!facturacion.getaCuentade().getUsername().equals(auth.getName()) && !(request.isUserInRole("ROLE_ADMIN")
				|| request.isUserInRole("ROLE_JEFEADM") || request.isUserInRole("ROLE_FACT"))) {
			flash.addFlashAttribute("error", "La factura que intentas ver no te corresponde porque no es tuya.");
			return "redirect:/facturacion/listar";
		}

		String carritoid = facturacion.getCotizacion().getId().toString();
		model.put("activePivot", true);
		model.put("facturaciones", facturacion);
//		model.put("proveedor", facturacion.get(0).getProducto().getProveedor().getNombre());
//		model.put("fecha", facturacion.get(0).getFecha().toString());
		model.put("codigofa", id);
		model.put("titulo", "Detalle de factura # : " + id);
		model.put("carritoid", carritoid);
		model.put("correlativo", facturacion.getCodigofactura());

		return "/facturas/ver";
	}

	// para ver el detalle de la FACTURA
	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_FACT" })
	@RequestMapping(value = "/cambiarcosto/", method = RequestMethod.POST)
	public String editarcarrito(@RequestParam(name = "id") Long id,
			@RequestParam(name = "codigoItemCarrito") Long codigoItemCarrito,
			@RequestParam(name = "costoN") double costoN, Map<String, Object> model, RedirectAttributes flash,
			Authentication auth, HttpServletRequest request) {

		System.out.print("\n VEAMOS EL codigoItemCarrito :" + codigoItemCarrito + " costo " + costoN + " el id: " + id);
		String pathredirect = "/factura/listar/";
		boolean cambiarcosto = false;
		double totalnuevo = 0.0;
		if (id > 0 && costoN > 0) {
			try {

				Facturacion factura = facturaservice.findBy(id);

				for (CarritoItems carrito : factura.getCotizacion().getCarrito()) {
					if (carrito.getId().equals(codigoItemCarrito)) {

						flash.addFlashAttribute("success", "Operacion exitosa!");
						nuevaNotificacion("fas fa-file-alt", "El costo de un producto en una factura ha sido cambiado!",
								"/factura/ver/" + factura.getId(), "gray");
						pathredirect = "/cotizacion/ver/" + factura.getCotizacion().getId().toString() + "/"
								+ factura.getId();
						cambiarcosto = true;
						double preciosin = (costoN * carrito.getCantidad());
//						double descuento = carrito.getDescuento() / 100;
						// el descuento es sin iva?
//						descuento = carrito.getPrecio()*descuento;
						totalnuevo += (preciosin * 1.13);
						double margennuevo = ((carrito.getProductos().getPrecio() / (costoN * 1.13)) * -1 * 100) + 100;
						carrito.setMargen(margennuevo);
						carrito.setPrecio(costoN);
						carritoitemsservice.save(carrito);

					} else {
						totalnuevo += carrito.getPrecio() * 1.13 * carrito.getCantidad();

					}

				}
				if (cambiarcosto) {
					System.out.print("\ncuantas veces paso" + totalnuevo);
					double totalnuevosin = totalnuevo / 1.13;
					totalnuevo = (totalnuevosin > 113.00 & factura.getCliente().getCliente().getAgente())
							? totalnuevo - (totalnuevosin * 0.01)
							: totalnuevo;
					factura.setTotaRegistrado(totalnuevo);
					facturaservice.save(factura);
				}

			} catch (Exception e) {
				e.printStackTrace();
				flash.addFlashAttribute("error", "Error cambiando el costo!");
				return "redirect:" + pathredirect;
			}

		} else {
			flash.addFlashAttribute("error", "Error cambiando el costo!");
			return "redirect:" + pathredirect;
		}
		return "redirect:" + pathredirect;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_FACT" })
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash, Authentication auth) {

		if (id > 0) {
			try {
				flash.addFlashAttribute("success", "Facturacion eliminada con éxito!");
				// aqui le ponemos reversa de factura hasta productos/inventarios
				Facturacion fac = facturaservice.findBy(id);
				String coddoc = fac.getCodigofactura();
				for (CarritoItems carrito : fac.getCotizacion().getCarrito()) {
					Producto pro = productoservice.findOne(carrito.getProductos().getId());
					System.out.print("Elemento: " + pro.getNombrep() + "\n" + "Cantidad: " + carrito.getCantidad());
					pro.setStock(pro.getStock() + carrito.getCantidad());
					productoservice.save(pro);
				}
				facturaservice.delete(id);
				nuevaNotificacion("fas fa-trash-alt", "La factura con el id " + id + " y correlativo " + coddoc
						+ " ha sido eliminado por " + auth.getName(), "#", "red");

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
	public String finalizandoFactura(@RequestParam(name = "id") Long id, @RequestParam(name = "codigo") String codigo,
			@RequestParam(name = "ndr") int ndr, RedirectAttributes flash) {
		System.out.print("\n VEAMOS EL ID :" + codigo);
		String redirecpage = "redirect:/factura/listar/";
		if (id > 0 && codigo != null) {
			try {
				Facturacion factura = facturaservice.findBy(id);

				factura.setStatus(ndr == 2 ? 4 : 1);
				factura.setCodigofactura(codigo);
				facturaservice.save(factura);
				flash.addFlashAttribute("success", "Operacion exitosa!");
				nuevaNotificacion("fas fa-file-alt", "Nueva remision finalizada!", "/factura/ver/" + factura.getId(),
						"green");
				redirecpage = "redirect:/factura/ver/" + factura.getId();
			} catch (Exception e) {
				flash.addFlashAttribute("error", "No se pudo cambiar el estado ni guardar la operacion!");
				return redirecpage;
			}

		}
		return redirecpage;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_SELLER" })
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String prueba(Map<String, Object> model, RedirectAttributes flash, Authentication authentication,
			HttpServletRequest request, @PathVariable(name = "id", required = true) Long id) {
		Facturacion facturacion = new Facturacion();
		facturacion = facturaservice.findBy(id);
		model.put("facturacion", facturacion);
		// llenando select a lo dundo
		model.put("fdePago", facturaservice.listFdp());
		model.put("cdePago", facturaservice.listCdp());
		model.put("tfactura", facturaservice.listTf());

		model.put("carteraclientes",
				request.isUserInRole("ROLE_SELLER") ? clienteService.findAllByUsuario(authentication.getName())
						: clienteService.findAll());
		// llenando selects a lo dundo

		model.put("titulo", "Facturacion #" + id);
		return "/facturas/form";
	}
}
