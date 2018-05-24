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
    try{
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/estilos.css">
        <link rel="stylesheet" href="assets/css/botones.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <ul class="show-tables">
            <h1>Tablas:</h1>
            <% for(int i=0; i<bds.size(); i++){%>
            <li><button class="table-button"><%= bds.get(i)%></button></li>
            <%}%>
        </ul>
        <div id="area-principal">
            <div class="toolbar">
            <button class="control" style="font-size:24px"><i class="material-icons">done</i></button>
            </div>
            <div id="trabajo">
        
                </div>
        </div>
        


        <script src="assets/js/qbe.js"></script>
    </body>
</html>
<%}catch(Exception e){}%>