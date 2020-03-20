package com.grupoq.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grupoq.app.models.entity.Marca;
import com.grupoq.app.models.service.IMarcaService;


@RequestMapping("/marca")
@Controller
public class MarcaController {
	
	@Autowired
	private IMarcaService marcaService;
	
	@GetMapping(value = "/cargar_marca", produces = { "application/json" })
	public @ResponseBody List<Marca> marcaTodosJson() {
		List<Marca> list = marcaService.findAll();
		return list;
	}

}
