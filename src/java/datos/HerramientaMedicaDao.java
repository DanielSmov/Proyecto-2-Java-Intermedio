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
import negocio.clases.HerramientaMedica;

/**
 *
 * @author Danny
 */
public class HerramientaMedicaDao {

    private Conexion conn;
    private PreparedStatement sentencia;

    public HerramientaMedicaDao() {
        this.conn = new Conexion();
        this.sentencia = null;
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

    public int eliminar(HerramientaMedica herr) {
        try {
            if (conn.conectarse()) { //si se conecta a la DB
                //sentencia permite efectuar una sentencia en SQL
                //sentencia = conn.getConn().prepareStatement("insert into bibliotecaicai.profesor values (?,?,?,?)");
                sentencia = conn.getConn().prepareStatement("delete from simame.herramienta_medica where codigo  = ? ");

                sentencia.setInt(1, herr.getCodigo());
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

    public int insertar(HerramientaMedica laHer) {
        try {
            if (conn.conectarse()) { //si se logra la conexion con la BD

                sentencia = conn.getConn().prepareStatement("insert into simame.herramienta_medica values (?,?,?,?)");

                //especifica los datos que va a mandar el comando
                sentencia.setInt(1, laHer.getCodigo());
                sentencia.setString(2, laHer.getDescripcion());
                sentencia.setInt(3, laHer.getCantidadTotal());
                sentencia.setInt(4, laHer.getCantidadPrestado());

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

    public int modificar(HerramientaMedica herr) {
        try {
            if (conn.conectarse()) { //si se conecta a la DB

                sentencia = conn.getConn().prepareStatement("update simame.herramienta_medica set descripcion = ?,"
                        + "cantidad_total= ?, cantidad_prestado = ? where codigo = ?");

                //hay que tener cuidado con el orden de los atributos
                sentencia.setString(1, herr.getDescripcion());
                sentencia.setInt(2, herr.getCantidadTotal());
                sentencia.setInt(3, herr.getCantidadPrestado());
                sentencia.setInt(4, herr.getCodigo());
                //ejecuta la sentencia sql 
                sentencia.executeUpdate();
                conn.desconectarse();
                return 0; //todo bien
            } else {
                return 1; //no se conecto a la base de datos

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 2; //algo sucedio
        }
    }

    public List<HerramientaMedica> consultaTodos() {
        try {
            if (conn.conectarse()) { //si se conecta a la DB
             
                sentencia = conn.getConn().prepareStatement("select * from simame.herramienta_medica");
                List lista = new ArrayList();

                //guarda la tabla en resultado
                ResultSet resultado = sentencia.executeQuery();

                //mientras haya un resultado
                while (resultado.next()) {
                    //crea estudiante
                    HerramientaMedica est = new HerramientaMedica();

                    //anade atributos
                    est.setCodigo(resultado.getInt(1));
                    est.setDescripcion(resultado.getString("descripcion"));
                    est.setCantidadTotal(resultado.getInt(3));
                    est.setCantidadPrestado(resultado.getInt(4));

                    //anade estudiante a la lista
                    lista.add(est);
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

    public List<HerramientaMedica> consultarPorCodigo(String codigo) {
        try {
            if (conn.conectarse()) {
                sentencia = conn.getConn().prepareStatement("select * from simame.herramienta_medica where codigo like ?");

                //son para decir q inicie o termine con la palabra q se busca 
                //basta con que contenga 
                sentencia.setString(1, "%" + codigo + "%");

                List lista = new ArrayList();
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    HerramientaMedica her = new HerramientaMedica();
                    her.setCodigo(resultado.getInt(1));
                    her.setDescripcion(resultado.getString("descripcion"));
                    her.setCantidadTotal(resultado.getInt(3));
                    her.setCantidadPrestado(resultado.getInt(4));
                    lista.add(her);
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

    public List<HerramientaMedica> consultarPorDescripcion(String desc) {
        try {
            if (conn.conectarse()) {
                // sentencia = conn.getConn().prepareStatement("select * from bibliotecaicai.profesor");
                sentencia = conn.getConn().prepareStatement("select * from simame.herramienta_medica where descripcion like ?");

                //son para decir q inicie o termine con la palabra q se busca 
                //basta con que contenga 
                sentencia.setString(1, "%" + desc + "%");

                List lista = new ArrayList();
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {

                    HerramientaMedica her = new HerramientaMedica();
                    her.setCodigo(resultado.getInt(1));
                    her.setDescripcion(resultado.getString("descripcion"));
                    her.setCantidadTotal(resultado.getInt(3));
                    her.setCantidadPrestado(resultado.getInt(4));
                    lista.add(her);

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

}
