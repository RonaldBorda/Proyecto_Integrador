package com.tecsup.demo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.demo.models.entity.Producto;

@Repository
public interface IProducto extends JpaRepository<Producto,Integer>{

}
