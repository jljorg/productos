package com.productos.productos.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;



@Entity
//el nombre de la tabla en la base de datos
//ademas hicimos una validacion que el nombre es unico
@Table(name="productos",uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class ProductosModel {

    @Id
    @GeneratedValue( strategy =   GenerationType.IDENTITY)  
    private int id; 

    @NotNull
    private String nombre;

    private int estado;
    //lazy es para que cada vez que lo llamemmos nos muestre valores de resto no
    //muchos productos tienen una categoria o pueden tener una categoria 
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    //hacemos el join con la columna categoria
    @JoinColumn(name="categoria")
    //se pone el acesso que solo es de escritura
    @JsonProperty(access = Access.WRITE_ONLY)
    private CategoriaModel categoria;
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
    public CategoriaModel getCategoria() {
        return categoria;
    }
    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }
    
    
}
