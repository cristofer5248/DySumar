package com.grupoq.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.grupoq.app.models.entity.Presentacion;
import com.grupoq.app.models.service.IPresentacionService;

@Controller
@RequestMapping("/presentacion")
public class PresentacionController {
	
	@Autowired
	private IPresentacionService presentacionService;
	
	@GetMapping(value = "/cargar_presentacion", produces = { "application/json" })
	public @ResponseBody List<Presentacion> celulasTodosJson() {
		List<Presentacion> list = presentacionService.findAll();
		return list;
	}

}
