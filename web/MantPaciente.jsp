<%-- 
    Document   : MantPaciente
    Created on : 05-jun-2020, 19:51:56
    Author     : Danny
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="negocio.bo.PacienteBo"%>
<%@page import="negocio.clases.Paciente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento Paciente</title>

        <script type = "text/javascript">
            function validarDatos() {
                //valida datos
                //como todos son strings, nada mas hay q revisar q los campos
                //esten llenos. Buscar un validar con JS.  

                var numAse = document.getElementById("txtNumeroAsegurado").value;
                var nomCom = document.getElementById("txtNombreCompleto").value;
                var dir = document.getElementById("txtDireccion").value;
                var edad = document.getElementById("txtEdad").value;
                var fechaNac = document.getElementById("txtFechaNacimiento").value;
                var email = document.getElementById("txtEmail").value;
                var tel = document.getElementById("txtTelefono").value;
                var prof = document.getElementById("txtProfesion").value;

                //valida numAsegurado
                if (numAse === "") {
                    alert("debe ingresar numero de asegurado");
                    return false;
                }
                //valida nombreCompleto
                if (nomCom === "") {
                    alert("debe ingresar nombre completo");
                    return false;
                }
                //valida direccion
                if (dir === "") {
                    alert("debe ingresar direccion");
                    return false;
                }
                //valida edad
                if (edad === "") {
                    alert("debe ingresar edad");
                    return false;
                }
                //valida fechaNac
                if (fechaNac === "") {
                    alert("debe ingresar fecha de nacimiento");
                    return false;
                }
                //valida email
                if (email === "") {
                    alert("debe ingresar email");
                    return false;
                }

                //valida telefono
                if (tel === "") {
                    alert("debe ingresar telefono");
                    return false;
                }

                //valida profesion
                if (prof === "") {
                    alert("debe ingresar profesion");
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
            function consNum() {

                var num = document.getElementById("txtNumeroAsegurado").value;
                if (num === "") {
                    alert("debe numero asegurado");
                } else {
                    if (!isNaN(num)) {
                        document.getElementById("oculto").value = "CONSNUM";
                        return true;

                    } else {
                        alert("Solo se permiten numeros en el numero de asegurado");
                        return false;
                    }
                }
            }
            function consNombre() {
                var nombre = document.getElementById("txtNombreCompleto").value;

                if (nombre === "") {
                    alert("Debe ingresar un nombre a buscar");
                    return false;
                } else {
                    document.getElementById("oculto").value = "CONSNOMBRE";
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

                if (request.getAttribute("numeroAsegurado") == null) {
                    request.setAttribute("numeroAsegurado", "");
                }

                if (request.getAttribute("nombreCompleto") == null) {
                    request.setAttribute("nombreCompleto", "");
                }

                if (request.getAttribute("direccion") == null) {
                    request.setAttribute("direccion", "");
                }

                if (request.getAttribute("edad") == null) {
                    request.setAttribute("edad", "");
                }

                if (request.getAttribute("fechaNacimiento") == null) {
                    request.setAttribute("fechaNacimiento", "");
                }

                if (request.getAttribute("email") == null) {
                    request.setAttribute("email", "");
                }

                if (request.getAttribute("telefono") == null) {
                    request.setAttribute("telefono", "");
                }

                if (request.getAttribute("profesion") == null) {
                    request.setAttribute("profesion", "");
                }

        %>

        <%--Deja la fecha al margen derecho de la pantalla--%>
        <div style="text-align: right; margin-right: 30px;">
            Hola <%= session.getAttribute("usuario")%>
            <br/>
            <%! SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");%>
            <%! String fecha = formatter.format(new Date());%>
            <%--<%=new Date()%>--%>
            <%=fecha%>
           
        </div>

        <%--Titulo de la pagina--%>
        <h1>Mantenimiento Paciente</h1>

        <%--Formulario de paciente--%>
        <form method="get" action = "ServletMantPaciente">

            <table border="1">


                <tr>
                    <td>Numero Asegurado</td>
                    <td>
                        <%--obtiene cedula--%>
                        <%if (request.getAttribute("numeroAsegurado") != "") {%>
                        <input type="text" id="txtNumeroAsegurado" name="numeroAsegurado" readonly ="true"
                               value ="<%= request.getAttribute("numeroAsegurado")%>" />
                        <%} else {%>
                        <input type="text" id="txtNumeroAsegurado" name="numeroAsegurado"
                               value ="<%= request.getAttribute("numeroAsegurado")%>" />
                        <%}%>
                    </td>
                </tr>

                <tr>
                    <td>Nombre completo</td>
                    <td>
                        <%--obtiene nombre--%>
                        <input type="text" id="txtNombreCompleto" name="nombreCompleto"
                               value ="<%= request.getAttribute("nombreCompleto")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <td>Direccion</td>
                    <td>
                        <%--obtiene nombre--%>
                        <input type="text" id="txtDireccion" name="direccion"
                               value ="<%= request.getAttribute("direccion")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <td>Edad</td>
                    <td>
                        <%--obtiene nombre--%>
                        <input type="text" id="txtEdad" name="edad"
                               value ="<%= request.getAttribute("edad")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <td>Fecha Nacimiento</td>
                    <td>
                        <%--obtiene nombre--%>
                        <input type="text" id="txtFechaNacimiento" name="fechaNacimiento"
                               value ="<%= request.getAttribute("fechaNacimiento")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <td>Email</td>
                    <td>
                        <%--obtiene nombre--%>
                        <input type="text" id="txtEmail" name="email"
                               value ="<%= request.getAttribute("email")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <td>Telefono</td>
                    <td>
                        <%--obtiene escuela--%>
                        <input type="text" id="txtTelefono" name="telefono"
                               value ="<%= request.getAttribute("telefono")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <td>Profesion</td>
                    <td>
                        <%--obtiene escuela--%>
                        <input type="text" id="txtProfesion" name="profesion"
                               value ="<%= request.getAttribute("profesion")%>"
                               />
                    </td>
                </tr>
            </table>
            <br/>

            <%--Declara botones para mantenimiento --%>
            <input type="submit" value ="Agregar" onclick="return agregar();"/>
            <input type="submit" value ="Modificar" onclick="return modificar();"/>
            <input type="submit" value ="Eliminar" onclick="return eliminar();"/>
            <input type="submit" value ="Consulta por numero de asegurado" onclick="return consNum();"/>
            <input type="submit" value ="Consulta por nombre" onclick="return consNombre();"/>
            <input type="submit" value ="Regresar" onclick="return regresar();"/>
            <input type="submit" value ="Limpiar" onclick="return limpiar();"/>



            <%--El hidden, oculta la opcion q se selecciono--%>
            <input type = "hidden" id = "oculto" name = "accion"/>
            <input type = "hidden" id = "seleccionado" name = "seleccionado"/>
            <hr/>

            <%--Crea tabla para mostrar datos--%>
            <table>
                <tr>
                    <th>Numero Asegurado</th>
                    <th>Nombre Completo</th>
                    <th>Direccion</th>
                    <th>Edad</th>
                    <th>Fecha Nacimiento</th>
                    <th>Email</th>
                    <th>Telefono</th>
                    <th>Profesion</th>
                    <th>Seleccionar</th>

                </tr>

                <%--Crea lista y pacienteBo para llamar los datos desde la BD--%>
                <%! List<Paciente> listaPac = new ArrayList();%>
                <%! PacienteBo pacBo = new PacienteBo();%>
                <%
                    //prueba si el parametro de busqueda por numero esta
                    if (request.getParameter("CONSNUM") != null) {
                        String num = request.getParameter("CONSNUM");

                        Paciente pac = this.pacBo.consultarPorNumAsegurado(num).get(0);
                        this.listaPac = new ArrayList<Paciente>();

                        if (pac == null) {
                            out.println("<h4>No se encontro el paciente con el numero de asegurado.</h4>");
                        } else {
                            this.listaPac.add(pac);
                        }
                    } else {
                        if (request.getParameter("CONSNOMBRE") != null) {
                            String nombre = request.getParameter("CONSNOMBRE");
                            this.listaPac = pacBo.consultarPorNombre(nombre);
                            if (this.listaPac.isEmpty()) {
                                out.println("<h4>No se encontro el paciente con nombre dado.</h4>");
                            }
                        } else {
                            this.listaPac = pacBo.consultaTodos();
                        }
                    }

                    //this.listaProfes = profeBo.consultaTodos();
                    for (int i = 0; i < listaPac.size(); i++) {

                        Paciente pac = listaPac.get(i);

                        //datos de numero de asegurado
                        out.println("<tr>"); //abre la fila
                        out.println("<td>");
                        out.println(pac.getNumAsegurado());
                        out.println("</td>");

                        //datos de nombre completo
                        out.println("<td>");
                        out.println(pac.getNombreCompleto());
                        out.println("</td>");

                        //datos de direccion
                        out.println("<td>");
                        out.println(pac.getDireccion());
                        out.println("</td>");
                        
                        //datos de edad
                        out.println("<td>");
                        out.println(pac.getEdad());
                        out.println("</td>");

                        //datos de fecha nacimiento
                        out.println("<td>");
                        out.println(pac.getFechaNacimiento());
                        out.println("</td>");

                        //datos de email
                        out.println("<td>");
                        out.println(pac.getEmail());
                        out.println("</td>");

                        //datos de telefono
                        out.println("<td>");
                        out.println(pac.getTelefono());
                        out.println("</td>");

                        //datos de profesion
                        out.println("<td>");
                        out.println(pac.getProfesion());
                        out.println("</td>");

                %>
                <td>
                    <%--Crea icono de seleccion--%>
                    <input type = "image" src = "img/seleccione.PNG" width = "25px"
                           onclick = "return seleccionar(<%=pac.getNumAsegurado()%>)"/>

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
