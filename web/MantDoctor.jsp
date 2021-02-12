<%-- 
    Document   : MantDoctor
    Created on : 05-jun-2020, 19:51:22
    Author     : Danny
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="negocio.bo.DoctorBo"%>
<%@page import="negocio.clases.Doctor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento Doctor</title>

        <script type = "text/javascript">
            function validarDatos() {
                //valida datos
                //como todos son strings, nada mas hay q revisar q los campos
                //esten llenos. Buscar un validar con JS.  

                var ced = document.getElementById("txtCedula").value;
                var nom = document.getElementById("txtNombre").value;
                var ape = document.getElementById("txtApellido").value;
                var espe = document.getElementById("txtEspecialidad").value;
                var sala = document.getElementById("txtSalario").value;
                var dir = document.getElementById("txtDireccion").value;
                var tel = document.getElementById("txtTelefono").value;

                if (isNaN(ced)) {
                    alert("cedula no es numero");
                    return false;
                }
                //valida cedula
                if (ced === "") {
                    alert("debe ingresar cedula");
                    return false;
                }
                //valida nombre
                if (nom === "") {
                    alert("debe ingresar nombre");
                    return false;
                }
                //valida apellido
                if (ape === "") {
                    alert("debe ingresar apellido");
                    return false;
                }
                //valida especialidad
                if (espe === "") {
                    alert("debe ingresar especialidad");
                    return false;
                }
                //valida salario
                if (sala === "") {
                    alert("debe ingresar salario");
                    return false;
                }

                //valida direccion
                if (dir === "") {
                    alert("debe ingresar direccion");
                    return false;
                }

                //valida telefono
                if (tel === "") {
                    alert("debe ingresar telefono");
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
            function consCedula() {

                var cedula = document.getElementById("txtCedula").value;
                if (cedula === "") {
                    alert("debe ingresar cedula");
                } else {
                    if (!isNaN(cedula)) {
                        document.getElementById("oculto").value = "CONSCEDULA";
                        return true;

                    } else {
                        alert("Solo se permiten numeros en la cedula");
                        return false;
                    }
                }
            }
            function consNombre() {
                var nombre = document.getElementById("txtNombre").value;

                if (nombre === "") {
                    alert("Debe ingresar un nombre a buscar");
                    return false;
                } else {
                    document.getElementById("oculto").value = "CONSNOMBRE";
                    return true;
                }

            }
            function seleccionar(cedula) {
                document.getElementById("oculto").value = "SELECCIONAR";
                document.getElementById("seleccionado").value = cedula;
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

                if (request.getAttribute("cedula") == null) {
                    request.setAttribute("cedula", "");
                }

                if (request.getAttribute("nombre") == null) {
                    request.setAttribute("nombre", "");
                }

                if (request.getAttribute("apellido") == null) {
                    request.setAttribute("apellido", "");
                }

                if (request.getAttribute("especialidad") == null) {
                    request.setAttribute("especialidad", "");
                }

                if (request.getAttribute("salario") == null) {
                    request.setAttribute("salario", "");
                }

                if (request.getAttribute("direccion") == null) {
                    request.setAttribute("direccion", "");
                }

                if (request.getAttribute("telefono") == null) {
                    request.setAttribute("telefono", "");
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
        <h1>Mantenimiento Doctor</h1>

        <%--Formulario de doctor--%>
        <form method="get" action = "ServletMantDoctor">

            <%--crea la tabla--%>
            <table border="1">
                <tr>
                    <%--crea la fila para cedula--%>
                    <td>Cedula</td>
                    <td>
                        <%--crea input para cedula--%>
                        <%if (request.getAttribute("cedula") != "") {%>
                        <input type="text" id="txtCedula" name="cedula" readonly ="true"
                               value ="<%= request.getAttribute("cedula")%>" />
                        <%} else {%>
                        <input type="text" id="txtCedula" name="cedula"
                               value ="<%= request.getAttribute("cedula")%>" />
                        <%}%>
                    </td>
                </tr>

                <tr>
                    <%--crea la fila para nombre--%>
                    <td>Nombre</td>
                    <td>
                        <%--crea input para nombre--%>
                        <input type="text" id="txtNombre" name="nombre"
                               value ="<%= request.getAttribute("nombre")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <%--crea la fila para apellido--%>
                    <td>Apellido</td>
                    <td>
                        <%--crea input para apellido--%>
                        <input type="text" id="txtApellido" name="apellido"
                               value ="<%= request.getAttribute("apellido")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <%--crea la fila para especialidad--%>
                    <td>Especialidad</td>
                    <td>
                        <%--crea input para especialidad--%>
                        <input type="text" id="txtEspecialidad" name="especialidad"
                               value ="<%= request.getAttribute("especialidad")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <%--crea la fila para salario--%>
                    <td>Salario</td>
                    <td>
                        <%--crea input para salario--%>
                        <input type="number" step="0.01" id="txtSalario" name="salario"
                               value="<%= request.getAttribute("salario")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <%--crea la fila para direccion --%>
                    <td>Direccion</td>
                    <td>
                        <%--crea input para direccion--%>
                        <input type="text" id="txtDireccion" name="direccion"
                               value ="<%= request.getAttribute("direccion")%>"
                               />
                    </td>
                </tr>

                <tr>
                    <%--crea la fila para telefono --%>
                    <td>Telefono</td>
                    <td>
                        <%--crea input para telefono--%>
                        <input type="text" id="txtTelefono" name="telefono"
                               value ="<%= request.getAttribute("telefono")%>"
                               />
                    </td>
                </tr>
            </table>
            <br/>

            <%--Declara botones para mantenimiento --%>
            <input type="submit" value ="Agregar" onclick="return agregar();"/>
            <input type="submit" value ="Modificar" onclick="return modificar();"/>
            <input type="submit" value ="Eliminar" onclick="return eliminar();"/>
            <input type="submit" value ="Consulta por cedula" onclick="return consCedula();"/>
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
                    <th>Cedula</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Especialidad</th>
                    <th>Salario</th>
                    <th>Direccion</th>
                    <th>Telefono</th>
                    <th>Seleccionar</th>

                </tr>
                <%--Crea lista y profeBo para llamar los datos desde la BD--%>
                <%! List<Doctor> listaDoc = new ArrayList();%>
                <%! DoctorBo docBo = new DoctorBo();%>


                <%
                    //prueba si el parametro de busqueda por cedula esta
                    if (request.getParameter("CONSCEDULA") != null) {

                        //entra el caso de si se busca por cedula
                        String cedula = request.getParameter("CONSCEDULA");

                        Doctor doc = this.docBo.consultarPorCedula(cedula).get(0);
                        this.listaDoc = new ArrayList<Doctor>();

                        if (doc == null) {
                            out.println("<h4>No se encontro el doctor con la cedula dada.</h4>");
                        } else {
                            this.listaDoc.add(doc);
                        }

                    } else {

                        //entra el caso de si hay busqueda por nombre
                        if (request.getParameter("CONSNOMBRE") != null) {
                            String nombre = request.getParameter("CONSNOMBRE");
                            this.listaDoc = docBo.consultarPorNombre(nombre);
                            if (this.listaDoc.isEmpty()) {
                                out.println("<h4>No se encontro el doctor con nombre dado.</h4>");
                            }
                        } else {
                            this.listaDoc = docBo.consultaTodos();
                        }
                    }

                    //this.listaProfes = profeBo.consultaTodos();
                    for (int i = 0; i < listaDoc.size(); i++) {
                        Doctor doc = listaDoc.get(i);
                        //datos de cedula
                        out.println("<tr>"); //abre la fila
                        out.println("<td>");
                        out.println(doc.getCedula());
                        out.println("</td>");

                        //datos de nombre
                        out.println("<td>");
                        out.println(doc.getNombre());
                        out.println("</td>");

                        //datos de apellido
                        out.println("<td>");
                        out.println(doc.getApellido());
                        out.println("</td>");

                        //datos de especialidad
                        out.println("<td>");
                        out.println(doc.getEspecialidad());
                        out.println("</td>");

                        //datos de salario
                        out.println("<td>");
                        out.println(doc.getSalario());
                        out.println("</td>");

                        //datos de direccion
                        out.println("<td>");
                        out.println(doc.getDireccion());
                        out.println("</td>");

                        //datos de Telefono
                        out.println("<td>");
                        out.println(doc.getTelefono());
                        out.println("</td>");

                %>
                <td>

                    <input type = "image" src = "img/seleccione.PNG" width = "25px"
                           onclick = "return seleccionar(<%=doc.getCedula()%>)"/>

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
