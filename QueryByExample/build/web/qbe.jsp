<%-- 
    Document   : qbe
    Created on : 2/04/2018, 12:45:31 PM
    Author     : augusto
--%>


<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="db.Conexion"%>

<%
    if( session.getAttribute("usuario")==null ){
        response.sendRedirect("index.html");
    }
    else if( session.getAttribute("basedatos")==null){
        response.sendRedirect("bddisponibles.jsp");
    }
    
    Conexion c = new Conexion(
            request.getSession().getAttribute("host").toString(),
            Integer.valueOf(request.getSession().getAttribute("puerto").toString()),
            request.getSession().getAttribute("usuario").toString(),
            request.getSession().getAttribute("contrasena").toString(),
            request.getSession().getAttribute("basedatos").toString()
    );
    
    c.conectar();
    ArrayList<String> bds = c.getNombreTablas();
    c.desconectar();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% for(int i=0; i<bds.size(); i++){%>
        <div class="tarjeta">
            <h3><%= bds.get(i)%></h3>
            <div class="cuerpo-tarjeta">
                 <a class="boton boton-exito" href="usarbd?bd=<%= bds.get(i)%>">Usar</a>
            </div>
        
        <%}%>
        
       
    </body>
</html>
