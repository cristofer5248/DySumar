package com.grupoq.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.grupoq.app.models.entity.NotadeCredito;
import com.grupoq.app.models.service.INotadeCreditoService;
import com.grupoq.app.util.paginator.PageRender;

@Controller
@SessionAttributes("notadecredito")
@RequestMapping("/notadecredito")
@Secured({ "ROLE_ADMIN", "ROLE_INV", "ROLE_JEFEADM" })
public class NotadeCreditoController {

	@Autowired
	INotadeCreditoService notadecreditoservice;

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo2(Map<String, Object> model, RedirectAttributes flash) {
		NotadeCredito notadecredito = new NotadeCredito();
		model.put("notadecredito", notadecredito);
		model.put("titulo", "Nota de credito");
		return "/notacredito/form2";
	}

	@RequestMapping(value = { "/listar", "/listar/{date1}/{date2}", "/listar/{codigo}" }, method = RequestMethod.GET)
	public String listar(@PathVariable(value = "codigo", required = false) String codigo,
			@PathVariable(value = "date1", required = false) String date1,
			@PathVariable(value = "date2", required = false) String date2,
			@RequestParam(value = "all", required = false, defaultValue = "1") int all,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		String xlsxPath = "?page=" + page;
		xlsxPath = (date1 != null) ? xlsxPath + "&all=" + all : xlsxPath;

		boolean enableallsearch = (date1 != null) ? true : false;

		String pathexcel = (enableallsearch) ? "notadecredito/listar/" + date1 + "/" + date2 + xlsxPath : xlsxPath;
		pathexcel = (codigo != null) ? "notadecredito/listar/" + codigo + xlsxPath : xlsxPath;

		Date date1_ = null;
		Date date2_ = null;
		if (date1 != null) {
			try {
				date1_ = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
				date2_ = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
				model.addAttribute("rangofechas", date1 + " y " + date2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		Pageable pageRequest = (all > 0) ? Pageable.unpaged() : PageRequest.of(page, 20);
		Page<NotadeCredito> notadecredito = null;
		if (codigo != null) {
			notadecredito = notadecreditoservice.findByCodigodoc(codigo, pageRequest);
		} else {
			notadecredito = (date1 != null && codigo == null)
					? notadecreditoservice.findByDates(date1_, date2_, pageRequest)
					: notadecreditoservice.findAll(pageRequest);
		}

		model.addAttribute("titulo", "Listado de inventario");
		PageRender<NotadeCredito> pageRender = new PageRender<>("listar", notadecredito);
		model.addAttribute("notasdecredito", notadecredito);
		model.addAttribute("page", pageRender);
		model.addAttribute("enableallsearch", enableallsearch);
		model.addAttribute("activePivot",false);
		model.addAttribute("pathall", pathexcel);

		return "/facturas/listarndc";
	}

}
