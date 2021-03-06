package com.tecsup.demo.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String save(@RequestParam(name="file",required=false) MultipartFile foto, Producto p,
			RedirectAttributes flash) {
		if(!foto.isEmpty()) {

			String ruta="D://Producto//recursos";

			try {
				byte[] bytes = foto.getBytes();
				Path rutaAbsoluta = Paths.get(ruta+"//"+foto.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
				p.setFoto(foto.getOriginalFilename());
			}catch(Exception e){
				
			}
		}
		
		productoService.save(p);
		return "redirect:/private/listar";
		
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
		return "redirect:/private/listar";
	}
	
	
	
	
}
