/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import negocio.clases.Doctor;

/**
 *
 * @author Danny
 */
public class DoctorDao {

    private Conexion conn;
    private PreparedStatement sentencia;

    public DoctorDao() {
        this.conn = new Conexion();
        this.sentencia = null;
    }

    public int eliminar(Doctor doc) {
        try {
            if (conn.conectarse()) { //si se conecta a la DB
                //sentencia permite efectuar una sentencia en SQL
                //sentencia = conn.getConn().prepareStatement("insert into bibliotecaicai.profesor values (?,?,?,?)");
                sentencia = conn.getConn().prepareStatement("delete from simame.doctor where cedula  = ? ");

                sentencia.setInt(1, doc.getCedula());
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
            return 3; //algo extranno sucedio

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 3; //algo extranno sucedio

        }

    }

    public int insertar(Doctor elDoc) {
        try {
            if (conn.conectarse()) { //si se logra la conexion con la BD

                //manda el comando SQL para insertar datos
                //sentencia = conn.getConn().prepareStatement("insert into bibliotecaicai.profesor values (?,?,?,?)");
                sentencia = conn.getConn().prepareStatement("insert into simame.doctor values (?,?,?,?,?,?,?)");

                //especifica los datos que va a mandar el comando
                sentencia.setInt(1, elDoc.getCedula());
                sentencia.setString(2, elDoc.getNombre());
                sentencia.setString(3, elDoc.getApellido());
                sentencia.setString(4, elDoc.getEspecialidad());
                sentencia.setDouble(5, elDoc.getSalario());
                sentencia.setString(6, elDoc.getDireccion());
                sentencia.setInt(7, elDoc.getTelefono());
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

    public int modificar(Doctor doc) {
        try {
            if (conn.conectarse()) { //si se conecta a la DB
                //sentencia permite efectuar una sentencia en SQL
                sentencia = conn.getConn().prepareStatement(
                        "update simame.doctor set nombre = ?,"
                        + "apellido = ?, especialidad = ?, salario = ?,"
                        + "direccion = ?, telefono = ? where cedula = ?"
                );

                sentencia.setInt(7, doc.getCedula());
                sentencia.setString(1, doc.getNombre());
                sentencia.setString(2, doc.getApellido());
                sentencia.setString(3, doc.getEspecialidad());
                sentencia.setDouble(4, doc.getSalario());
                sentencia.setString(5, doc.getDireccion());
                sentencia.setInt(6, doc.getTelefono());

                int res = sentencia.executeUpdate();
                conn.desconectarse();
                return 0; //todoBien
            } else {
                return 1; //no se conecto a la base de datos

            }

        } catch (SQLIntegrityConstraintViolationException ex) {
            ex.printStackTrace();
            return 2; //algo extranno sucedio

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 3; //algo extranno sucedio

        }

    }

    public List<Doctor> consultaTodos() {
        try {
            if (conn.conectarse()) { //si se conecta a la DB
                //selecciona todo de la tabla estudiante
                //sentencia = conn.getConn().prepareStatement("select * from bibliotecaicai.estudiante");
                sentencia = conn.getConn().prepareStatement("select * from simame.doctor");
                java.util.List lista = new ArrayList();

                //guarda la tabla en resultado
                ResultSet resultado = sentencia.executeQuery();

                //mientras haya un resultado
                while (resultado.next()) {
                    //crea estudiante
                    Doctor doc = new Doctor();

                    //anade atributos
                    doc.setCedula(resultado.getInt(1)); //cedula
                    doc.setNombre(resultado.getString("nombre"));
                    doc.setApellido(resultado.getString("apellido"));
                    doc.setEspecialidad(resultado.getString("especialidad"));
                    doc.setSalario(resultado.getDouble(5)); //salario
                    doc.setDireccion(resultado.getString("direccion"));
                    doc.setTelefono(resultado.getInt(7)); //telefono

                    //anade doctor a la lista
                    lista.add(doc);
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

    public List<Doctor> consultarPorNombre(String nombre) {
        try {
            if (conn.conectarse()) {
                // sentencia = conn.getConn().prepareStatement("select * from bibliotecaicai.profesor");
                sentencia = conn.getConn().prepareStatement("select * from simame.doctor where nombre like ?");

                //son para decir q inicie o termine con la palabra q se busca 
                //basta con que contenga 
                sentencia.setString(1, "%" + nombre + "%");

                List lista = new ArrayList();
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    Doctor doc = new Doctor();

                    doc.setCedula(resultado.getInt(1));
                    doc.setNombre(resultado.getString("nombre"));
                    doc.setApellido(resultado.getString("apellido"));
                    doc.setEspecialidad(resultado.getString("especialidad"));
                    doc.setSalario(resultado.getDouble(5));
                    doc.setDireccion(resultado.getString("direccion"));
                    doc.setTelefono(resultado.getInt(7));
                    lista.add(doc);
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

    public List<Doctor> consultarPorCedula(String cedula) {
        try {
            if (conn.conectarse()) {
                // sentencia = conn.getConn().prepareStatement("select * from bibliotecaicai.profesor");
                sentencia = conn.getConn().prepareStatement("select * from simame.doctor where cedula like ?");

                //son para decir q inicie o termine con la palabra q se busca 
                //basta con que contenga 
                sentencia.setString(1, "%" + cedula + "%");

                List lista = new ArrayList();
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    Doctor doc = new Doctor();
                    doc.setCedula(resultado.getInt(1));
                    doc.setNombre(resultado.getString("nombre"));
                    doc.setApellido(resultado.getString("apellido"));
                    doc.setEspecialidad(resultado.getString("especialidad"));
                    doc.setSalario(resultado.getDouble("salario"));
                    doc.setDireccion(resultado.getString("direccion"));
                    doc.setTelefono(resultado.getInt(7));

                    lista.add(doc);
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
