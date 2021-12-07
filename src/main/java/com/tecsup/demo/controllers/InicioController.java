package com.tecsup.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
	
	// Podriamos inculir aca la ruleta de los productos
	@GetMapping("/")
	public String inicio() {
		return "inicio";
	}
}
