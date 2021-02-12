/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.bo;

import datos.HerramientaMedicaDao;
import negocio.clases.HerramientaMedica;
import java.util.List;
/**
 *
 * @author Danny
 */
public class HerramientaMedicaBo {
    private HerramientaMedicaDao herramientaMedicaDao;
    
    public HerramientaMedicaBo(){
        this.herramientaMedicaDao = new HerramientaMedicaDao();
    }

    public HerramientaMedicaDao getHerramientaMedicaDao() {
        return herramientaMedicaDao;
    }

    public void setHerramientaMedicaDao(HerramientaMedicaDao herramientaMedicaDao) {
        this.herramientaMedicaDao = herramientaMedicaDao;
    }
    
    public int eliminar(HerramientaMedica laHer){
        return herramientaMedicaDao.eliminar(laHer);
    }
    
    public int insertar(HerramientaMedica laHer){
        return herramientaMedicaDao.insertar(laHer);
    }
    
    public int modificar(HerramientaMedica laHer){
        return herramientaMedicaDao.modificar(laHer);
    }
    
    public List<HerramientaMedica> consultaTodos(){
       return herramientaMedicaDao.consultaTodos();
    }
    
    public List<HerramientaMedica> consultarPorCodigo(String codigo){
       return herramientaMedicaDao.consultarPorCodigo(codigo);
    }
    
    public List<HerramientaMedica> consultarPorDescripcion(String des){
       return herramientaMedicaDao.consultarPorDescripcion(des);
    }
}
