package com.proyectofinalargprogetapa2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Orden {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String descripcion;
    private Float costo;
    private Date fecha;
    private Boolean estado;
    
    @ManyToOne
    private Cliente cliente;
    
    private Integer categoria;
    private Integer tecnico;

    public Orden() {
    }

    public Orden(String descripcion, Float costo, Date fecha, Boolean estado, Cliente cliente, Integer categoria, Integer tecnico) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }
    
    
    
}
