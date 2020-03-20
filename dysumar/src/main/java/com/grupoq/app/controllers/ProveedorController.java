package com.grupoq.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.grupoq.app.models.entity.Proveedor;
import com.grupoq.app.models.service.IProveedorService;

@RequestMapping("/proveedor")
@Controller
public class ProveedorController {

	@Autowired
	private IProveedorService proveedoresService;
	
	@GetMapping(value = "/cargar_proveedor", produces = { "application/json" })
	public @ResponseBody List<Proveedor> celulasTodosJson() {
		List<Proveedor> list = proveedoresService.findAll();
		return list;
	}
}
