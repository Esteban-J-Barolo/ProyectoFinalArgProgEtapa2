package com.proyectofinalargprogetapa2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Orden {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String descripcion;
    private Float costo;
    private LocalDate fecha;
    private Boolean estado;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Categoria categoria;
    
    @ManyToOne
    private Tecnico tecnico;

    public Orden() {
    }

    public Orden(String descripcion, Float costo, LocalDate fecha, Boolean estado, Cliente cliente, Categoria categoria, Tecnico tecnico) {
        this.descripcion = descripcion;
        this.costo = costo;
        this.fecha = fecha;
        this.estado = estado;
        this.cliente = cliente;
        this.categoria = categoria;
        this.tecnico = tecnico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

   
    
    
    
}
