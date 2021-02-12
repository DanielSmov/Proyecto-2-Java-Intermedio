/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import negocio.bo.UsuarioBo;
import negocio.clases.Usuario;

/**
 *
 * @author Danny
 */
public class ServletLogin extends HttpServlet {

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

            //obtiene datos de la pag
            String us = request.getParameter("usuario");
            String pass = request.getParameter("password");

            //crea usuario y usuarioBo
            //Usuario usuario = new Usuario();
            UsuarioBo usuBo = new UsuarioBo();

            //consulta con la id en la BD
            //usuario = usuBo.consultarPorId(us).get(0);

            List<Usuario> usuLis = usuBo.consultarPorId(us);
            
            //crea una sesion, donde se guardan los datos del cliente
            HttpSession session = request.getSession();

            if (!(usuLis.isEmpty())) {
                if (usuLis.get(0).getPassword().equals(pass)) {
                    //entra la secion y manda a inicio
                    session.setAttribute("usuario", us);
                    response.sendRedirect("Inicio.jsp");
                } else {
                    //no entra, manda pag de error
                    response.sendRedirect("LoginErroneo.html");
                }
            } else {
                response.sendRedirect("LoginErroneo.html");
            }

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
