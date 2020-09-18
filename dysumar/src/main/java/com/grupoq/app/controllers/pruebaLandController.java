package com.grupoq.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.grupoq.app.models.entity.CarritoItems;
import com.grupoq.app.models.entity.Facturacion;
import com.grupoq.app.models.entity.Giro;
import com.grupoq.app.models.service.IFacturaService;
import com.grupoq.app.models.service.IGiroService;

@RequestMapping("/adminzone")
@RestController
public class pruebaLandController {
	@Autowired
	private IGiroService giroService;

	@Autowired
	private IFacturaService facturaService;

	@RequestMapping(value = "/saveExpress/{nombre}", method = { RequestMethod.GET }, produces = { "application/json" })
	@ResponseBody
	public String saveExpress(@PathVariable(value = "nombre", required = true) String nombre) {
		Giro giro = new Giro();
		giro.setDetalles(nombre);
		giroService.save(giro);
		return "exito";

	}

	@RequestMapping(value = "/facturasAllstatus/{opc}/{valor}", method = RequestMethod.GET)
	public String verSQLFacturaStatuses(@PathVariable(value = "opc") int opc,
			@PathVariable(value = "valor") String valor) {
		String result = "";
		int anyCounter = 0;
		if (opc == 1) {
			try {
				List<Facturacion> veamos = facturaService.findByCotizacionByCarritoItemsByIdByStatusWithoutProducto();
				for (Facturacion factura : veamos) {
					result = "El size de carrito es: " + factura.getCotizacion().getCarrito().size() + "<br/>";
					result += "Datos en el carrito:<br/>";
					for (CarritoItems carro : factura.getCotizacion().getCarrito()) {
						result += "Estatus: " + carro.isStatus() + "<br/>";
						if (!carro.isStatus()) {
							veamos.remove(anyCounter);

						}
						anyCounter++;
					}

				}

				for (Facturacion factura : veamos) {

					for (CarritoItems carro : factura.getCotizacion().getCarrito()) {

						result += "id: " + carro.getId() + "<br/>";
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				result += "vacio";
			}

		}

		return result;
	}

}