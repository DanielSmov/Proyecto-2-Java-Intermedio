/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import negocio.clases.Doctor;
import negocio.clases.Usuario;

/**
 *
 * @author Danny
 */
public class UsuarioDao {

    private Conexion conn;
    private PreparedStatement sentencia;

    public UsuarioDao() {
        this.conn = new Conexion();
        this.sentencia = null;
    }

    public int eliminar(Usuario us) {
        try {
            if (conn.conectarse()) { //si se conecta a la DB
                //sentencia permite efectuar una sentencia en SQL
                //sentencia = conn.getConn().prepareStatement("insert into bibliotecaicai.profesor values (?,?,?,?)");
                sentencia = conn.getConn().prepareStatement("delete from simame.usuario where usuario  = ? ");

                sentencia.setString(1, us.getId());
                int res = sentencia.executeUpdate();
                conn.desconectarse();
                if (res == 0) {
                    return 0;
                    //se ejecuto la sentencia pero no se elimino nada
                } else {
                    return 1; //se eliminp bien el registo
                }
            } else {
                return 2; //no se conecto a la base de datos

            }

        } catch (SQLIntegrityConstraintViolationException ex) {
            ex.printStackTrace();
            return 4; //algo extranno sucedio

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 3; //algo extranno sucedio

        }

    }

    public int insertar(Usuario us) {
        try {
            if (conn.conectarse()) { //si se logra la conexion con la BD

                //manda el comando SQL para insertar datos
                //sentencia = conn.getConn().prepareStatement("insert into bibliotecaicai.profesor values (?,?,?,?)");
                sentencia = conn.getConn().prepareStatement("insert into simame.usuario values (?,?)");

                //especifica los datos que va a mandar el comando
                sentencia.setString(1, us.getId());
                sentencia.setString(2, us.getPassword());

                //ejecuta la sentencia con los datos actualizados
                sentencia.executeUpdate();

                //se desconecta de la BD
                conn.desconectarse();
                return 0;

            } else { //si no se logra la conexion con la base de datos
                return 1;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            if (sqle.getSQLState().startsWith("23")) {
                return 2; //llave PK duplicada
            } else {
                return 3; //error ejecutando el insert
            }
        }

    }

    public int modificar(Usuario us) {
        try {
            if (conn.conectarse()) { //si se conecta a la DB
                //sentencia permite efectuar una sentencia en SQL
                //sentencia = conn.getConn().prepareStatement("insert into bibliotecaicai.profesor values (?,?,?,?)");
                sentencia = conn.getConn().prepareStatement("update simame.usuario set password = ?,"
                        + "where usuario = ?");

                //hay que tener cuidado con el orden de los atributos
                sentencia.setString(1, us.getId());
                sentencia.setString(2, us.getPassword());

                int res = sentencia.executeUpdate();
                conn.desconectarse();
                if (res == 0) {
                    return 0;
                    //se ejecuto la sentencia pero no se elimino nada
                } else {
                    return 1; //se eliminp bien el registo
                }
            } else {
                return 2; //no se conecto a la base de datos

            }

        } catch (SQLIntegrityConstraintViolationException ex) {
            ex.printStackTrace();
            return 4; //algo extranno sucedio

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 3; //algo extranno sucedio

        }

    }
  
    public List<Usuario> consultaTodos() {
        try {
            if (conn.conectarse()) { //si se conecta a la DB
                //selecciona todo de la tabla estudiante
                //sentencia = conn.getConn().prepareStatement("select * from bibliotecaicai.estudiante");
                sentencia = conn.getConn().prepareStatement("select * from simame.usuario");
                java.util.List lista = new ArrayList();

                //guarda la tabla en resultado
                ResultSet resultado = sentencia.executeQuery();

                //mientras haya un resultado
                while (resultado.next()) {
                    //crea estudiante
                    Usuario us = new Usuario();
                   

                    //anade atributos
                    us.setId(resultado.getString("usuario"));
                    us.setPassword(resultado.getString("password"));
                
                    //anade doctor a la lista
                    lista.add(us);
                }
                //termina la conexion
                this.conn.desconectarse();
                //devuelve la lista
                return lista;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> consultarPorId(String id) {
        try {
            if (conn.conectarse()) {
                // sentencia = conn.getConn().prepareStatement("select * from bibliotecaicai.profesor");
                sentencia = conn.getConn().prepareStatement("select * from simame.usuario where usuario like ?");

                //son para decir q inicie o termine con la palabra q se busca 
                //basta con que contenga 
                sentencia.setString(1, "%" + id + "%");

                List lista = new ArrayList();
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    Usuario us = new Usuario();
                    us.setId(resultado.getString("usuario"));
                    us.setPassword(resultado.getString("password"));
                    lista.add(us);
                }
                this.conn.desconectarse();
                return lista;
            } else {
                return null; // retorna null si no se conecta
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public Conexion getConn() {
        return conn;
    }

    public void setConn(Conexion conn) {
        this.conn = conn;
    }

    public PreparedStatement getSentencia() {
        return sentencia;
    }

    public void setSentencia(PreparedStatement sentencia) {
        this.sentencia = sentencia;
    }

}
