package com.grupoq.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.grupoq.app.models.entity.Giro;
import com.grupoq.app.models.service.IGiroService;

@SessionAttributes("giro")
@RequestMapping("/giro")
@Controller
public class GiroController {
	
	@Autowired
	private IGiroService giroService;
	
	@GetMapping(value = "/cargar_giro/{term}", produces = { "application/json" })
	public @ResponseBody List<Giro> giroTodosJson(@PathVariable String term) {
		List<Giro> list = giroService.findByNombre(term);
		return list;
	}
	

}
