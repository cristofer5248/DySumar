package com.grupoq.app.controllers;

import java.util.Map;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.grupoq.app.models.entity.NotadeCredito;

@Controller
@SessionAttributes("notadecredito")
@RequestMapping("/notadecredito")
@Secured({ "ROLE_ADMIN", "ROLE_INV", "ROLE_JEFEADM" })
public class NotadeCreditoController {

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo2(Map<String, Object> model, RedirectAttributes flash) {
		NotadeCredito notadecredito = new NotadeCredito();
		model.put("notadecredito", notadecredito);
		model.put("titulo", "Nota de credito");
		return "/notacredito/form2";
	}

}
