package com.servitecafastwash.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.servitecafastwash.modelo.Auditoria;
import com.servitecafastwash.modelo.Usuario;
import com.servitecafastwash.modelo.service.IAuditoriaService;
import com.servitecafastwash.modelo.service.UsuarioServiceImpl;

@Controller
@SessionAttributes("usuario")
@RequestMapping("/usuario")
@Secured("ROLE_ADMIN")
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IAuditoriaService auditoriaService;
	
	@GetMapping("/crear")
	public String crear(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "/usuario/crearUsuario";
	}
	

	@GetMapping("/crear/{id}")
	public String editar( @PathVariable(name="id") Long id, Model model, RedirectAttributes flash) {
		if(id > 0) {
			Usuario usuario =usuarioService.findById(id);
			if(usuario == null) {
				flash.addFlashAttribute("danger", "El Usuario No Existe");
				return "redirect:/usuario/listar";
			}
			model.addAttribute("usuario", usuario);
		}
		return "/usuario/crearUSuario";
	}
	
	@PostMapping("/crear")
	public String guardar(@Valid Usuario usuario, BindingResult result, SessionStatus status, RedirectAttributes flash,
						Model model, Authentication authentication) {
		
		usuario.setEnabled(true);
		String encode = passwordEncoder.encode(usuario.getContrasena());
		usuario.setContrasena(encode);
		
		Auditoria auditoria = new Auditoria();
		
		if(usuario.getId() == null ) {
			auditoria.setMovimiento("Crear Usuario");
			Usuario email = usuarioService.findByEmail(usuario.getEmail());
			if(email != null) {
				model.addAttribute("existe", "El Correo Ya Existe");
				return "/usuario/crearUsuario";
			} 
			email=usuarioService.findByEmail(authentication.getName());
			auditoria.setUsuario(email);
			flash.addFlashAttribute("success", "Usuario Creado Correctamente");
		}else {		
			usuario.addAuditoria(auditoria);
			auditoria.setMovimiento("Editar Usuario");
			flash.addFlashAttribute("success", "Usuario Editado Correctamente");
		}
		if(result.hasErrors()) {
			return "/usuario/crearUsuario";
		}
		auditoriaService.save(auditoria);
		usuarioService.save(usuario);
		status.setComplete();
		
		
		return "redirect:/usuario/listar";
	}
	
	
	@GetMapping("/listar")
	public String listar(Model model){
		List<Usuario> usuario = usuarioService.findAll();
		model.addAttribute("usuario", usuario);
		return "/usuario/listar"; 		
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(name="id") Long id, RedirectAttributes flash,Authentication authentication) {
		if(id > 0) {
			Usuario usuario = usuarioService.findByEmail(authentication.getName());
			
			Auditoria auditoria = new Auditoria();
			auditoria.setMovimiento("Eliminar Usuario");
			auditoria.setUsuario(usuario);
			
			auditoriaService.save(auditoria);
			
			usuarioService.delete(id);
			flash.addFlashAttribute("danger", "El Usuario Se Elimino Correctamente");
		}
		return "redirect:/usuario/listar";
	}
	
}
