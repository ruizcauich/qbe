
<%@page import="java.util.ArrayList"%>
<%-- 
    Document   : home.jsp
    Created on : 25/02/2018, 07:15:21 PM
    Author     : augusto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="db.Conexion"%>

<%
    if( session.getAttribute("usuario")==null ){
        response.sendRedirect("index.html");
    }
    
    Conexion c = new Conexion(
            request.getSession().getAttribute("host").toString(),
            Integer.valueOf(request.getSession().getAttribute("puerto").toString()),
            request.getSession().getAttribute("usuario").toString(),
            request.getSession().getAttribute("contrasena").toString()
    );
    
    c.conectar();
    ArrayList<String> bds = c.getNombreBasesDeDatos();
    c.desconectar();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Expression Language</title>
        <link rel="stylesheet" href="assets/css/botones.css">
        <link rel="stylesheet" href="assets/css/estilos.css">
        </head>
    <body>
        <header>
            <div class="menu-usuario">
                <ul>
                    <li >
                                <img src="assets/img/avatar.png">
                                <span>${sessionScope.usuario}</span>
                       
                        
                    </li>
                    <li>
                        <a href="logout"> Cerrar Sesion</a>
                    </li>
                </ul>
                
                
                
            </div>
        </header>
        <main>
            
            <h1>Bienvenido ${sessionScope.usuario} </h1>
            <p>
                SELECCIONE LA BASE DE DATOS CON LA QUE TRABAJARÁ
            </p>
           <%
              for(int i=0; i<bds.size();i++){
                 
               
           %> 
           <div class="tarjeta">
               <div class="cuerpo-tarjeta">
                    <h3><%=bds.get(i)%></h3>
                    
                    <a class="boton boton-exito" href="usarbd?bd=<%=bds.get(i)%>">Usar</a>
               </div>
               
           </div>
           <%}%>
           
        </main>
        <footer class="page-footer">
            <p>Usted se encuentra conectado a ${sessionScope.host}
               
            </p>
        </footer>
        
                
    </body>
</html>
