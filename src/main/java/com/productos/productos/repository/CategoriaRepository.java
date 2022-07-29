package com.productos.productos.repository;

import com.productos.productos.models.CategoriaModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository  extends JpaRepository<CategoriaModel,Integer>{
    
}
