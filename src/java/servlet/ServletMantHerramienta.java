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
import negocio.bo.HerramientaMedicaBo;
import negocio.clases.HerramientaMedica;

/**
 *
 * @author Danny
 */
public class ServletMantHerramienta extends HttpServlet {

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
            out.println("<title>Manteniminento de Herramientas Medicas</title>");
            out.println("</head>");
            out.println("<body>");

            //accion es el nombre del parametro oculto
            String accion = request.getParameter("accion");

            //HerramientaMedica y HerramientaMEdicaBo
            HerramientaMedicaBo herramientaBo = new HerramientaMedicaBo();
            HerramientaMedica herr = new HerramientaMedica();

            switch (accion) {
                case "AGREGAR":
                    //se usa el name del input
                    herr = new HerramientaMedica();

                    //llena el profesor con los datos 
                    herr.setCodigo(Integer.parseInt(request.getParameter("codigo")));
                    herr.setDescripcion(request.getParameter("descripcion"));
                    herr.setCantidadTotal(Integer.parseInt(request.getParameter("cantidadTotal")));
                    herr.setCantidadPrestado(Integer.parseInt(request.getParameter("cantidadPrestado")));

                    //manda el obj herramienta al agregar del BO 
                    int res = herramientaBo.insertar(herr);

                    switch (res) {
                        case 0:
                            out.println("<h1>Herramienta agregada correctamente</h1>");
                            break;
                        case 1:
                            out.println("<h1>No se pudo conectar correctamente</h1>");
                            break;
                        case 2:
                            out.println("<h1>Ya existe una herramienta con ese codigo</h1>");
                            break;
                        case 3:
                            out.println("<h1>Ocurrio un error inesperado</h1>");
                            break;
                    }
                    break;

                case "MODIFICAR":
                    //se usa el name del input
                    herr = new HerramientaMedica();

                    //llena el profesor con los datos 
                    herr.setCodigo(Integer.parseInt(request.getParameter("codigo")));
                    herr.setDescripcion(request.getParameter("descripcion"));
                    herr.setCantidadTotal(Integer.parseInt(request.getParameter("cantidadTotal")));
                    herr.setCantidadPrestado(Integer.parseInt(request.getParameter("cantidadPrestado")));

                    //manda el obj herramienta al agregar del BO 
                    res = herramientaBo.modificar(herr);

                    switch (res) {
                        case 0:
                            out.println("<h1>Herramienta modificada correctamente</h1>");
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

                    herr = new HerramientaMedica();
                    //llena el profesor con los datos
                    herr.setCodigo(Integer.parseInt(request.getParameter("codigo")));
                    //manda el obj herramienta al agregar del BO 
                    res = herramientaBo.eliminar(herr);

                    switch (res) {
                        case 0:
                            out.println("<h1>No se ha eliminado nada</h1>");
                            break;
                        case 1:
                            out.println("<h1>Se elimino la herramienta</h1>");
                            break;
                        case 2:
                            out.println("<h1>No se conecto a la base de datos</h1>");
                            break;

                    }
                    break;

                case "SELECCIONAR":
                    //elProfesor = new Profesor();

                    Integer cod = Integer.parseInt(request.getParameter("seleccionado"));
                    //String ced = request.getParameter("seleccionado");

                    herr = herramientaBo.consultarPorCodigo(cod + "").get(0);
                    //cuando se mandan cosas al JSP, se usa el setAttribute

                    request.setAttribute("codigo", herr.getCodigo());
                    request.setAttribute("descripcion", herr.getDescripcion());
                    request.setAttribute("cantidadTotal", herr.getCantidadTotal());
                    request.setAttribute("cantidadPrestado", herr.getCantidadPrestado());

                    //se usa para redireccionar a la pag nueva con los datos nuevos
                    request.getRequestDispatcher("MantHerramienta.jsp").forward(request, response);
                    break;

                case "CONSCODIGO":
                    String codigo = request.getParameter("codigo");
                    response.sendRedirect("MantHerramienta.jsp?CONSCODIGO=" + codigo);
                    break;

                case "CONSDESC":
                    String desc = request.getParameter("descripcion");
                    response.sendRedirect("MantHerramienta.jsp?CONSDESC=" + desc);

                    break;
                case "REGRESAR":
                    response.sendRedirect("Inicio.jsp");
                    break;

                case "LIMPIAR":
                    response.sendRedirect("MantHerramienta.jsp");
                    break;

            }
            out.println("<br/>");
            out.println("<a href=\"MantHerramienta.jsp\">Volver</a>");
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
