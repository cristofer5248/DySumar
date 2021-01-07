package com.grupoq.app.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupoq.app.models.entity.Categoria;
import com.grupoq.app.models.entity.Giro;
import com.grupoq.app.models.entity.Inventario;
import com.grupoq.app.models.entity.Marca;
import com.grupoq.app.models.entity.Movimientos;
import com.grupoq.app.models.entity.Presentacion;
import com.grupoq.app.models.entity.Producto;
import com.grupoq.app.models.entity.Proveedor;
import com.grupoq.app.models.service.ICategoriaService;
import com.grupoq.app.models.service.IGiroService;
import com.grupoq.app.models.service.IInventarioService;
import com.grupoq.app.models.service.IMarcaService;
import com.grupoq.app.models.service.IMovimientosService;
import com.grupoq.app.models.service.IPresentacionService;
import com.grupoq.app.models.service.IProductoService;
import com.grupoq.app.models.service.IProveedorService;

@Controller
@RequestMapping("/excel")
@SessionAttributes("producto")
public class ExcelController {

	@Autowired
	private ICategoriaService categoryservice;

	@Autowired
	private IProductoService productoservice;

	@Autowired
	private IMarcaService marcaservice;

	@Autowired
	private IPresentacionService presentacionservice;

	@Autowired
	private IProveedorService proveedorservice;

	@Autowired
	private IGiroService giroservice;

	@Autowired
	private IInventarioService inventarioservice;

	@Autowired
	private IMovimientosService movimientoservice;

	@PostMapping("/excelimport")
	public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile,
			@RequestParam(name = "replace_", required = false) int replace_, RedirectAttributes flash,
			Authentication authentication) throws IOException {

//		List<Producto> productos_xls = new ArrayList<Producto>();
		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);

		// giro default
		Giro giro = (giroservice.findByNombre("No disponible") != null) ? giroservice.findByNombre_("No disponible")
				: giroDefault();

		int celdanumero = 0;
		// preparando movimientos e inventario
		Movimientos movimiento = new Movimientos();
		movimientoservice.save(movimiento);

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			Inventario inventario = new Inventario();
			inventario.setMovimientos(movimiento);// desde aqui vamos a preparar un solo carrito para un solo
													// movimiento.

			celdanumero = i;
			// libro 1 - recorredor i
			XSSFRow row = worksheet.getRow(i);
			// creando un producto vacio desde 0
			Producto producto = new Producto();

			// metiendo datos no foraneos
			String bodega, nombreproducto;
			Date fecha;
			double costo, margen;
			int stock;

			try {

				bodega = row.getCell(3).getStringCellValue();
				producto.setBodega(bodega);

				costo = row.getCell(2).getNumericCellValue();
				producto.setPrecio(costo);

				fecha = row.getCell(4).getDateCellValue();
				producto.setFecha(fecha);

				margen = row.getCell(7).getNumericCellValue();
				producto.setMargen(margen);

				stock = (int) row.getCell(18).getNumericCellValue();
				producto.setStock(stock);

				nombreproducto = row.getCell(1).getStringCellValue();
				producto.setNombrep(nombreproducto);

			} catch (NullPointerException e) {
				flash.addFlashAttribute("error",
						"Un dato esta vacio o tiene un formato incorrecto, revisar la celda de la fila " + celdanumero);
				System.out.print("null weon");
				return "redirect:/producto/nuevo";
			}

			try {
				// proceso de verificacion de foraneas
				DataFormatter formatter = new DataFormatter();
				String codigopro = formatter.formatCellValue(row.getCell(0));
				System.out.print("\ncodigo es antes solo agarrado: " + codigopro + "\n");
				String categoriString = row.getCell(5).getStringCellValue();
				String marcastring = row.getCell(6).getStringCellValue();
				String presentacionstring = row.getCell(8).getStringCellValue();

				String proveedorstring = row.getCell(10).getStringCellValue();
				Long giroproveedor = Long.parseLong(row.getCell(12).getRawValue().toString());
				if ((row.getCell(12).getRawValue().toString()) == null) {
					giroproveedor = Long.parseLong("13");
				}
				// si es el codigo ya existe
				codigopro = (productoservice.findByCodigo(codigopro) == null) ? codigopro : null;
				System.out.print("\ncodigo es: " + codigopro + "\n");
				if (codigopro == null && replace_ == 0) {
					flash.addFlashAttribute("error", "El codigo de producto de la fila " + celdanumero + " ya existe");
					return "redirect:/producto/nuevo";
				}
				if (codigopro == null && replace_ == 1) {

					productoReplace(formatter.formatCellValue(row.getCell(0)), authentication, stock, movimiento);
					flash.addFlashAttribute("success",
							"Ingreso de productos con exitosa, numero de productos: " + celdanumero++);

				} else {
					producto.setCodigo(formatter.formatCellValue(row.getCell(0)));
					// vemos si esta o no la categoria agregada antes.
					Categoria categoria_xls = categoryservice.findByNombre(categoriString);
					if (categoria_xls != null) {
						producto.setCategoria(categoria_xls);
						System.out.print("\n" + categoriString + " encontrada!");
					} else {
						producto.setCategoria(insertCategory(categoriString));
						System.out.print("\nIngresada nueva categoria: " + categoriString);
					}

					Marca marca_xls = marcaservice.findByNombre(marcastring);
					if (marca_xls != null) {
						producto.setMarca(marca_xls);
						System.out.print("\n" + marcastring + " encontrada!");
					} else {
						producto.setMarca(insertMarca(marcastring));
						System.out.print("\nIngresada nueva marca: " + marcastring);
					}

					// presentacion
					Presentacion presentacion_xls = presentacionservice.findByDetalle(presentacionstring);
					System.out.print("\nEL NOMBRE DE LA PRESENTACION ES: " + presentacionstring);
					if (presentacion_xls != null) {
						producto.setPresentacion(presentacion_xls);
						System.out.print("\n" + presentacionstring + " encontrada!");
					} else {
						producto.setPresentacion(
								insertPresentacion(presentacionstring, row.getCell(9).getStringCellValue()));
						System.out.print("\nIngresada nueva presentacion: " + presentacionstring);
					}
					// proveedor
					Proveedor proveedor_xls = proveedorservice.findByNombre(proveedorstring);
					if (proveedor_xls != null) {
						producto.setProveedor(proveedor_xls);
						System.out.print("\n" + proveedorstring + " encontrada!");
					} else {
						// datos del proveedor para insertar
						String codigoproveedor = row.getCell(11).getStringCellValue();
						String telefono = row.getCell(13).getRawValue();
						String email = row.getCell(14).getStringCellValue();
						String nit = row.getCell(15).getStringCellValue();
						String direccion = row.getCell(16).getStringCellValue();
						String razonsocial = row.getCell(17).getStringCellValue();

						// asunto de giro ya registrado
						Giro giroproveedor_xls = (giroservice.findBy(giroproveedor)) == null ? giro
								: giroservice.findBy(giroproveedor);
						// listo

						producto.setProveedor(insertProveedor(proveedorstring, codigoproveedor, giroproveedor_xls,
								telefono, email, nit, direccion, razonsocial));
						System.out.print("\nIngresado nuevo proveedor: " + proveedorstring);
					}
					// guardando aqui el producto e inventario
					productoservice.save(producto);
					inventario.setFecha(new Date());
					inventario.setComentario("Ingresado desde un archivo excel");
					inventario.setProducto(producto);
					inventario.setCodigoProveedor("EXCEL");
					inventario.setZaNombrede(authentication.getName());
					inventario.setStock(stock);
					inventarioservice.save(inventario);
					workbook.close();
					flash.addFlashAttribute("success", "Insercion con exito!, numero de productos: " + celdanumero++);
				}
			} catch (Exception e) {
				e.printStackTrace();
				flash.addFlashAttribute("error", "Hay un error en su archivo, ultima celda revisa: " + celdanumero++);

			}
		}

		return "redirect:/producto/nuevo";
	}

	public Categoria insertCategory(String nombre) {
		Categoria categoria = new Categoria();
		categoria.setNombre(nombre);
		categoryservice.save(categoria);
		return categoria;
	}

	public Marca insertMarca(String nombre) {
		Marca marca = new Marca();
		marca.setNombrem(nombre);
		marcaservice.save(marca);
		return marca;
	}

	public Presentacion insertPresentacion(String nombre, String unidad) {
		String unidadlenght = (unidad.length() > 5) ? unidad.substring(0, 4) : unidad;

		Presentacion presentacion = new Presentacion();
		presentacion.setDetalle(nombre);
		presentacion.setUnidad(unidadlenght);// solo de 5

		presentacionservice.save(presentacion);
		return presentacion;
	}

	public Proveedor insertProveedor(String nombre, String codigoproveedor, Giro giro, String telefono, String email,
			String nit, String direccion, String razonsocial) {
		Proveedor proveedor = new Proveedor();
		proveedor.setNombre(nombre);
		proveedor.setCodigoP(codigoproveedor);
		proveedor.setGiro(giro);
		proveedor.setTelefono(telefono);
		proveedor.setEmail(email);
		proveedor.setTelefono(telefono);
		proveedor.setNit(nit);
		proveedor.setDireccion(direccion);
		proveedor.setRazonsocial(razonsocial);
		proveedorservice.save(proveedor);
		return proveedor;
	}

	public Giro giroDefault() {
		Giro giro_temp = new Giro();
		giro_temp.setDetalles("No disponible");
		giroservice.save(giro_temp);
		return giro_temp;
	}

	public void productoReplace(String codigo_replace, Authentication authentication, int stock,
			Movimientos movimiento_replace) {

		Producto producto_replace = productoservice.findByCodigo(codigo_replace);
		if (inventarioservice.findByProductoById(producto_replace.getId()).size() < 1) {
			System.out.print("HE ENTRADO A IF DE NO INGRESO FANTASMA");
			Inventario inventario_replace__ = new Inventario();
			Movimientos extramove = new Movimientos();
			movimientoservice.save(extramove);
			inventario_replace__.setMovimientos(extramove);
			inventario_replace__.setCodigoProveedor("EXCEL...");
			inventario_replace__.setComentario(
					"Este es un ingreso que no habia sido mostrado, ingreso por : " + authentication.getName());
			inventario_replace__.setZaNombrede(authentication.getName());
			inventario_replace__.setFecha(new Date());
			inventario_replace__.setProducto(producto_replace);
			inventario_replace__.setStock(producto_replace.getStock());
			inventarioservice.save(inventario_replace__);
		}

		Inventario inventario_replace = new Inventario();
		inventario_replace.setMovimientos(movimiento_replace);
		inventario_replace.setCodigoProveedor("DESDE EXCEL...");
		inventario_replace.setComentario("Este ingreso fue desde un archivo de excel por " + authentication.getName());
		inventario_replace.setZaNombrede(authentication.getName());
		inventario_replace.setFecha(new Date());
		inventario_replace.setProducto(producto_replace);
		inventario_replace.setStock(stock);
		inventarioservice.save(inventario_replace);

		producto_replace.setStock(sumarStocks(producto_replace.getId()));
		productoservice.save(producto_replace);

		//
	}

	public int sumarStocks(Long codigo_sumstock) {
		List<String> totalstock = inventarioservice.sumarStock(codigo_sumstock);
		return Integer.parseInt(totalstock.get(0).toString());
	}

}
