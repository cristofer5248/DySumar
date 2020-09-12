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
	
	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM", "ROLE_SELLER" })
	@RequestMapping(value = "/saveExpress", method = RequestMethod.POST)	
	public String saveExpress(@RequestParam String nombreGiro) {
		Giro giro = new Giro();
		giro.setDetalles(nombreGiro);
		giroService.save(giro);		
		return "exito";
		
	}
	

}
