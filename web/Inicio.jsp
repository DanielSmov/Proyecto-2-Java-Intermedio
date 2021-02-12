<%-- 
    Document   : Inicio
    Created on : 09-jun-2020, 18:34:01
    Author     : Danny
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <script type = "text/javascript">
            function accion(valor) {
                document.getElementById("oculto").value = valor;

            }

        </script>

    </head>
    <body>
        <%--Si la session tiene un usuario--%>
        <%if (session.getAttribute("usuario") != null) {%>
        <div style="text-align: right; margin-right: 30px;">
            Hola <%= session.getAttribute("usuario")%>
            <br/>
            <%! SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");%>
            <%! String fecha = formatter.format(new Date());%>
            <%--<%=new Date()%>--%>
            <%=fecha%>

        </div>

        <%--Manda el texto del menu a la pantalla--%>
        <h1>Bienvenido al sistema SIMAME-WEB!</h1>
        <form method="get" action="ServletMenu">
            <input type = "submit" value="Mantenimiento de doctores" onclick="accion(1)"/>
            <input type = "submit" value="Mantenimiento de pacientes" onclick="accion(2)"/>
            <input type = "submit" value="Mantenimiento de herramientas medicas" onclick="accion(3)"/>
            <input type = "submit" value="Mantenimiento de usuarios" onclick="accion(4)"/>
            <input type = "submit" value="Cerrar sesion" onclick="accion(5)"/>
            <input type = "hidden" id = "oculto" name = "boton"/>
        </form>

        <%} else {%>
        <%--Manda el texto de inicio invalido--%>
        <p>Sesion invalida, vuelva a loguearse...</p>
        <a href="Login.html">Ir al Login</a>
        <%}%>
    </body>
</html>
