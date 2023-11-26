
package com.proyectofinalargprogetapa2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tecnico {
    
    @Id
    private Integer id;
    private String nombreApellido;

    public Tecnico() {
    }

    public Tecnico(Integer id, String nombreApellido) {
        this.id = id;
        this.nombreApellido = nombreApellido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }
    
    
}
