package com.servitecafastwash.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String  error, Model model, 
			Principal principal, RedirectAttributes flash, @RequestParam(value="logout", required=false) String  logout) {
		
		if(principal != null){
			flash.addFlashAttribute("info", "Ya Has Iniciado Sesión");
			return "redirect:/";
		}
		if(error != null ) {
			model.addAttribute("danger", "Nombre De Usuario O Contraseña Incorrecta");			
		}
		if(logout != null ) {
			model.addAttribute("info", "Has Cerrado De Sesiòn Con Èxito");			
		}
		return "login";
	}
}
