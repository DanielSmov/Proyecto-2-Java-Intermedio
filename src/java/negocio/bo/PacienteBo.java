/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.bo;

import datos.PacienteDao;
import java.util.List;
import negocio.clases.Paciente;

/**
 *
 * @author Danny
 */
public class PacienteBo {
    private PacienteDao pacienteDao;
    
    public PacienteBo(){
        this.pacienteDao = new PacienteDao();
    }

    public PacienteDao getPacienteDao() {
        return pacienteDao;
    }

    public void setPacienteDao(PacienteDao pacienteDao) {
        this.pacienteDao = pacienteDao;
    }
    
    public int eliminar(Paciente elPac){
        return pacienteDao.eliminar(elPac);
    }
    
    public int insertar(Paciente elPac){
        return pacienteDao.insertar(elPac);
    }
    
    public int modificar(Paciente elPac){
        return pacienteDao.modificar(elPac);
    }
    
    public List<Paciente> consultaTodos(){
        return pacienteDao.consultaTodos();
    }
    
    public List<Paciente> consultarPorNombre(String nombre){
        return pacienteDao.consultarPorNombre(nombre);
    }
    
    public List<Paciente> consultarPorNumAsegurado(String num){
        return pacienteDao.consultarPorNumAsegurado(num);
    }
}
