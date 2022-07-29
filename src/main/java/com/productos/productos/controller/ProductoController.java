package com.productos.productos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.Servlet;
import javax.validation.Valid;

import com.productos.productos.models.CategoriaModel;
import com.productos.productos.models.ProductosModel;
import com.productos.productos.repository.CategoriaRepository;
import com.productos.productos.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
   private  CategoriaRepository categoriaRepository;
   
   @Autowired
   private  ProductoRepository productoRepository;
   
  
   
   
   @PostMapping
   public  ResponseEntity<ProductosModel> guardarProducto(@Valid @RequestBody ProductosModel producto){
       //funcion de guardado
    Optional<CategoriaModel>categoriaOptional=categoriaRepository.findById(producto.getCategoria().getId());
    if(!categoriaOptional.isPresent()){
        return ResponseEntity.unprocessableEntity().build();
    }
    producto.setCategoria(categoriaOptional.get());
    ProductosModel productoGuardado=productoRepository.save(producto);
    URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
    .buildAndExpand(productoGuardado.getId()).toUri();
    return ResponseEntity.created(ubicacion).body(productoGuardado);


   }

   @PutMapping("/{id}")
   public  ResponseEntity<ProductosModel> actualizarProducto(@PathVariable Integer id, @Valid @RequestBody ProductosModel producto){
    Optional<CategoriaModel>categoriaOptional=categoriaRepository.findById(producto.getCategoria().getId());

    if(!categoriaOptional.isPresent()){
        return ResponseEntity.unprocessableEntity().build();
    }
    Optional<ProductosModel> productoOptional=productoRepository.findById(id);
    if(!productoOptional.isPresent()){
        return ResponseEntity.unprocessableEntity().build();
    }
    producto.setCategoria(categoriaOptional.get());
    producto.setId(productoOptional.get().getId());
    productoRepository.save(producto);
    return ResponseEntity.noContent().build();
   }
   
   @DeleteMapping("/{id}")
   public  ResponseEntity<ProductosModel> eliminarProducto(@PathVariable Integer id){
    Optional<ProductosModel> productoOptional= productoRepository.findById(id);
    if(!productoOptional.isPresent()){
        return ResponseEntity.unprocessableEntity().build();
    }
    productoRepository.delete(productoOptional.get());
    return ResponseEntity.noContent().build();
}
@GetMapping
   public ResponseEntity<List<ProductosModel>> listarProductos(Pageable pageable){
       return ResponseEntity.ok(productoRepository.findAll());
      
   }

   @GetMapping("/{id}")
   public  ResponseEntity<ProductosModel> buscarProductoId(@PathVariable Integer id){
      Optional<ProductosModel> productoOptional= productoRepository.findById(id);
      if(!productoOptional.isPresent()){
          return ResponseEntity.unprocessableEntity().build();
      }
      
      return ResponseEntity.ok(productoOptional.get());
  }
   
}
