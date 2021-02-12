/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.clases;

import java.util.Date;

/**
 *
 * @author Danny
 */
public class Paciente {
    private Integer numAsegurado;
    private String nombreCompleto;
    private String direccion;
    private Integer edad;
    private Date fechaNacimiento; 
    private String email;
    private Integer telefono;
    private String profesion; 
 
    public Paciente(){
        this.numAsegurado = 0;
        this.nombreCompleto = "";
        this.direccion = "";
        this.edad = 0;
        this.fechaNacimiento = new Date();
        this.email = "";
        this.telefono = 0;
        this.profesion = "";
    }

    public Integer getNumAsegurado() {
        return numAsegurado;
    }

    public void setNumAsegurado(Integer numAsegurado) {
        this.numAsegurado = numAsegurado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    
    
    
}
