<%-- 
    Document   : MantHerramienta
    Created on : 05-jun-2020, 19:55:45
    Author     : Danny
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="negocio.bo.HerramientaMedicaBo"%>
<%@page import="negocio.clases.HerramientaMedica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento Herramienta Medica</title>

        <script type = "text/javascript">
            function validarDatos() {
                //valida datos
                //como todos son strings, nada mas hay q revisar q los campos
                //esten llenos. Buscar un validar con JS.  

                var cod = document.getElementById("txtCodigo").value;
                var des = document.getElementById("txtDescripcion").value;
                var cantTotal = document.getElementById("txtCantidadTotal").value;
                var cantPres = document.getElementById("txtCantidadPrestado").value;

                //valida codigo
                if (cod === "") {
                    if (isNaN(cod)) {
                        alert("codigo no es numero");
                        return false;
                    }
                    alert("debe ingresar codigo");
                    return false;
                }
                //valida descripcion
                if (des === "") {
                    alert("debe ingresar descripcion");
                    return false;
                }

                //valida cantidad total
                if (cantTotal === "") {
                    if (isNaN(cantTotal)) {
                        alert("cantidad total no es numero");
                        return false;
                    }
                    alert("debe ingresar cantidad total");
                    return false;
                }

                //valida cantidad prestados
                if (cantPres === "") {
                    if (isNaN(cantPres)) {
                        alert("cantidad prestados no es numero");
                        return false;
                    }
                    alert("debe ingresar cantidad prestados");
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

            function consCodigo() {
                //guarda codigo 
                var codigo = document.getElementById("txtCodigo").value;
                //verifica que el codigo no venga vacio
                if (codigo === "") {
                    alert("debe ingresar codigo");
                } else {
                    //verifica q el codigo sea un numero
                    if (!isNaN(codigo)) {
                        document.getElementById("oculto").value = "CONSCODIGO";
                        return true;

                    } else {
                        alert("Solo se permiten numeros en el codigo");
                        return false;
                    }
                }
            }

            function consDescripcion() {
                var desc = document.getElementById("txtDescripcion").value;

                if (desc === "") {
                    alert("Debe ingresar una descripcion a buscar");
                    return false;
                } else {
                    document.getElementById("oculto").value = "CONSDESC";
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

                if (request.getAttribute("codigo") == null) {
                    request.setAttribute("codigo", "");
                }

                if (request.getAttribute("descripcion") == null) {
                    request.setAttribute("descripcion", "");
                }

                if (request.getAttribute("cantidadTotal") == null) {
                    request.setAttribute("cantidadTotal", "");
                }

                if (request.getAttribute("cantidadPrestado") == null) {
                    request.setAttribute("cantidadPrestado", "");
                }

                if (request.getAttribute("cantidadTotal") == null) {
                    request.setAttribute("cantidadTotal", "");
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
        <h1>Mantenimiento Herramienta Medica</h1>

        <%--Formulario de Herramienta medica--%>
        <form method="get" action = "ServletMantHerramienta">

            <%--crea la tabla de herramienta medica--%>
            <table border="1">
                <tr>
                    <%--crea la fila para codigo--%>
                    <td>Codigo</td>
                    <td>
                        <%--crea input para codigo--%>
                        <%if (request.getAttribute("codigo") != "") {%>
                        <input type="text" id="txtCodigo" name="codigo" readonly ="true"
                               value ="<%= request.getAttribute("codigo")%>" />
                        <%} else {%>
                        <input type="text" id="txtCodigo" name="codigo"
                               value ="<%= request.getAttribute("codigo")%>" />
                        <%}%>
                    </td>
                </tr>

                <tr>
                    <%--crea la fila para descripcion--%>
                    <td>Descripcion</td>
                    <td>
                        <%--crea input para descripcion--%>
                        <input type="text" id="txtDescripcion" name="descripcion"
                               value ="<%= request.getAttribute("descripcion")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <%--crea la fila para cantidad total--%>
                    <td>Cantidad Total</td>
                    <td>
                        <%--crea input para cantidad total--%>
                        <input type="text" id="txtCantidadTotal" name="cantidadTotal"
                               value ="<%= request.getAttribute("cantidadTotal")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <%--crea la fila para cantidad prestado--%>
                    <td>Cantidad Prestado</td>
                    <td>
                        <%--crea input para cantidad prestado--%>
                        <input type="text" id="txtCantidadPrestado" name="cantidadPrestado"
                               value ="<%= request.getAttribute("cantidadPrestado")%>"
                               />
                    </td>
                </tr>

            </table>
            <br/>

            <%--Declara botones para mantenimiento --%>
            <input type="submit" value ="Agregar" onclick="return agregar();"/>
            <input type="submit" value ="Modificar" onclick="return modificar();"/>
            <input type="submit" value ="Eliminar" onclick="return eliminar();"/>
            <input type="submit" value ="Consulta por codigo" onclick="return consCodigo();"/>
            <input type="submit" value ="Consulta por descripcion" onclick="return consDescripcion();"/>
            <input type="submit" value ="Regresar" onclick="return regresar();"/>
            <input type="submit" value ="Limpiar" onclick="return limpiar();"/>



            <%--El hidden, oculta la opcion q se selecciono--%>
            <input type = "hidden" id = "oculto" name = "accion"/>
            <input type = "hidden" id = "seleccionado" name = "seleccionado"/>
            <hr/>

            <%--Crea tabla para mostrar datos--%>
            <table>
                <tr>
                    <th>Codigo</th>
                    <th>Descripcion</th>
                    <th>Cantidad Total</th>
                    <th>Cantidad Prestado</th>
                    <th>Seleccionar</th>

                </tr>
                <%--Crea lista y herramientaBo para llamar los datos desde la BD--%>
       
                <%! List<HerramientaMedica> listaHerr = new ArrayList();%>
                <%! HerramientaMedicaBo herrBo = new HerramientaMedicaBo();%>

                <%
                    //prueba si el parametro de busqueda por codigo esta
                    if (request.getParameter("CONSCODIGO") != null) {

                        //entra el caso de si se busca por cedula
                        String codigo = request.getParameter("CONSCODIGO");

                        HerramientaMedica herr = this.herrBo.consultarPorCodigo(codigo).get(0);
                        this.listaHerr = new ArrayList<HerramientaMedica>();
                        
                        if (herr == null) {
                            out.println("<h4>No se encontro la herramienta medica buscada.</h4>");
                        } else {
                            this.listaHerr.add(herr);
                        }

                    } else {

                        //entra el caso de si hay busqueda por descripcion
                        if (request.getParameter("CONSDESC") != null) {
                            String desc = request.getParameter("CONSDESC");
                            this.listaHerr = herrBo.consultarPorDescripcion(desc);
                            if (this.listaHerr.isEmpty()) {
                                out.println("<h4>No se encontro la herramienta con la descripcion buscada.</h4>");
                            }
                        } else {
                            this.listaHerr = herrBo.consultaTodos();
                        }
                    }

                    //this.listaProfes = profeBo.consultaTodos();
                    for (int i = 0; i < listaHerr.size(); i++) {
                        HerramientaMedica herr = listaHerr.get(i);
                       
                        //datos de codigo
                        out.println("<tr>"); //abre la fila
                        out.println("<td>");
                        out.println(herr.getCodigo());
                        
                        out.println("</td>");

                        //datos de descripcion
                        out.println("<td>");
                        out.println(herr.getDescripcion());
                        out.println("</td>");

                        //datos de cantidad total
                        out.println("<td>");
                        out.println(herr.getCantidadTotal());
                        out.println("</td>");

                        //datos de cantidad prestados
                        out.println("<td>");
                        out.println(herr.getCantidadPrestado());
                        out.println("</td>");

                %>
                <td>

                    <input type = "image" src = "img/seleccione.PNG" width = "25px"
                           onclick = "return seleccionar(<%=herr.getCodigo()%>)"/>

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
