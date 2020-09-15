package com.grupoq.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.grupoq.app.models.entity.Giro;
import com.grupoq.app.models.service.IGiroService;

@RestController
public class GiroRestController {
	@Autowired
	private IGiroService giroService;
	
	@RequestMapping(value = "/saveExpress/{nombre}", method = {RequestMethod.GET}, produces = { "application/json" })
	@ResponseBody
	public String saveExpress(@PathVariable(value = "nombre", required = true) String nombre) {
		Giro giro = new Giro();
		giro.setDetalles(nombre);
		giroService.save(giro);		
		return "exito";
		
	}
	@RequestMapping(value = "/nada", method= RequestMethod.GET)	
	public String nada(String nada) {
		return "nada";
	}
	

}
