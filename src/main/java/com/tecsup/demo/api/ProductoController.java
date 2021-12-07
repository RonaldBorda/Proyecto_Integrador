package com.tecsup.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.demo.models.entity.Producto;
import com.tecsup.demo.models.impl.ProductoServiceImpl;

@RestController
public class ProductoController {
	@Autowired
	private ProductoServiceImpl productoServiceImpl;
	
	@RequestMapping("/productos")
	public Iterable<Producto> getProduc(){
		return productoServiceImpl.findAll();
	}
}
