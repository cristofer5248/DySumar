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

//import com.grupoq.app.models.entity.ClienteDirecciones;
import com.grupoq.app.models.entity.Role;
import com.grupoq.app.models.entity.Usuario;
import com.grupoq.app.models.service.IRolesService;
import com.grupoq.app.models.service.IUsuarioService;
import com.grupoq.app.util.paginator.PageRender;

@Controller
@RequestMapping("/user")
@SessionAttributes("usuario")
public class UsuarioController {
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IRolesService rolesService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
	@RequestMapping(value = { "/ver" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {

		if (authentication != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}

//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Pageable pageRequest = PageRequest.of(page, 10);

		Usuario user1 = usuarioService.findByUsername(authentication.getName());
		Page<Usuario> usuario = null;

		Role rolcheck = new Role();
		rolcheck = rolesService.findByUser_id(user1.getId());
		if (rolcheck.getAuthority().equals("ROLE_ADMIN") || rolcheck.getAuthority().equals("ROLE_JEFEADM")) {
			if (rolcheck.getAuthority().equals("ROLE_ADMIN")) {
				usuario = usuarioService.findByIdNot(user1.getId(), pageRequest);
			} else {
				usuario = usuarioService.findByIdNotAndBoss(rolcheck.getId(), pageRequest);
			}
		} else {
			usuario = usuarioService.findByRoles_Authority("ROLE_USER", pageRequest);
		}
//		List<Taller> taller = tallerService.findAll();

//				clienteService.findAll(pageRequest);

		PageRender<Usuario> pageRender = new PageRender<Usuario>("/user/ver", usuario);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("usuario", usuario);
		model.addAttribute("page", pageRender);
		return "users/ver";
	}

	@GetMapping(value = "/verusuario/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = usuarioService.findOne(id);
//		List<?> taller = facturaService.probando(id);
		
		if (usuario == null) {
			flash.addFlashAttribute("error", "El cliente no tiene facturas");			
			return "redirect:users/ver";
		}

		model.put("usuario", usuario);		
		model.put("titulo", "Detalle del usuario: " + usuario.getNombre()+" "+usuario.getApellidos());
		return "users/verusuario";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin/{id}")
	public String hacerAdmin(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Role rol = new Role();
		Role rolcheck = new Role();
		rolcheck = rolesService.findByUser_idByAuthority(id, "ROLE_ADMIN");

		List<Role> countRoles;
		countRoles = rolesService.findByUser_idList(id);
		System.out.print("El size es: " + countRoles.size());
		if (countRoles.size() <= 1){
			if (rolcheck == null) {
				rol.setAuthority("ROLE_ADMIN");
				rol.setUser_id(id);
				rolesService.save(rol);
				flash.addFlashAttribute("success", "El usuario ahora tiene rol de ADMINISTRADOR");
				return "redirect:/user/ver";
			}
			flash.addFlashAttribute("error",
					"El rol de ese usuario no pudo ser eliminado porque se quedaria sin ningun ROL, asignar uno primero");
			return "redirect:/user/ver";
		}
		rolesService.delete(rolcheck);
		flash.addFlashAttribute("error", "El usuario removido de role de ADMINISTRADOR");
		return "redirect:/user/ver";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
	@RequestMapping(value = "/adminJ/{id}")
	public String hacerJefe(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Role rol = new Role();
		Role rolcheck = new Role();
		rolcheck = rolesService.findByUser_idByAuthority(id, "ROLE_JEFEADM");

		List<Role> countRoles;
		countRoles = rolesService.findByUser_idList(id);
		System.out.print("El size es: " + countRoles.size());
		if (countRoles.size() <= 1) {
			if (rolcheck == null) {
				rol.setAuthority("ROLE_JEFEADM");
				rol.setUser_id(id);
				rolesService.save(rol);
				flash.addFlashAttribute("success", "El usuario ahora tiene rol de JEFE/A");
				return "redirect:/user/ver";
			}
			flash.addFlashAttribute("error",
					"El rol de ese usuario no pudo ser eliminado porque se quedaria sin ningun ROL, asignar uno primero");
			return "redirect:/user/ver";
		}
		rolesService.delete(rolcheck);
		flash.addFlashAttribute("error", "El usuario removido de role de JEFE/A");
		return "redirect:/user/ver";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
	@RequestMapping(value = "/adminINV/{id}")
	public String hacerInv(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Role rol = new Role();
		Role rolcheck = new Role();
		rolcheck = rolesService.findByUser_idByAuthority(id, "ROLE_INV");

		List<Role> countRoles;
		countRoles = rolesService.findByUser_idList(id);
		System.out.print("El size es: " + countRoles.size());
		if (countRoles.size() <= 1) {
			if (rolcheck == null) {
				rol.setAuthority("ROLE_INV");
				rol.setUser_id(id);
				rolesService.save(rol);
				flash.addFlashAttribute("success", "El usuario ahora tiene rol de JEFE DE INVENTARIO");
				return "redirect:/user/ver";
			}
			flash.addFlashAttribute("error",
					"El rol de ese usuario no pudo ser eliminado porque se quedaria sin ningun ROL, asignar uno primero");
			return "redirect:/user/ver";
		}
		rolesService.delete(rolcheck);
		flash.addFlashAttribute("error", "El usuario removido de role de JEFE DE INVENTARIO");
		return "redirect:/user/ver";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
	@RequestMapping(value = "/adminS/{id}")
	public String hacerSeller(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Role rol = new Role();
		Role rolcheck = new Role();
		rolcheck = rolesService.findByUser_idByAuthority(id, "ROLE_SELLER");

		List<Role> countRoles;
		countRoles = rolesService.findByUser_idList(id);
		System.out.print("El size es: " + countRoles.size());
		if (countRoles.size() <= 1) {
			if (rolcheck == null) {
				rol.setAuthority("ROLE_SELLER");
				rol.setUser_id(id);
				rolesService.save(rol);
				flash.addFlashAttribute("success", "El usuario ahora tiene rol de VENDEDOR");
				return "redirect:/user/ver";
			}
			flash.addFlashAttribute("error",
					"El rol de ese usuario no pudo ser eliminado porque se quedaria sin ningun ROL, asignar uno primero");
			return "redirect:/user/ver";
		}
		rolesService.delete(rolcheck);
		flash.addFlashAttribute("error", "El usuario removido de role de VENDEDOR");
		return "redirect:/user/ver";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
	@RequestMapping(value = "/adminF/{id}")
	public String hacerFacturero(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Role rol = new Role();
		Role rolcheck = new Role();
		rolcheck = rolesService.findByUser_idByAuthority(id, "ROLE_FACT");

		List<Role> countRoles;
		countRoles = rolesService.findByUser_idList(id);
		System.out.print("El size es: " + countRoles.size());
		if (countRoles.size() <= 1) {
			if (rolcheck == null) {
				rol.setAuthority("ROLE_FACT");
				rol.setUser_id(id);
				rolesService.save(rol);
				flash.addFlashAttribute("success", "El usuario ahora tiene rol de FACTURACION");
				return "redirect:/user/ver";
			}
			flash.addFlashAttribute("error",
					"El rol de ese usuario no pudo ser eliminado porque se quedaria sin ningun ROL, asignar uno primero");
			return "redirect:/user/ver";
		}
		rolesService.delete(rolcheck);
		flash.addFlashAttribute("error", "El usuario removido de role de FACTURACION");		
		return "redirect:/user/ver";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;

		if (id > 0) {
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/user/ver";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/user/ver";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Editar Usuario");
		return "users/form";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
	@RequestMapping(value = "/desact/{id}")
	public String desactivar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;

		if (id > 0) {
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El ID del USUARIO no existe en la BBDD!");
				return "redirect:/user/ver";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del USUARIO no puede ser cero!");
			return "redirect:/user/ver";
		}

		usuario.setEnabled(false);
		usuarioService.save(usuario);
		flash.addFlashAttribute("success", "Usuario Desactivado!");
		model.put("titulo", "Usuarios");
		return "redirect:/user/ver";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
	@RequestMapping(value = "/activar/{id}")
	public String activarU(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;

		if (id > 0) {
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El ID del USER no existe en la BBDD!");
				return "redirect:/user/ver";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del USER no puede ser cero!");
			return "redirect:/user/ver";
		}

		usuario.setEnabled(true);
		usuarioService.save(usuario);
		model.put("titulo", "Usuarios");
		flash.addFlashAttribute("success", "Usuario Activado!");
		return "redirect:/user/ver";
	}

	@GetMapping(value = "/carga_tipou", produces = { "application/json" })
	public @ResponseBody List<Role> cargarTipou() {
		return usuarioService.findAllRole();
	}

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
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

	@Secured({ "ROLE_ADMIN", "ROLE_JEFEADM" })
	@RequestMapping(value = "/crear")
	public String crear(Map<String, Object> model) {

		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Crear Usuario");
		return "users/form";
	}
}
