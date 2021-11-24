package com.tecsup.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tecsup.demo.models.entity.Producto;
import com.tecsup.demo.models.entity.Usuario;
import com.tecsup.demo.models.service.IProductoService;
import com.tecsup.demo.models.service.IUsuarioService;

@Controller
@RequestMapping("/private")
public class PrivateController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/index")
	public String index(Authentication auth, HttpSession session) {
		String username=auth.getName();
		if(session.getAttribute("usuario")==null) {
			Usuario usuario=usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		return "index";
		
	}
	/*
	 * Productos
	 */
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Producto>productos = productoService.listar();
		model.addAttribute("productos",productos);
		return "index";
	}
	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("producto",new Producto());
		return "formProducto";
	}
	@PostMapping("/save")
	public String save(@Validated Producto p,Model model) {
		productoService.save(p);
		return "redirect:/listar";
	}
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		Optional<Producto>producto=productoService.listarId(id);
		model.addAttribute("producto",producto);
		return "formProducto";
	}
	@GetMapping("/eliminar/{id}")
	public String delete(Model model,@PathVariable int id) {
		productoService.delete(id);
		return "redirect:/listar";
	}
	
}
