package com.productos.productos.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.Servlet;
import javax.validation.Valid;

import com.productos.productos.models.CategoriaModel;
import com.productos.productos.repository.CategoriaRepository;

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
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private  CategoriaRepository categoriaRepository;

    @GetMapping
   public ResponseEntity<List<CategoriaModel>> listarCategoria(Pageable pageable){
       return ResponseEntity.ok(categoriaRepository.findAll());
      
   }

   @PostMapping
   public  ResponseEntity<CategoriaModel> guardarCategoria(@Valid @RequestBody CategoriaModel categoria){
       //funcion de guardado
       CategoriaModel categoriaGuardado= categoriaRepository.save(categoria);
    URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
    .buildAndExpand(categoriaGuardado.getId()).toUri();
    return ResponseEntity.created(ubicacion).body(categoriaGuardado);
   }
   @PutMapping("/{id}")
   public  ResponseEntity<CategoriaModel> actualizarCategoria(@PathVariable Integer id, @Valid @RequestBody CategoriaModel categoria){
       Optional<CategoriaModel> categoriaOptional= categoriaRepository.findById(id);

       if(!categoriaOptional.isPresent()){
           return ResponseEntity.unprocessableEntity().build();
       }

       categoria.setId(categoriaOptional.get().getId());
       categoriaRepository.save(categoria);
       return ResponseEntity.noContent().build();

   }

   @DeleteMapping("/{id}")
   public  ResponseEntity<CategoriaModel> eliminarCategoria(@PathVariable Integer id){
    Optional<CategoriaModel> categoriaOptional= categoriaRepository.findById(id);
    if(!categoriaOptional.isPresent()){
        return ResponseEntity.unprocessableEntity().build();
    }
    categoriaRepository.delete(categoriaOptional.get());
    return ResponseEntity.noContent().build();
}

@GetMapping("/{id}")
 public  ResponseEntity<CategoriaModel> buscarCategoriaId(@PathVariable Integer id){
    Optional<CategoriaModel> categoriaOptional= categoriaRepository.findById(id);
    if(!categoriaOptional.isPresent()){
        return ResponseEntity.unprocessableEntity().build();
    }
    
    return ResponseEntity.ok(categoriaOptional.get());
}
    
    
}
