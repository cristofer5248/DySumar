package com.grupoq.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupoq.app.models.entity.Marca;
import com.grupoq.app.models.service.IMarcaService;
import com.grupoq.app.util.paginator.PageRender;

@RequestMapping("/marca")
@SessionAttributes("marca")
@Controller
public class MarcaController {

	@Autowired
	private IMarcaService marcaService;

	@GetMapping(value = "/cargar_marca", produces = { "application/json" })
	public @ResponseBody List<Marca> marcaTodosJson() {
		List<Marca> list = marcaService.findAll();
		return list;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 20);
		Page<Marca> marcas = marcaService.findAllPage(pageRequest);
		PageRender<Marca> pageRender = new PageRender<>("listar", marcas);
		model.addAttribute("titulo", "Listado de marcas");
		model.addAttribute("marcas", marcas);
		model.addAttribute("page", pageRender);
		return "/marcas/listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Map<String, Object> model) {
		Marca marcas = new Marca();
		model.put("marca", marcas);
		model.put("titulo", "Registrar nueva marca");
		model.put("nullchecker", 1);
		return "/marcas/form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@Valid Marca marca, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Marcas");
			return "/marcas/marcaform";
		}
		String mensajeFlash = (marca.getIdm() != null) ? "marca editada con éxito!" : "Marca creada con éxito!";

		marcaService.save(marca);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/marca/listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			try {
				marcaService.delete(id);
				flash.addFlashAttribute("success", "Marca eliminado con éxito!");
			} catch (Exception e) {
				flash.addFlashAttribute("success",
						"La marca posiblemente tiene registros en productos, no se puede eliminar!");
				return "redirect:/marca/listar";
			}

		}
		return "redirect:/marca/listar";
	}
}
