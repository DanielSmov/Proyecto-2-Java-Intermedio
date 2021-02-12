package negocio.bo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import datos.UsuarioDao;
import java.util.List;
import negocio.clases.Usuario;

/**
 *
 * @author Danny
 */
public class UsuarioBo {

    private UsuarioDao usuarioDao;

    public UsuarioBo() {
        this.usuarioDao = new UsuarioDao();
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public int eliminar(Usuario us) {
        return usuarioDao.eliminar(us);
    }

    public int insertar(Usuario us) {
        return usuarioDao.insertar(us);
    }
    
    public int modificar(Usuario us) {
        return usuarioDao.modificar(us);
    }

    public List<Usuario> consultaTodos() {
        return this.usuarioDao.consultaTodos();
    }
    
    public List<Usuario> consultarPorId(String id) {
        return this.usuarioDao.consultarPorId(id);
    }


}
