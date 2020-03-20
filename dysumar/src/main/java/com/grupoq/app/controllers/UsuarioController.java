package com.grupoq.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.grupoq.app.models.entity.Role;
import com.grupoq.app.models.entity.Usuario;
import com.grupoq.app.models.service.IRolesService;
import com.grupoq.app.models.service.IUsuarioService;
import com.grupoq.app.util.paginator.PageRender;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UsuarioController {
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IRolesService rolesService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = { "/ver" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {

		if (authentication != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}

//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Pageable pageRequest = PageRequest.of(page, 4);

		Usuario user1 = usuarioService.findByUsername(authentication.getName());
		Page<Usuario> usuario = null;
		if (authentication.getName().equals("admin")) {
			usuario = usuarioService.findByIdNot(user1.getId(), pageRequest);
		} else {
			usuario = usuarioService.findByRoles_Authority("ROLE_USER", pageRequest);
		}
//		List<Taller> taller = tallerService.findAll();

//				clienteService.findAll(pageRequest);

		PageRender<Usuario> pageRender = new PageRender<Usuario>("/ver", usuario);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("usuario", usuario);
		model.addAttribute("page", pageRender);
		return "users/ver";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin/{id}")
	public String hacerAdmin(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Role rol = new Role();
		Role rolcheck = new Role();
		rolcheck = rolesService.findByUser_idByAuthority(id, "ROLE_ADMIN");
		if (rolcheck != null) {
			rolesService.delete(rolcheck);
			flash.addFlashAttribute("error", "El usuario removido de administrador");
			return "redirect:/user/ver";
		}
		rol.setAuthority("ROLE_ADMIN");
		rol.setUser_id(id);
		rolesService.save(rol);
		flash.addFlashAttribute("success", "El usuario ahora tiene rol de administrador");
		return "redirect:/user/ver";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/adminU/{id}")
	public String hacerUser(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Role rol = new Role();
		Role rolcheck = new Role();
		rolcheck = rolesService.findByUser_idByAuthority(id, "ROLE_USER");
		if (rolcheck != null) {
			rolesService.delete(rolcheck);
			flash.addFlashAttribute("error", "El usuario removido de role de user");
			return "redirect:/user/ver";
		}
		rol.setAuthority("ROLE_USER");
		rol.setUser_id(id);
		rolesService.save(rol);
		flash.addFlashAttribute("success", "El usuario ahora tiene rol de usuario");
		return "redirect:/user/ver";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;

		if (id > 0) {
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/listar";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Editar Usuario");
		return "users/form";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/desact/{id}")
	public String desactivar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;

		if (id > 0) {
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/listar";
		}

		usuario.setEnabled(false);
		usuarioService.save(usuario);
		model.put("usuario", usuario);
		model.put("titulo", "Usuarios");
		return "redirect:/users/ver";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/activar/{id}")
	public String activarU(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;

		if (id > 0) {
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/listar";
		}

		usuario.setEnabled(true);
		usuarioService.save(usuario);
		model.put("usuario", usuario);
		model.put("titulo", "Usuarios");
		return "redirect:/users/ver";
	}

	@GetMapping(value = "/carga_tipou", produces = { "application/json" })
	public @ResponseBody List<Role> cargarTipou() {
		return usuarioService.findAllRole();
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		String mensajeFlash = (usuario.getId() != null) ? "usuario guardado con éxito!" : "usuario editado con éxito!";
		usuario.setEnabled(true);
		String pass1 = usuario.getPassword();
		String passfinal = passwordEncoder.encode(pass1);
		usuario.setPassword(passfinal);
		usuarioService.save(usuario);

		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/user/ver";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		usuarioService.delete(id);
		flash.addFlashAttribute("success", "Usuario eliminado con éxito!");

		return "redirect:/user/ver";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/crear")
	public String crear(Map<String, Object> model) {

		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Crear Usuario");
		return "users/form";
	}
}
