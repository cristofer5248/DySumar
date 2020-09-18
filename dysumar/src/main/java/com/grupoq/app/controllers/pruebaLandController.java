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
		if (opc == 1) {
			try {
				List<Facturacion> veamos = facturaService
						.findByCotizacionByCarritoItemsByIdByStatusWithoutProducto(Long.parseLong(valor));
				result="AUN NO ESTAN TODOS POSITIVOS, A ESPERAR";
				if(veamos.isEmpty()) {
					result = "Eliminar porque no hay ningun falso de mas";	
				}
				
			} catch (Exception e) {

				result += "Eliminar porque no hay ningun falso de mas";
			}

		}

		return result;
	}

}