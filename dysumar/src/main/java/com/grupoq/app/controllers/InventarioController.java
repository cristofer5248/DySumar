package com.grupoq.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.grupoq.app.models.entity.Inventario;
import com.grupoq.app.models.entity.Producto;
import com.grupoq.app.models.service.IInventarioService;
import com.grupoq.app.util.paginator.PageRender;

@Controller
@SessionAttributes("inventario")
@RequestMapping("/inventario")
public class InventarioController {
	
	@Autowired
	private IInventarioService inventarioService;
	
	@RequestMapping(value= "/listar", method=RequestMethod.GET)
	public String listar (@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 20);
		Page<Inventario> inventario = inventarioService.findAll(pageRequest);
		PageRender<Inventario> pageRender = new PageRender<>("listar", inventario);
		model.addAttribute("titulo", "Listado de inventario");
		model.addAttribute("inventarios", inventario);
		model.addAttribute("page", pageRender);
		return "/inventario/listar";		
	}
}
