package com.tecsup.demo.models.service;

import com.tecsup.demo.models.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
	public Usuario registrar(Usuario u);
	
}
