package com.servitecafastwash.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.servitecafastwash.modelo.Auditoria;
import com.servitecafastwash.modelo.Servicio;
import com.servitecafastwash.modelo.TipoVehiculo;
import com.servitecafastwash.modelo.Usuario;
import com.servitecafastwash.modelo.service.IAuditoriaService;
import com.servitecafastwash.modelo.service.IServicioService;
import com.servitecafastwash.modelo.service.ITipoVehiculoService;
import com.servitecafastwash.modelo.service.IUsuarioService;
import com.servitecafastwash.modelo.util.PageRender;

@Controller
@SessionAttributes("servicio")
@RequestMapping("/servicio")
public class ServicioController {

	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private ITipoVehiculoService tipoVehiculoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IAuditoriaService auditoriaService;
	
	@GetMapping("/crear")
	public String crear(Model model) {
		Servicio servicio = new Servicio();
		List<TipoVehiculo> tipoVehiculo = tipoVehiculoService.findAll();
		model.addAttribute("tipoVehiculo", tipoVehiculo);
		model.addAttribute("servicio", servicio);
		return "servicio/crearServicio";
	}
	
	@PostMapping("/crear")
	public String guardar(@Valid Servicio servicio,BindingResult result,Model model, SessionStatus status, RedirectAttributes flash
			, Authentication authentication) {
		
		if(result.hasErrors()) {
			List<TipoVehiculo> tipoVehiculo  = tipoVehiculoService.findAll();
			model.addAttribute("tipoVehiculo", tipoVehiculo);
			return "/servicio/crearServicio";
		}
		Usuario usuario = usuarioService.findByEmail(authentication.getName());
		
		Auditoria auditoria = new Auditoria();
		auditoria.setUsuario(usuario);
		
		if(servicio.getId() == null) {
			auditoria.setMovimiento("Crear Servicio");
			flash.addFlashAttribute("success", "Servicio Creado Correctamente");
		}else {
			auditoria.setMovimiento("Editar Servicio");
			flash.addFlashAttribute("success", "Servicio Editado Correctamente");
		}
		auditoriaService.save(auditoria);
		servicioService.save(servicio);
		status.setComplete();
		
		return "redirect:/servicio/listar";
	}
	
	@GetMapping("/crear/{id}")
	public String editar( @PathVariable Long id,Model model,RedirectAttributes flash) {
		if(id > 0) {
			Servicio servicio = servicioService.findById(id);
			List<TipoVehiculo> tipoVehiculo  = tipoVehiculoService.findAll();
			if(servicio == null) {
				flash.addFlashAttribute("danger", "El Servicio No Existe");
			}
			model.addAttribute("servicio", servicio);
			model.addAttribute("tipoVehiculo", tipoVehiculo);
		}
		return "/servicio/crearServicio";
	}
	
	@GetMapping("/listar")
	public  String  listar(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 5);
		Page<Servicio> servicio = servicioService.findAll(pageable);
		PageRender<Servicio> pageRender = new PageRender<>("/servicio/listar", servicio);
		model.addAttribute("servicio", servicio);
		model.addAttribute("page", pageRender);
		return "servicio/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String Eliminar(@PathVariable(name="id") Long id, RedirectAttributes flash, Authentication authentication) {
		if(id > 0) {
			Usuario usuario = usuarioService.findByEmail(authentication.getName());
			
			Auditoria auditoria = new Auditoria();
			auditoria.setMovimiento("Eliminar Servicio");
			auditoria.setUsuario(usuario);
			auditoriaService.save(auditoria);
			
			servicioService.delete(id);
			flash.addFlashAttribute("success", "Se Elimino El Servicio Correctamente");
		}
		return "redirect:/servicio/listar";
	}
}
