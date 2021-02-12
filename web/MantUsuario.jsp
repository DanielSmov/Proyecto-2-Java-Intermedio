<%-- 
    Document   : MantUsuario
    Created on : 05-jun-2020, 19:56:05
    Author     : Danny
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="negocio.bo.UsuarioBo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocio.clases.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento Usuario</title>

        <script type = "text/javascript">
            function validarDatos() {
                //valida datos
                //como todos son strings, nada mas hay q revisar q los campos
                //esten llenos. Buscar un validar con JS.  

                var id = document.getElementById("txtId").value;
                var pass = document.getElementById("txtPassword").value;

                //valida id
                if (id === "") {
                    alert("debe ingresar id");
                    return false;
                }
                //valida password
                if (pass === "") {
                    alert("debe ingresar password");
                    return false;
                }

                return true;
            }
            function agregar() {
                if (validarDatos()) {
                    //asigna al valor de oculto "AGREGAR"
                    document.getElementById("oculto").value = "AGREGAR";
                    return true;
                } else {
                    return false;
                }
            }
            function modificar() {
                if (validarDatos()) {
                    //asigna al valor de oculto "MODIFICAR"
                    document.getElementById("oculto").value = "MODIFICAR";
                    return true;
                } else {
                    return false;
                }
            }
            function eliminar() {
                if (validarDatos()) {
                    //asigna al valor de oculto "ELIMINAR"
                    document.getElementById("oculto").value = "ELIMINAR";
                    return true;
                } else {
                    return false;
                }
            }
            function consId() {
                var nombre = document.getElementById("txtId").value;

                if (nombre === "") {
                    alert("Debe ingresar una id a buscar");
                    return false;
                } else {
                    document.getElementById("oculto").value = "CONSID";
                    return true;
                }

            }
            function seleccionar(valor) {
                document.getElementById("oculto").value = "SELECCIONAR";
                document.getElementById("seleccionado").value = valor;
            }
            function limpiar() {
                document.getElementById("oculto").value = "LIMPIAR";
            }
            function regresar() {
                document.getElementById("oculto").value = "REGRESAR";
            }

        </script>

    </head>
    <body>
        <%--Deja los espacios en blanco--%>
        <% if (session.getAttribute("usuario") != null) {

                if (request.getAttribute("id") == null) {
                    request.setAttribute("id","");
                }
                if (request.getAttribute("password") == null) {
                    request.setAttribute("password","");
                }
        %>

        <%--Muestra la fecha--%>
        <div style="text-align: right; margin-right: 30px;">
            Hola <%= session.getAttribute("usuario")%>
            <br/>
            <%! SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");%>
            <%! String fecha = formatter.format(new Date());%>
            <%--<%=new Date()%>--%>
            <%=fecha%>
           
        </div>

        <%--Titulo de la pagina--%>
        <h1>Mantenimiento Usuario</h1>

        <%--Formulario de usuario--%>
        <form method="get" action = "ServletMantUsuario">

            <%--crea la tabla--%>
            <table border="1">
                <tr>
                    <%--crea la fila para password--%>
                    <td>Id</td>
                    <td>
                        <%--crea input para id--%>
                        <%if (request.getAttribute("id") != "") {%>
                        <input type="text" id="txtId" name="id" readonly ="true"
                               value ="<%= request.getAttribute("id")%>" />
                        <%} else {%>
                        <input type="text" id="txtId" name="id"
                               value ="<%= request.getAttribute("id")%>" />
                        <%}%>
                    </td>
                </tr>

                <tr>
                    <%--crea la fila para password--%>
                    <td>Password</td>
                    <td>
                        <%--crea input para password--%>
                        <input type="text" id="txtPassword" name="password"
                               value ="<%= request.getAttribute("password")%>"
                               />
                    </td>
                </tr>

            </table>
            <br/>

            <%--Declara botones para mantenimiento --%>
            <input type="submit" value ="Agregar" onclick="return agregar();"/>
            <input type="submit" value ="Modificar" onclick="return modificar();"/>
            <input type="submit" value ="Eliminar" onclick="return eliminar();"/>
            <input type="submit" value ="Consulta por id" onclick="return consId();"/>
            <input type="submit" value ="Regresar" onclick="return regresar();"/>
            <input type="submit" value ="Limpiar" onclick="return limpiar();"/>


            <%--El hidden, oculta la opcion q se selecciono--%>
            <input type = "hidden" id = "oculto" name = "accion"/>
            <input type = "hidden" id = "seleccionado" name = "seleccionado"/>
            <hr/>

            <%--Crea tabla para mostrar datos--%>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Password</th>
                    <th>Seleccionar</th>
                </tr>
                <%--Crea lista y usuarioBo para llamar los datos desde la BD--%>

                <%! List<Usuario> listaUs = new ArrayList();%>
                <%! UsuarioBo usBo = new UsuarioBo();%>


                <%
                    //prueba si el parametro de busqueda por cedula esta

                    //entra el caso de si hay busqueda por nombre
                    if (request.getParameter("CONSID") != null) {
                        String id = request.getParameter("CONSID");
                        this.listaUs = usBo.consultarPorId(id); 
                        
                        
                        if (this.listaUs.isEmpty()) {
                            out.println("<h4>No se encontro el usuario esa id.</h4>");
                        }
                    } else {
                        this.listaUs = usBo.consultaTodos();
                    }

                    //this.listaProfes = profeBo.consultaTodos();
                    for (int i = 0; i < listaUs.size(); i++) {
                        Usuario usu = listaUs.get(i);
                        //datos de id
                        out.println("<tr>"); //abre la fila
                        
                        out.println("<td>");
                        out.println(usu.getId());
                        out.println("</td>");

                        //datos de password
                        out.println("<td>");
                        out.println(usu.getPassword());
                        out.println("</td>");

                    
                %>
                <td>

                    <input type = "image" src = "img/seleccione.PNG" width = "25px"
                           onclick = "return seleccionar(<%=usu.getId()%>)"/>

                </td>
                <%
                        out.println("</tr>"); //cierra la fila
                    }
                %>
            </table>

        </form>
        <%} else {%>
        <p>Sesion invalida, vuelva a loguearse...</p>
        <a href="Login.html">Ir al Login</a>
        <%}%>


    </body>
</html>
