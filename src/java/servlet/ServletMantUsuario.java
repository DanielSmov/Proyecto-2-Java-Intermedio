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
import negocio.bo.UsuarioBo;
import negocio.clases.Usuario;

/**
 *
 * @author Danny
 */
public class ServletMantUsuario extends HttpServlet {

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
            out.println("<title>Servlet ServletMantUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String accion = request.getParameter("accion");
            UsuarioBo usBo = new UsuarioBo();
            Usuario us = new Usuario();
            
            switch (accion) {
                case "AGREGAR":
                    
                    //llena el profesor con los datos 
                    us.setId(request.getParameter("id"));
                    us.setPassword(request.getParameter("password"));
                    
                    //manda el obj profesor al agregar del BO 
                    int res = usBo.insertar(us);

                    switch (res) {
                        case 0:
                            out.println("<h1>Usuario agregado correctamente</h1>");
                            break;
                        case 1:
                            out.println("<h1>No se pudo conectar correctamente</h1>");
                            break;
                        case 2:
                            out.println("<h1>Ya existe un usuario con esta id</h1>");
                            break;
                        case 3:
                            out.println("<h1>Ocurrio un error inesperado</h1>");
                            break;
                    }
                    break;

                case "MODIFICAR":
                     //llena el profesor con los datos 
                    us.setId(request.getParameter("id"));
                    us.setPassword(request.getParameter("password"));
                    
                    //manda el obj profesor al agregar del BO 
                    res = usBo.modificar(us);

                    switch (res) {
                        case 0:
                            out.println("<h1>Usuario modificado correctamente</h1>");
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
                    us = new Usuario();
                   
                    //llena el profesor con los datos 
                    
                    us.setId(request.getParameter("id"));
                    //manda el obj profesor al agregar del BO 
                    res = usBo.eliminar(us);

                    switch (res) {
                        case 0:
                            out.println("<h1>No se ha eliminado nada</h1>");
                            break;
                        case 1:
                            out.println("<h1>Se elimino el usuario</h1>");
                            break;
                        case 2:
                            out.println("<h1>No se conecto a la base de datos</h1>");
                            break;

                    }
                    break;

                case "SELECCIONAR":
                    //elProfesor = new Profesor();
                    String id = request.getParameter("seleccionado");
                    us = usBo.consultarPorId(id).get(0);
                    
                    request.setAttribute("id", us.getId());
                    request.setAttribute("password", us.getPassword());
                    
                    //se usa para redireccionar a la pag nueva con los datos nuevos
                    request.getRequestDispatcher("MantUsuario.jsp").forward(request, response);
                    break;

                case "CONSID":
                    String id1 = request.getParameter("id");
                    response.sendRedirect("MantUsuario.jsp?CONSID=" + id1);
                    break;

                case "REGRESAR":
                    response.sendRedirect("Inicio.jsp");
                    break;

                case "LIMPIAR":
                    response.sendRedirect("MantUsuario.jsp");
                    break;
                
            }
            
            out.println("<br/>");
            out.println("<a href=\"MantUsuario.jsp\">Volver</a>");
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
