/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.clases;

/**
 *
 * @author Danny
 */
public class HerramientaMedica {
    private Integer codigo;
    private String descripcion; 
    private Integer cantidadTotal;
    private Integer cantidadPrestado; 

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Integer getCantidadPrestado() {
        return cantidadPrestado;
    }

    public void setCantidadPrestado(Integer cantidadPrestado) {
        this.cantidadPrestado = cantidadPrestado;
    }
    
    
    
}
