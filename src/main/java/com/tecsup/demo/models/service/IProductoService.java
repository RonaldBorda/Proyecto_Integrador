package com.tecsup.demo.models.service;

import java.util.List;
import java.util.Optional;

import com.tecsup.demo.models.entity.Producto;


public interface IProductoService {
	public List<Producto>listar();
	public Optional<Producto>listarId(int id);
	public int save(Producto p);
	public void delete(int id);
	Iterable<Producto> findAll();
}
