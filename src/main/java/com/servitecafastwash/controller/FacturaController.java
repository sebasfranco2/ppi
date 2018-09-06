package com.servitecafastwash.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.servitecafastwash.modelo.Auditoria;
import com.servitecafastwash.modelo.Factura;
import com.servitecafastwash.modelo.ItemFactura;
import com.servitecafastwash.modelo.Servicio;
import com.servitecafastwash.modelo.Usuario;
import com.servitecafastwash.modelo.Vehiculo;
import com.servitecafastwash.modelo.service.IAuditoriaService;
import com.servitecafastwash.modelo.service.IFacturaService;
import com.servitecafastwash.modelo.service.IServicioService;
import com.servitecafastwash.modelo.service.IUsuarioService;
import com.servitecafastwash.modelo.service.IVehiculoService;


@Controller
@SessionAttributes("factura")
@RequestMapping("/cobro")
public class FacturaController {

	@Autowired
	private IFacturaService facturaService;
	
	@Autowired
	private IVehiculoService vehiculoService;
	
	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IAuditoriaService auditoriaService;
	

	
	@GetMapping("/crear/{vehiculoId}")
	public String crear(Model model, @PathVariable("vehiculoId") Long vehiculoId,RedirectAttributes flash) {
		Vehiculo vehiculo = vehiculoService.findById(vehiculoId);
		
		if(vehiculo == null) {
			flash.addFlashAttribute("danger", "El Vehiculo No Existe");
			return "redirect:/vehiculo/listar";
		}
		Factura factura = new Factura();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String fecha = format.format(date);
		
		factura.setVehiculo(vehiculo);
		model.addAttribute("factura", factura);
		model.addAttribute("fecha", fecha);
		List<Factura> factura1 = facturaService.findByLast(vehiculo.getId());
		if(factura1.size() > 0) {
			if(factura1.get(0).getActivo() == 1) {
				flash.addFlashAttribute("danger", "El Vehiculo Tiene Un Servicio En progreso");
				return "redirect:/vehiculo/listar";
			}else {
				return "factura/crearFactura";
			}
		}
		return "factura/crearFactura";
	}
	
	@PostMapping("/crear")
	
		public String guardar(Factura factura,@RequestParam(name="item_id[]", required=false) Long itemId[], 
				RedirectAttributes flash, SessionStatus status, Model model,Authentication authentication){
		if(itemId == null || itemId.length == 0) {
			flash.addFlashAttribute("danger", "El Cobro Debe Tener Servicios Agregados");
			return "redirect:/cobro/crear" + factura.getVehiculo().getId();
		}
		for(int i=0; i < itemId.length; i++) {
			Servicio servicio = servicioService.findById(itemId[i]);
			ItemFactura linea = new ItemFactura();
			linea.setServicio(servicio);
			factura.addItemFactura(linea);
		}
		Usuario usuario = usuarioService.findByEmail(authentication.getName());
				
		Auditoria auditoria = new Auditoria();
		auditoria.setMovimiento("Guardar Factura");
		auditoria.setUsuario(usuario);
		auditoriaService.save(auditoria);
		
		factura.setUsuario(usuario);
		factura.setActivo(1);
		facturaService.save(factura);
		status.setComplete();
		flash.addFlashAttribute("success", "Factura Creada Correctamente");
		return "redirect:/vehiculo/listar";
	}
	
    @GetMapping(value="/cargar-producto/{term}" , produces = { "application/json" })
    public @ResponseBody List<Servicio> cargarProducto(@PathVariable(name="term") String nombre){
    	return servicioService.findNombre(nombre);
    }
	
}
