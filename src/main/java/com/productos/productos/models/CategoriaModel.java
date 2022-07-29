package com.productos.productos.models;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categorias")
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String nombre;
    private int estado;
    //una  categoria tiene muchos productos
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<ProductosModel> productos = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Set<ProductosModel> getProductos() {
        return productos;
    }

    public void setProductos(Set<ProductosModel> productos) {
        this.productos = productos;
        /*
        for(ProductosModel producto : productos){
            producto.setCategoria(this);

        }
        */
    }

}
