package com.servitecafastwash.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.servitecafastwash.modelo.Auditoria;
import com.servitecafastwash.modelo.TipoVehiculo;
import com.servitecafastwash.modelo.Usuario;
import com.servitecafastwash.modelo.Vehiculo;
import com.servitecafastwash.modelo.service.IAuditoriaService;
import com.servitecafastwash.modelo.service.ITipoVehiculoService;
import com.servitecafastwash.modelo.service.IUsuarioService;
import com.servitecafastwash.modelo.service.IVehiculoService;
import com.servitecafastwash.modelo.util.PageRender;

@Controller
@RequestMapping({ "vehiculo", "/" })
@SessionAttributes("vehiculo")
public class VehiculoController {

	@Autowired
	private ITipoVehiculoService tipoVehiculoService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IAuditoriaService auditoriaService;

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/crear")
	public String crear(Model model) {
		Vehiculo vehiculo = new Vehiculo();
		List<TipoVehiculo> tipoVehiculo = tipoVehiculoService.findAll();

		model.addAttribute("vehiculo", vehiculo);
		model.addAttribute("tipoVehiculo", tipoVehiculo);
		model.addAttribute("titulo", "Crear Vehiculo");

		return "vehiculo/crearVehiculo";
	}

	@PostMapping("/crear")
	public String guardar(@Valid Vehiculo vehiculo, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash, Authentication authentication) {

		Auditoria auditoria = new Auditoria();

		Usuario usuario = usuarioService.findByEmail(authentication.getName());

		if (vehiculo.getId() == null) {
			
			Vehiculo placa = vehiculoService.findByPlaca(vehiculo.getPlaca());
			
			if (placa != null) {
			
				List<TipoVehiculo> tipoVehiculo = tipoVehiculoService.findAll();
				model.addAttribute("tipoVehiculo", tipoVehiculo);
				model.addAttribute("existe", "La Placa Ya Existe");
				
				return "/vehiculo/crearVehiculo";
				
			}
			auditoria.setMovimiento("Crear Vehiculo");
			auditoria.setUsuario(usuario);
			auditoriaService.save(auditoria);

			vehiculoService.guardar(vehiculo);
			status.setComplete();
			flash.addFlashAttribute("success", "Vehiculo Creado Correctamente");
			return "redirect:/vehiculo/listar";
		}
		if (result.hasErrors()) {
			return "/vehiculo/crearVehiculo";
		}

		auditoria.setMovimiento("Editar Vehiculo");
		auditoria.setUsuario(usuario);
		auditoriaService.save(auditoria);

		vehiculoService.guardar(vehiculo);
		status.setComplete();
		flash.addFlashAttribute("success", "Vehiculo Editado Correctamente");
		return "redirect:/vehiculo/listar";

	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/crear/{id}")
	public String editar(Model model, @PathVariable(name = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Vehiculo vehiculo = vehiculoService.findById(id);
			List<TipoVehiculo> tipoVehiculo = tipoVehiculoService.findAll();
			if (vehiculo == null) {
				flash.addFlashAttribute("danger", "El Vehiculo No Existe");
			}
			model.addAttribute("tipoVehiculo", tipoVehiculo);
			model.addAttribute("vehiculo", vehiculo);
		}
		return "/vehiculo/crearVehiculo";
	}

	@GetMapping({ "/listar", "/" })
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 5);
		Page<Vehiculo> vehiculos = vehiculoService.findAll(pageable);
		PageRender<Vehiculo> pageRender = new PageRender<>("/vehiculo/listar", vehiculos);
		model.addAttribute("vehiculo", vehiculos);
		model.addAttribute("page", pageRender);
		return "vehiculo/listar";
	}

	@RequestMapping(value = "/buscarVehi", method = RequestMethod.GET)
	public @ResponseBody String buscarVehiculo(@RequestParam("nombre") String nombre, Model model) {
		String r = "";
		List<Vehiculo> vehiculos = vehiculoService.findAllVehiLike(nombre);
		r += "<table class='table table-striped'>"
				+ "<thead> <tr> <th>Cedula</th> <th>Nombre</th> <th>Apellido</th> <th>Placa</th> <th>Tipo Vehiculo</th>"
				+ "<th>Registrar Servicio</th> <th>Editar</th> <th>Eliminar</th></thead><tbody>";
		
		for (Vehiculo vehiculo : vehiculos) {
			r += "<tr> <td>" + vehiculo.getCedula() + "</td>" + "<td>" + vehiculo.getNombre() + "</td>" + "<td>"
					+ vehiculo.getApellido() + "</td>" + "<td>" + vehiculo.getPlaca() + "</td> " + "<td>"
					+ vehiculo.getTiposVehiculos().getTipoVehiculo() + "</td> <td> <a href=" + "'/cobro/crear/'" +
					"class='btn btn-success btn-xs text-white'>Registrar Servicio</a>";
		}
		r += "</tr></tbody> </table>";
		return r;
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash,
			Authentication authentication) {
		if (id > 0) {
			Usuario usuario = usuarioService.findByEmail(authentication.getName());

			Auditoria auditoria = new Auditoria();
			auditoria.setMovimiento("Eliminar Vehiculo");
			auditoria.setUsuario(usuario);
			auditoriaService.save(auditoria);

			vehiculoService.eliminar(id);
			flash.addFlashAttribute("success", "Vehiculo Eliminado Correctamente");
		}
		return "redirect:/listar";
	}
}
