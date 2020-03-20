package com.grupoq.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grupoq.app.models.entity.Margen;
import com.grupoq.app.models.service.IMargenService;

@Controller
@RequestMapping("/margen")
public class MargenController {

	@Autowired
	private IMargenService margenService;
	
	@GetMapping(value = "/cargar_margen", produces = { "application/json" })
	public @ResponseBody List<Margen> celulasTodosJson() {
		List<Margen> list = margenService.findAll();
		return list;
	}
}
