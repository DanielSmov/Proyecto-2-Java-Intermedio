/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.bo;

import datos.DoctorDao;
import java.util.List;
import negocio.clases.Doctor;

/**
 *
 * @author Danny
 */
public class DoctorBo {

    private DoctorDao doctorDao;

    public DoctorBo() {
        this.doctorDao = new DoctorDao();
    }

    public int modificar(Doctor elDoc) {
        return this.doctorDao.modificar(elDoc);
    }

    public int insertar(Doctor elDoc) {
        return this.doctorDao.insertar(elDoc);
    }

    public int eliminar(Doctor elDoc) {
        return this.doctorDao.eliminar(elDoc);
    }

    public List<Doctor> consultaTodos() {
        return doctorDao.consultaTodos();
    }

    public List<Doctor> consultarPorCedula(String cedula) {
        return doctorDao.consultarPorCedula(cedula);
    }

    public List<Doctor> consultarPorNombre(String nombre) {
        return doctorDao.consultarPorNombre(nombre);
    }

    public DoctorDao getEstudiantDao() {
        return doctorDao;
    }

    public void setEstudiantDao(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

}
