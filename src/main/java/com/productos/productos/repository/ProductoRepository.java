package com.productos.productos.repository;

import com.productos.productos.models.ProductosModel;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductosModel,Integer> {

    
    
}
