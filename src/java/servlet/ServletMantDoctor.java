/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.bo.DoctorBo;
import negocio.clases.Doctor;

/**
 *
 * @author Danny
 */
public class ServletMantDoctor extends HttpServlet {

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
            out.println("<title>Mantenimiento de Doctores</title>");
            out.println("</head>");
            out.println("<body>");

            //Accion lleva el valor de hidden
            String accion = request.getParameter("accion");

            //Doctor Bo
            DoctorBo doctorBo = new DoctorBo();
            Doctor doc = new Doctor();

            switch (accion) {
                case "AGREGAR":
                    //inicializa el obj doctor
                    doc = new Doctor();
                    //dota el obj con datos
                    doc.setCedula(Integer.parseInt(request.getParameter("cedula")));
                    doc.setNombre(request.getParameter("nombre"));
                    doc.setApellido(request.getParameter("apellido"));
                    doc.setEspecialidad(request.getParameter("especialidad"));
                    doc.setSalario(Double.parseDouble(request.getParameter("salario")));
                    doc.setDireccion(request.getParameter("direccion"));
                    doc.setTelefono(Integer.parseInt(request.getParameter("telefono")));

                    //manda el objeto por las capas hasta llegar a la base de datos
                    int res = doctorBo.insertar(doc);

                    //el switch decide la accion segun el resultado de la operacion
                    switch (res) {
                        case 0:
                            out.println("<h1>Doctor agregado correctamente</h1>");

                            break;
                        case 1:
                            out.println("<h1>No se pudo conectar correctamente</h1>");
                            break;
                        case 2:
                            out.println("<h1>Ya existe un doctor con esta cedula</h1>");
                            break;
                        case 3:
                            out.println("<h1>Ocurrio un error inesperado</h1>");
                            break;
                    }
                    break;
                case "MODIFICAR":
                    doc = new Doctor();
                    //dota el obj con datos
                    doc.setCedula(Integer.parseInt(request.getParameter("cedula")));
                    doc.setNombre(request.getParameter("nombre"));
                    doc.setApellido(request.getParameter("apellido"));
                    doc.setEspecialidad(request.getParameter("especialidad"));
                    doc.setSalario(Double.parseDouble(request.getParameter("salario")));
                    doc.setDireccion(request.getParameter("direccion"));
                    doc.setTelefono(Integer.parseInt(request.getParameter("telefono")));

                    //manda el objeto por las capas hasta llegar a la base de datos
                    res = doctorBo.modificar(doc);

                    //el switch decide la accion segun el resultado de la operacion
                    switch (res) {
                        case 0:
                            out.println("<h1>Doctor modificado correctamente</h1>");
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
                    doc = new Doctor();
                    //dota el obj con datos
                    doc.setCedula(Integer.parseInt(request.getParameter("cedula")));

                    //manda el objeto por las capas hasta llegar a la base de datos
                    res = doctorBo.eliminar(doc);

                    //el switch decide la accion segun el resultado de la operacion
                    switch (res) {
                        case 0:
                            out.println("<h1>No se ha eliminado nada</h1>");
                            break;
                        case 1:
                            out.println("<h1>Se elimino el doctor</h1>");
                            break;
                        case 2:
                            out.println("<h1>No se conecto a la base de datos</h1>");
                            break;
                    }
                    break;

                case "SELECCIONAR":
                    //selecciona la cedula del doctor
                    Integer ced = Integer.parseInt(request.getParameter("seleccionado"));
                    //asigna al obj doc el resultado de la consulta
                    doc = doctorBo.consultarPorCedula(ced + "").get(0);

                    //pide permiso para mandar datos al JSP usando setAttribute
                    request.setAttribute("cedula", doc.getCedula());
                    request.setAttribute("nombre", doc.getNombre());
                    request.setAttribute("apellido", doc.getApellido());
                    request.setAttribute("especialidad", doc.getEspecialidad());
                    request.setAttribute("salario", doc.getSalario());
                    request.setAttribute("direccion", doc.getDireccion());
                    request.setAttribute("telefono", doc.getTelefono());

                    //manda datos al JSP usnado getRequestDispatcher
                    request.getRequestDispatcher("MantDoctor.jsp").forward(request, response);

                    break;
                case "CONSCEDULA":
                    //obtiene valor de cedula
                    String cedula = request.getParameter("cedula");

                    //manda el doctor con la cedula correspondiente
                    response.sendRedirect("MantDoctor.jsp?CONSCEDULA=" + cedula);
                    break;
                case "CONSNOMBRE":
                    //obtiene valor de nombre
                    String nombre = request.getParameter("nombre");

                    //manda el doctor con la cedula correspondiente
                    response.sendRedirect("MantDoctor.jsp?CONSNOMBRE=" + nombre);

                    break;
                case "REGRESAR":
                    //devuelve al menu principal
                    response.sendRedirect("Inicio.jsp");
                    break;
                case "LIMPIAR":
                    //vuelve a cargar la pagina
                    response.sendRedirect("MantDoctor.jsp");
                    break;

            }
            out.println("<br/>");
            out.println("<a href=\"MantDoctor.jsp\">Volver</a>");
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
