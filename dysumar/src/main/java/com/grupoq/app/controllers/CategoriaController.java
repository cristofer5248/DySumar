package com.grupoq.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grupoq.app.models.entity.Categoria;
import com.grupoq.app.models.service.ICategoriaService;

@RequestMapping("/categoria")
@Controller
public class CategoriaController {
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@GetMapping(value = "/cargar_categoria", produces = { "application/json" })
	public @ResponseBody List<Categoria> celulasTodosJson() {
		List<Categoria> list = categoriaService.findAll();
		return list;
	}

}
