/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.bo.PacienteBo;
import negocio.clases.Paciente;

/**
 *
 * @author Danny
 */
public class ServletMantPaciente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mantenimiento de Pacientes</title>");
            out.println("</head>");
            out.println("<body>");

            //accion es el nombre del hidden
            String accion = request.getParameter("accion");

            //paciente y pacienteBO
            Paciente pac = new Paciente();
            PacienteBo pacBo = new PacienteBo();

            switch (accion) {
                case "AGREGAR":
                    //se usa el name del input
                    pac = new Paciente();
                    String date = request.getParameter("fechaNacimiento");
                    try {
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                        pac.setFechaNacimiento(date1); //pendiente
                    } catch (ParseException ex) {
                        System.out.println("Error con fecha");
                    }
                    //llena el profesor con los datos 
                    pac.setNumAsegurado(Integer.parseInt(request.getParameter("numeroAsegurado")));
                    pac.setNombreCompleto(request.getParameter("nombreCompleto"));
                    pac.setDireccion(request.getParameter("direccion"));
                    pac.setEdad(Integer.parseInt(request.getParameter("edad")));
                    // pac.setFechaNacimiento(request.getParameter("fechaNacimiento"));
                    pac.setEmail(request.getParameter("email"));
                    pac.setTelefono(Integer.parseInt(request.getParameter("telefono")));
                    pac.setProfesion(request.getParameter("profesion"));

                    //manda el obj profesor al agregar del BO 
                    int res = pacBo.insertar(pac);

                    switch (res) {
                        case 0:
                            out.println("<h1>Paciente agregado correctamente</h1>");
                            break;
                        case 1:
                            out.println("<h1>No se pudo conectar correctamente</h1>");
                            break;
                        case 2:
                            out.println("<h1>Ya existe un paciente con este numero de asegurado</h1>");
                            break;
                        case 3:
                            out.println("<h1>Ocurrio un error inesperado</h1>");
                            break;
                    }
                    break;

                case "MODIFICAR":
                    //se usa el name del input
                    String dateMod = request.getParameter("fechaNacimiento");
                    try {
                        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(dateMod);
                        pac.setFechaNacimiento(date1); //pendiente
                    } catch (ParseException ex) {
                        System.out.println("Error con fecha");
                    }

                    pac.setNumAsegurado(Integer.parseInt(request.getParameter("numeroAsegurado")));
                    pac.setNombreCompleto(request.getParameter("nombreCompleto"));
                    pac.setDireccion(request.getParameter("direccion"));
                    pac.setEdad(Integer.parseInt(request.getParameter("edad")));
                    //pac.setFechaNacimiento(request.getParameter("fechaNacimiento"));
                    pac.setEmail(request.getParameter("email"));
                    pac.setTelefono(Integer.parseInt(request.getParameter("telefono")));
                    pac.setProfesion(request.getParameter("profesion"));

                    //manda el obj profesor al agregar del BO 
                    res = pacBo.modificar(pac);
                    switch (res) {
                        case 0:
                            out.println("<h1>Paciente modificado correctamente</h1>");
                            break;
                        case 1:
                            out.println("<h1>No se pudo conectar correctamente</h1>");
                            break;
                        case 2:
                            out.println("<h1>Ocurrio un error inesperado</h1>");
                            break;
                    }
                    break;

                case "ELIMINAR":
                    //se usa el name del input

                    pac = new Paciente();
                    //llena el profesor con los datos 

                    pac.setNumAsegurado(Integer.parseInt(request.getParameter("numeroAsegurado")));
                    //manda el obj profesor al agregar del BO 
                    res = pacBo.eliminar(pac);

                    switch (res) {
                        case 0:
                            out.println("<h1>No se ha eliminado nada</h1>");
                            break;
                        case 1:
                            out.println("<h1>Se elimino el paciente</h1>");
                            break;
                        case 2:
                            out.println("<h1>No se conecto a la base de datos</h1>");
                            break;

                    }
                    break;

                case "SELECCIONAR":
                    //elProfesor = new Profesor();
                    Integer num = Integer.parseInt(request.getParameter("seleccionado"));

                    //String ced = request.getParameter("seleccionado");
                    pac = pacBo.consultarPorNumAsegurado(num + "").get(0);
                    //cuando se mandan cosas al JSP, se usa el setAttribute

                    request.setAttribute("numeroAsegurado", pac.getNumAsegurado());
                    request.setAttribute("nombreCompleto", pac.getNombreCompleto());
                    request.setAttribute("direccion", pac.getDireccion());
                    request.setAttribute("edad", pac.getEdad());
                    request.setAttribute("fechaNacimiento", pac.getFechaNacimiento());
                    request.setAttribute("email", pac.getEmail());
                    request.setAttribute("telefono", pac.getTelefono());
                    request.setAttribute("profesion", pac.getProfesion());

                    //se usa para redireccionar a la pag nueva con los datos nuevos
                    request.getRequestDispatcher("MantPaciente.jsp").forward(request, response);
                    break;

                case "CONSNUM":
                    String numAs = request.getParameter("numeroAsegurado");
                    response.sendRedirect("MantPaciente.jsp?CONSNUM=" + numAs);
                    break;

                case "CONSNOMBRE":
                    String nombre = request.getParameter("nombreCompleto");
                    response.sendRedirect("MantPaciente.jsp?CONSNOMBRE=" + nombre);
                    break;

                case "REGRESAR":
                    response.sendRedirect("Inicio.jsp");
                    break;

                case "LIMPIAR":
                    response.sendRedirect("MantPaciente.jsp");
                    break;

            }

            out.println("<br/>");
            out.println("<a href=\"MantPaciente.jsp\">Volver</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
