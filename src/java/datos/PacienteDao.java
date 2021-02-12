/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import negocio.clases.Paciente;

/**
 *
 * @author Danny
 */
public class PacienteDao {

    private Conexion conn;
    private PreparedStatement sentencia;

    public PacienteDao() {
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

    public int eliminar(Paciente pac) {
        try {
            if (conn.conectarse()) { //si se conecta a la DB
                //sentencia permite efectuar una sentencia en SQL
                //sentencia = conn.getConn().prepareStatement("insert into bibliotecaicai.profesor values (?,?,?,?)");
                sentencia = conn.getConn().prepareStatement("delete from simame.paciente where num_asegurado  = ? ");

                sentencia.setInt(1, pac.getNumAsegurado());
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

    public int insertar(Paciente elPac) {
        try {
            if (conn.conectarse()) { //si se logra la conexion con la BD

                //manda el comando SQL para insertar datos
                //sentencia = conn.getConn().prepareStatement("insert into bibliotecaicai.profesor values (?,?,?,?)");
                sentencia = conn.getConn().prepareStatement("insert into simame.paciente values (?,?,?,?,?,?,?,?)");

                //especifica los datos que va a mandar el comando
                sentencia.setInt(1, elPac.getNumAsegurado());
                sentencia.setString(2, elPac.getNombreCompleto());
                sentencia.setString(3, elPac.getDireccion());
                sentencia.setInt(4, elPac.getEdad());
                sentencia.setTimestamp(5, new Timestamp(elPac.getFechaNacimiento().getTime()));
                sentencia.setString(6, elPac.getEmail());
                sentencia.setInt(7, elPac.getTelefono());
                sentencia.setString(8, elPac.getProfesion());

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

    public int modificar(Paciente elPac) {
        try {
            if (conn.conectarse()) { //si se conecta a la DB
                //sentencia permite efectuar una sentencia en SQL
                //sentencia = conn.getConn().prepareStatement("insert into bibliotecaicai.profesor values (?,?,?,?)");
                sentencia = conn.getConn().prepareStatement("update simame.paciente set nombre = ?,"
                        + "direccion = ?, edad = ? , fecha_nacimiento = ?,"
                        + "e_mail = ?, telefono = ? , profesion = ?"
                        + "where num_asegurado = ?");

                //hay que tener cuidado con el orden de los atributos
                sentencia.setString(1, elPac.getNombreCompleto());
                sentencia.setString(2, elPac.getDireccion());
                sentencia.setInt(3, elPac.getEdad());
                sentencia.setTimestamp(4, new Timestamp(elPac.getFechaNacimiento().getTime()));
                sentencia.setString(5, elPac.getEmail());
                sentencia.setInt(6, elPac.getTelefono());
                sentencia.setString(7, elPac.getProfesion());
                sentencia.setInt(8, elPac.getNumAsegurado());
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

    public List<Paciente> consultaTodos() {
        try {
            if (conn.conectarse()) { //si se conecta a la DB
                //selecciona todo de la tabla estudiante
                //sentencia = conn.getConn().prepareStatement("select * from bibliotecaicai.estudiante");
                sentencia = conn.getConn().prepareStatement("select * from simame.paciente");
                List lista = new ArrayList();

                //guarda la tabla en resultado
                ResultSet resultado = sentencia.executeQuery();

                //mientras haya un resultado
                while (resultado.next()) {
                    //crea estudiante

                    Paciente pac = new Paciente();

                    //anade atributos
                    pac.setNumAsegurado(resultado.getInt(1));
                    pac.setNombreCompleto(resultado.getString("nombre"));
                    pac.setDireccion(resultado.getString("direccion"));
                    pac.setEdad(resultado.getInt(4));
                    pac.setFechaNacimiento(resultado.getTimestamp(5));
                    pac.setEmail(resultado.getString("e_mail"));
                    pac.setTelefono(resultado.getInt(7));
                    pac.setProfesion(resultado.getString("profesion"));

                    //anade estudiante a la lista
                    lista.add(pac);
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

    public List<Paciente> consultarPorNombre(String nombre) {
        try {
            if (conn.conectarse()) {
                // sentencia = conn.getConn().prepareStatement("select * from bibliotecaicai.profesor");
                sentencia = conn.getConn().prepareStatement("select * from simame.paciente where nombre like ?");

                //son para decir q inicie o termine con la palabra q se busca 
                //basta con que contenga 
                sentencia.setString(1, "%" + nombre + "%");

                List lista = new ArrayList();
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    Paciente pac = new Paciente();
                    SimpleDateFormat formatter = new SimpleDateFormat();

                    pac.setNumAsegurado(resultado.getInt(1));
                    pac.setNombreCompleto(resultado.getString("nombre"));
                    pac.setDireccion(resultado.getString("direccion"));
                    pac.setEdad(resultado.getInt(4));
                    try {
                        pac.setFechaNacimiento(formatter.parse(resultado.getString("fecha_nacimiento")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    pac.setEmail(resultado.getString("e_mail"));
                    pac.setTelefono(resultado.getInt(7));
                    pac.setProfesion(resultado.getString("profesion"));
                    lista.add(pac);
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

    public List<Paciente> consultarPorNumAsegurado(String num) {
        try {
            if (conn.conectarse()) {
                // sentencia = conn.getConn().prepareStatement("select * from bibliotecaicai.profesor");
                sentencia = conn.getConn().prepareStatement("select * from simame.paciente where num_asegurado like ?");

                //son para decir q inicie o termine con la palabra q se busca 
                //basta con que contenga 
                sentencia.setString(1, "%" + num + "%");

                List lista = new ArrayList();
                ResultSet resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    Paciente pac = new Paciente();
                    SimpleDateFormat formatter = new SimpleDateFormat();

                    pac.setNumAsegurado(resultado.getInt(1));
                    pac.setNombreCompleto(resultado.getString("nombre"));
                    pac.setDireccion(resultado.getString("direccion"));
                    pac.setEdad(resultado.getInt(4));
                    try {
                        pac.setFechaNacimiento(formatter.parse(resultado.getString("fecha_nacimiento")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    pac.setEmail(resultado.getString("e_mail"));
                    pac.setTelefono(resultado.getInt(7));
                    pac.setProfesion(resultado.getString("profesion"));
                    lista.add(pac);
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
