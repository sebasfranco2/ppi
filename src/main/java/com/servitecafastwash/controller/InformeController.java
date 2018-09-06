package com.servitecafastwash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformeController {
	
	@GetMapping("/informe")
	public String informe() {
		return "informe";
	}
}
